package com.wesley.service.impl;

import com.wesley.pojo.Department;
import com.wesley.mapper.DepartmentMapper;
import com.wesley.pojo.RespBean;
import com.wesley.service.DepartmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Wesley
 * @since 2023-11-05
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements DepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public List<Department> getAllDepartments() {
        return departmentMapper.getAllDepartments(-1);
    }

    @Override
    public RespBean addDep(Department dep) {
        dep.setEnabled(true);
        try{
            departmentMapper.addDep(dep);
            if(1 == dep.getResult()){
                return RespBean.success("dep added successfully", dep);
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        return RespBean.error("dep added failed");
    }

    @Override
    public RespBean delDep(Integer id) {
        Department dep = new Department();
        dep.setId(id);
        departmentMapper.delDep(dep);
        if(-2 == dep.getResult()){
            return RespBean.error("there is children department belong deleted dep, delete failed");
        }
        if(-1 == dep.getResult()){
            return RespBean.error("there is employee belongs to delete dep");
        }
        if(1 == dep.getResult()){
            return RespBean.success("dep delete success");
        }
        return RespBean.error("delete failed");
    }
}
