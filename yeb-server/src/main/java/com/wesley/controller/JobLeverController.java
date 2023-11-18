package com.wesley.controller;


import ch.qos.logback.core.encoder.EchoEncoder;
import com.wesley.pojo.Joblevel;
import com.wesley.pojo.RespBean;
import com.wesley.service.JoblevelService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/system/basic/joblevel")
public class JobLeverController {
    @Autowired
    private JoblevelService joblevelService;

    @ApiOperation(value ="get all job levels")
    @GetMapping("/")
    public List<Joblevel> getAllJobLevels(){

        try{
            List<Joblevel> list = joblevelService.list();
            return joblevelService.list();

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @ApiOperation(value = "add job level")
    @PostMapping("/")
    public RespBean addJobLevel(@RequestBody Joblevel joblevel){
        joblevel.setCreateDate(LocalDateTime.now());
        if(joblevelService.save(joblevel)){
            return RespBean.success("job level added");
        }
        return RespBean.error("add operation failed");
    }

    @ApiOperation(value = "update job level")
    @PutMapping("/")
    public RespBean updateJobLevel(@RequestBody Joblevel jobLevel){
        if (joblevelService.updateById(jobLevel)){
            return RespBean.success("update successfully!");
        }
        return RespBean.error("update failed!");
    }
    @ApiOperation(value = "delete job level")
    @DeleteMapping("/{id}")
    public RespBean deleteJobLevel(@PathVariable Integer id){
        if (joblevelService.removeById(id)){
            return RespBean.success("delete successfully!");
        }
        return RespBean.error("delete failed!");
    }

    @ApiOperation(value = "delete joblevels in batch")
    @DeleteMapping("/")
    public RespBean deleteJobLevelByIds(Integer[] ids){
        if (joblevelService.removeByIds(Arrays.asList(ids))){
            return RespBean.success("delete successfully!");
        }
        return RespBean.error("delete failed!");
    }
}
