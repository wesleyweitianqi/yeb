package com.wesley.service;

import com.wesley.pojo.Department;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wesley.pojo.RespBean;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Wesley
 * @since 2023-11-05
 */
public interface DepartmentService extends IService<Department> {
    List<Department> getAllDepartments();

    RespBean addDep(Department dep);

    RespBean delDep(Integer id);
}
