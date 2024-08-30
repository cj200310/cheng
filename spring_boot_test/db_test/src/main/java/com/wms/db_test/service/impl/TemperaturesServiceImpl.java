package com.wms.db_test.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.db_test.entity.Dapengname;
import com.wms.db_test.entity.Temperatures;
import com.wms.db_test.entity.guanli;
import com.wms.db_test.entity.temperaturepingjiabiao;
import com.wms.db_test.mapper.TemperaturesMapper;
import com.wms.db_test.service.TemperaturesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author db_test
 * @since 2024-06-04
 */
@Service
public class TemperaturesServiceImpl extends ServiceImpl<TemperaturesMapper, Temperatures> implements TemperaturesService {

    @Resource
    private TemperaturesMapper temperaturesMapper;

    @Override
    public void pageCC(double temperatureC, double humidityPercent) {
        temperaturesMapper.pageCC(temperatureC,humidityPercent);
    }

    @Override
    public IPage pageCCC(Page<Temperatures> page) {
        return temperaturesMapper.pageCCC(page);
    }

    @Override
    public void add(String messageBody) {
        temperaturesMapper.add(messageBody);
    }

    @Override
    public void addtwo(String messageBody) {
        temperaturesMapper.addtwo(messageBody);
    }

    @Override
    public IPage pageCCCC(Page<temperaturepingjiabiao> page) {
        return temperaturesMapper.pageCCCC(page);
    }

    @Override
    public IPage getdp(Page<Dapengname> page) {
        return temperaturesMapper.getdp(page);
    }

    @Override
    public IPage getgl(Page<guanli> page) {
        return temperaturesMapper.getgl(page);
    }

    //@Override
    //public IPage dapeng(IPage<dapengname> page, Wrapper wrapper) {
       // return temperaturesMapper.dapeng(page,wrapper);
    //}


}
