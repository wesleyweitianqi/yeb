package com.wesley.mapper;

import com.wesley.pojo.Admin;
import com.wesley.pojo.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Wesley
 * @since 2023-11-05
 */
public interface MenuMapper extends BaseMapper<Menu> {
    /**
     * get menu by user ID
     * @param id
     * @return
     */
    List<Menu> getMenusByAdminId(Integer id);

    List<Menu> getMenusWithRole();
}
