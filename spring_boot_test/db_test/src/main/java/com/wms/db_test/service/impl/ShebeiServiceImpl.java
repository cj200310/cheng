package com.wms.db_test.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.db_test.entity.Shebei;
import com.wms.db_test.mapper.ShebeiMapper;
import com.wms.db_test.service.ShebeiService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author db_test
 * @since 2024-07-02
 */
@Service
public class ShebeiServiceImpl extends ServiceImpl<ShebeiMapper, Shebei> implements ShebeiService {
    @Resource
    private ShebeiMapper shebeiMapper;

    @Override
    public IPage shebei(Page<Shebei> page, Wrapper wrapper) {
        return shebeiMapper.shebei(page,wrapper);
    }

    @Override
    public IPage dpc(Page<Shebei> page, String dpname) {
        return shebeiMapper.dpc(page,dpname);
    }
}
