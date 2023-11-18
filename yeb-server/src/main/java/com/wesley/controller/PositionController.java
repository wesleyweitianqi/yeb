package com.wesley.controller;


import com.wesley.pojo.Position;
import com.wesley.pojo.RespBean;
import com.wesley.service.PositionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.Arrays;
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
@RequestMapping("/system/basic/pos")
public class PositionController {
    @Autowired
    private PositionService positionService;

    @ApiOperation(value = "get all positions")
    @GetMapping("/all")
    public List<Position> getAllPositions(){
        System.out.println("getall");
        System.out.println(positionService.list().toString());
        return positionService.list();
    }

    @ApiOperation( value ="add position")
    @PostMapping("/")
    public RespBean addPosition(@RequestBody Position position){
        position.setCreateDate(LocalDateTime.now());
        if(positionService.save(position)){
            return RespBean.success("position added");
        }
        return RespBean.error("position adding failed");
    }

    @ApiOperation(value = "update position")
    @PutMapping("/")
    public RespBean updatePosition(@RequestBody Position position) {
        if(positionService.updateById(position)){
            return RespBean.success("update successfully");
        }
        return RespBean.error("update failed");
    }

    @ApiOperation(value = " delete position by Id")
    @DeleteMapping("/{id}")
    public  RespBean deletePosition(@RequestBody Integer id){
        if(positionService.removeById(id)){
            return RespBean.success("deleted successfully");
        }
        return RespBean.error("delete failed");
    }

    @ApiOperation(value =" delete positions by list ")
    @DeleteMapping("/")
    public RespBean deletePositions(@RequestBody int[] ids){
        if(positionService.removeByIds(Arrays.asList(ids))){
            return RespBean.success("delete ids successfully");
        }
        return RespBean.error("delete multiple failed");
    }
}

