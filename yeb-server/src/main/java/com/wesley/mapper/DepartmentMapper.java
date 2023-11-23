package com.wesley.mapper;

import com.wesley.pojo.Department;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Wesley
 * @since 2023-11-05
 */
public interface DepartmentMapper extends BaseMapper<Department> {


    List<Department> getAllDepartments(int parentId);

    void addDep(Department dep);
}
