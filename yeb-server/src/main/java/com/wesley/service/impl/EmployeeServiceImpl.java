package com.wesley.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wesley.pojo.*;
import com.wesley.mapper.EmployeeMapper;
import com.wesley.service.EmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.amqp.rabbit.core.RabbitTemplate;


import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Wesley
 * @since 2023-11-05
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * @param currentPage
     * @param size
     * @param employee
     * @param beginDateScope
     * @return get employee list in pagination
     */
    @Override
    public RespPageBean getEmployeePage(Integer currentPage, Integer size, Employee employee, LocalDate[] beginDateScope) {
        Page page = new Page<>(currentPage, size);
        IPage<Employee> employeePage = employeeMapper.getEmployeePage(page, employee, beginDateScope);
        RespPageBean respPageBean = new RespPageBean(employeePage.getTotal(), employeePage.getRecords());
        return respPageBean;
    }

    /**
     * @return get workID
     */
    @Override
    public RespBean maxWorkId() {
        try {
            List<Map<String, Object>> maps = employeeMapper.selectMaps(new QueryWrapper<Employee>().select("max(work_id)"));
            Integer maxId = Integer.parseInt(maps.get(0).get("max(work_id)").toString()) + 1;
            return RespBean.success(null, maxId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Employee> getEmployee(Integer id) {
        try{

            Employee emp = employeeMapper.getEmployee(id).get(0);
            System.out.println(emp.toString());
            return employeeMapper.getEmployee(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }

    /**
     * @param employee
     * @return insert employee
     */
    @Override
    public RespBean insertEmployee(Employee employee) {
        LocalDate beginContract = employee.getBeginContract();
        LocalDate endContract = employee.getEndContract();
        long days = beginContract.until(endContract, ChronoUnit.DAYS);
        DecimalFormat decimalFormat = new DecimalFormat("##.00");
        employee.setContractTerm(Double.parseDouble(decimalFormat.format(days/365.00))
        );
        try{
            Integer res = employeeMapper.insert(employee);
            System.out.println(res);
            if (res ==1) {
                Employee emp = employeeMapper.getEmployee(employee.getId()).get(0);
                String uuid = UUID.randomUUID().toString();
                MailLog mailLog = new MailLog();
                mailLog.setMsgId(uuid);
                mailLog.setEid(emp.getId());
                mailLog.setStatus(0);
                mailLog.setRouteKey(MailConstants.MAIL_ROUTING_KEY_NAME);
                mailLog.setExchange(MailConstants.MAIL_EXCHANGE_NAME);
                mailLog.setCount(MailConstants.MAX_TRY_COUNT);
                mailLog.setTryTime(LocalDateTime.now().plusMinutes(MailConstants.MSG_TIMEOUT));
                mailLog.setCreateTime(LocalDateTime.now());
                mailLog.setUpdateTime(LocalDateTime.now());
                rabbitTemplate.convertAndSend(MailConstants.MAIL_EXCHANGE_NAME,MailConstants.MAIL_ROUTING_KEY_NAME, emp, new CorrelationData(uuid));
                return RespBean.success("insert employee successfully");
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        return RespBean.error("insert failed");
    }
}
