package com.wesley.service.impl;

import com.wesley.pojo.Nation;
import com.wesley.mapper.NationMapper;
import com.wesley.service.NationService;
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
public class NationServiceImpl extends ServiceImpl<NationMapper, Nation> implements NationService {

}
