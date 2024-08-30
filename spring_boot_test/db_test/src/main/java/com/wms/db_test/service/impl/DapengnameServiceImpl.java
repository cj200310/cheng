package com.wms.db_test.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.db_test.entity.Dapengname;
import com.wms.db_test.mapper.DapengnameMapper;
import com.wms.db_test.mapper.TemperaturesMapper;
import com.wms.db_test.service.DapengnameService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
public class DapengnameServiceImpl extends ServiceImpl<DapengnameMapper, Dapengname> implements DapengnameService {

    @Resource
    private DapengnameMapper dapengnameMapper;

    @Override
    public IPage dapeng(Page<Dapengname> page, Wrapper wrapper) {
        return dapengnameMapper.dapeng(page,wrapper);
    }
}
