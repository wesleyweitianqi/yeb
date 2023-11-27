package com.wesley.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wesley.pojo.Employee;
import com.wesley.mapper.EmployeeMapper;
import com.wesley.pojo.RespBean;
import com.wesley.pojo.RespPageBean;
import com.wesley.service.EmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;

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
        return employeeMapper.getEmployee(id);
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
        Integer res = employeeMapper.insert(employee);
        if (res ==1) {
            return RespBean.success("insert employee successfully");
        }
        return RespBean.error("insert failed");
    }
}
