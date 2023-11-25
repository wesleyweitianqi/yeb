package com.wesley.controller;


import com.wesley.pojo.Department;
import com.wesley.pojo.RespBean;
import com.wesley.service.DepartmentService;
import com.wesley.service.impl.DepartmentServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

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
@RequestMapping("/system/basic/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;


    //TODO: show list on swagger.
    @ApiOperation(value = "get All departments")
    @GetMapping("/")
    public List<Department> getAllDepartment(){
        return departmentService.getAllDepartments();
    }

    @ApiOperation(value = "addDep")
    @PostMapping("/")
    public RespBean addDep(@RequestBody Department dep){
        return departmentService.addDep(dep);
    }

    @ApiOperation(value = "delete Dep")
    @DeleteMapping("/")
    public  RespBean delDep(@RequestBody Integer id) {
        return departmentService.delDep(id);
    }
}

