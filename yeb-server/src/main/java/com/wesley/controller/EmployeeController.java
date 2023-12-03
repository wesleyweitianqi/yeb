package com.wesley.controller;


import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.wesley.pojo.*;
import com.wesley.service.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
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

        return employeeService.getEmployeePage(currentPage, size, employee, beginDateScope);
    }

    @ApiOperation(value = "add employee")
    @PostMapping("/")
    public RespBean addEmp(@RequestBody Employee employee) {
        System.out.println("===============>add employee");
        return employeeService.insertEmployee(employee);
    }

    @ApiOperation(value = "get employee")
    @GetMapping("/{id}")
    public Employee getEmployee(@PathVariable Integer id){
        System.out.println("get employyee id " + id);
        return (Employee) employeeService.getEmployee(id).get(0);
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

    @ApiOperation(value="update Employee")
    @PutMapping("/")
    public RespBean updateEmp(@RequestBody Employee employee){
        if(employeeService.updateById(employee)){
            return RespBean.success("update employee successfully");
        }
        return RespBean.error("update employee failed");
    }

    @ApiOperation(value = "delete employee")
    @DeleteMapping("/{id}")
    public RespBean deleteEmp(@PathVariable Integer id){
        if(employeeService.removeById(id)){
            return RespBean.success("delete employee successfully");
        }
        return RespBean.error("delete employee failed");
    }

    @ApiOperation(value = "export employee to excel")
    @GetMapping(value = "/export", produces = "application/octet-stream")
    public void exportEmp(HttpServletResponse response) throws IOException {
        List<Employee> list = employeeService.getEmployee(null);
        ExportParams params = new ExportParams("employee table", "employeeTable", ExcelType.HSSF);
        Workbook workbook = ExcelExportUtil.exportExcel(params, Employee.class, list);
        ServletOutputStream outputStream = null;
        try{
            response.setContentType("application/octet-stream");
            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode("employee table.xls", "UTF-8"));
            outputStream = response.getOutputStream();
            workbook.write(outputStream);
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            if(null != outputStream){
                try{
                    outputStream.flush();
                    outputStream.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }



    }
}

