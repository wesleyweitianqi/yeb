package com.wesley.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wesley.pojo.Employee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Wesley
 * @since 2023-11-05
 */
public interface EmployeeMapper extends BaseMapper<Employee> {
    IPage<Employee> getEmployeePage(Page<Employee> page,
                                    @Param("employee") Employee employee,
                                    @Param("beginDateScope") LocalDate[] beginDateScope);

    List<Employee> getEmployee(Integer id);
}
