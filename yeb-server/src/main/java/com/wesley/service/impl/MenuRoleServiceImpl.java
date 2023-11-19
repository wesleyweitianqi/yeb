package com.wesley.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wesley.mapper.MenuMapper;
import com.wesley.pojo.MenuRole;
import com.wesley.mapper.MenuRoleMapper;
import com.wesley.pojo.RespBean;
import com.wesley.service.MenuRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
public class MenuRoleServiceImpl extends ServiceImpl<MenuRoleMapper, MenuRole> implements MenuRoleService {

    @Autowired
    private MenuRoleService menuRoleService;
    @Autowired
    private MenuRoleMapper menuRoleMapper;
    @Override
    public RespBean updateMenuRole(Integer rid, Integer[] mids) {
        menuRoleMapper.delete(new QueryWrapper<MenuRole>().eq("rid", rid));
        if(null == mids || 0 == mids.length){
            return  RespBean.success("update successfully");
        }
        Integer res = menuRoleMapper.insertRecord(rid, mids);
        if (mids.length == res ){
            return RespBean.success("update successfully");
        }

        return RespBean.error("update failed");
    }


}
