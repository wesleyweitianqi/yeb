package com.wesley.service.impl;

import com.wesley.pojo.Employee;
import com.wesley.mapper.EmployeeMapper;
import com.wesley.service.EmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Wesley
 * @since 2023-11-05
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

}
