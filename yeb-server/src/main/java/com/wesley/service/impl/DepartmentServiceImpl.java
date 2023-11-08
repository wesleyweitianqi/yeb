package com.wesley.service.impl;

import com.wesley.pojo.Department;
import com.wesley.mapper.DepartmentMapper;
import com.wesley.service.DepartmentService;
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
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements DepartmentService {

}
