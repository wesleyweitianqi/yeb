package com.wesley.service.impl;

import com.wesley.pojo.Admin;
import com.wesley.mapper.AdminMapper;
import com.wesley.service.IAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wesley
 * @since 2023-11-05
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

}
