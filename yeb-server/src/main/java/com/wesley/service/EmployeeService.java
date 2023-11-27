package com.wesley.service;

import com.wesley.pojo.Employee;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wesley.pojo.RespBean;
import com.wesley.pojo.RespPageBean;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Wesley
 * @since 2023-11-05
 */
public interface EmployeeService extends IService<Employee> {
    RespPageBean getEmployeePage(Integer currentPage, Integer size, Employee employee, LocalDate[] beginDateScope);

    RespBean insertEmployee(Employee employee);

    RespBean maxWorkId();

    List<Employee> getEmployee(Integer id);
}
