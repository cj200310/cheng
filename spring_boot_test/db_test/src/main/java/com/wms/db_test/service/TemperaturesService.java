package com.wms.db_test.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.db_test.entity.Dapengname;
import com.wms.db_test.entity.Temperatures;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wms.db_test.entity.guanli;
import com.wms.db_test.entity.temperaturepingjiabiao;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author db_test
 * @since 2024-06-04
 */
public interface TemperaturesService extends IService<Temperatures>{
    void pageCC(double temperatureC, double humidityPercent);

    IPage pageCCC(Page<Temperatures> page);

    void add(String messageBody);

    void addtwo(String messageBody);

    IPage pageCCCC(Page<temperaturepingjiabiao> page);

    IPage getdp(Page<Dapengname> page);

    IPage getgl(Page<guanli> page);

    //IPage dapeng(IPage<dapengname> page, Wrapper wrapper);
}
