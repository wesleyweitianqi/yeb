package com.wesley.controller;


import com.wesley.pojo.Employee;
import com.wesley.pojo.RespPageBean;
import com.wesley.service.EmployeeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.time.LocalDate;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Wesley
 * @since 2023-11-05
 */
@Controller
@RequestMapping("/employee/basic")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @ApiOperation(value ="get employee info")
    @GetMapping("/")
    public RespPageBean getEmployeePage(@RequestParam(defaultValue = "1") Integer currentPage,
                                          @RequestParam(defaultValue = "10") Integer size,
                                          @ModelAttribute Employee employee,
                                          LocalDate[] beginDateScope){
        RespPageBean page = employeeService.getEmployeePage(currentPage, size, employee, beginDateScope);
        System.out.println(page.toString());
        return employeeService.getEmployeePage(currentPage, size, employee, beginDateScope);
    }
}

