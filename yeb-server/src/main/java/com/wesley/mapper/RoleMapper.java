package com.wesley.mapper;

import com.wesley.pojo.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Wesley
 * @since 2023-11-05
 */
@Component
public interface RoleMapper extends BaseMapper<Role> {
    List<Role> getRoles(Integer adminId);
}
