package com.wesley.mapper;

import com.wesley.pojo.AdminRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Wesley
 * @since 2023-11-05
 */
public interface AdminRoleMapper extends BaseMapper<AdminRole> {
    Integer addRole(@Param("adminId") Integer adminId,@Param("rids") Integer[] rids);
}

