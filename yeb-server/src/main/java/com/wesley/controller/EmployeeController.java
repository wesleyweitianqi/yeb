package com.wesley.controller;


import com.wesley.pojo.*;
import com.wesley.service.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.List;

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

    @Autowired
    private NationService nationService;

    @Autowired
    private PoliticsStatusService politicsStatusService;

    @Autowired
    private JoblevelService joblevelService;

    @Autowired
    private PositionService positionService;

    @Autowired
    private  DepartmentService departmentService;

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

    @ApiOperation(value = "add employee")
    @PostMapping("/")
    public RespBean addEmp(@RequestBody Employee employee) {
        return employeeService.insertEmployee(employee);
    }
    @ApiOperation(value = "get nations")
    @GetMapping("/nations")
    public List<Nation> getAllNations() {
        return nationService.list();
    }
    @ApiOperation(value = "get politics")
    @GetMapping("/politicsstatus")
    public List<PoliticsStatus> getAllPoliticsStatus() {
        return politicsStatusService.list();
    }
    @ApiOperation(value = "get joblevels")
    @GetMapping("/joblevels")
    public List<Joblevel> getAllJobLevels() {
        return joblevelService.list();
    }
    @ApiOperation(value = "get positions")
    @GetMapping("/positions")
    public List<Position> getAllPositions() {
        return positionService.list();
    }
    @ApiOperation(value = "get workId")
    @GetMapping("/maxWorkId")
    public RespBean maxWorkID() {
        return employeeService.maxWorkId();
    }
    @ApiOperation(value = "get departments")
    @GetMapping("/deps")
    public List<Department> getAllDepartments(){
        return departmentService.getAllDepartments();
    }
}

