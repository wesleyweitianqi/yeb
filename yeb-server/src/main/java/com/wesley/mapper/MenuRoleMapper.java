package com.wesley.mapper;

import com.wesley.pojo.MenuRole;
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

/**
 * update role menus
 * @param rid
 * @param mids
 * @return
 */
public interface MenuRoleMapper extends BaseMapper<MenuRole> {
    Integer insertRecord(@Param("rid") Integer rid, @Param("mids") Integer[] mids);
}
