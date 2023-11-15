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
    @Select("  SELECT\n" +
            "        m.*,\n" +
            "        r.id AS rid,\n" +
            "        r.`name` AS rname,\n" +
            "        r.nameZh AS rnameZh\n" +
            "        FROM\n" +
            "        t_menu m,\n" +
            "        t_menu_role mr,\n" +
            "        t_role r\n" +
            "        WHERE\n" +
            "        m.id = mr.mid\n" +
            "        AND mr.rid = r.id\n" +
            "        ORDER BY\n" +
            "        m.id")
    List<Menu> getMenusWithRole();
}
