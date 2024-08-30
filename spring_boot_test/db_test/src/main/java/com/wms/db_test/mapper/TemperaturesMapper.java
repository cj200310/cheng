package com.wms.db_test.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.db_test.entity.Dapengname;
import com.wms.db_test.entity.Temperatures;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wms.db_test.entity.guanli;
import com.wms.db_test.entity.temperaturepingjiabiao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author db_test
 * @since 2024-06-04
 */
@Mapper
public interface TemperaturesMapper extends BaseMapper<Temperatures> {

    void pageCC(@Param("temperatureC") double temperatureC, @Param("humidityPercent") double humidityPercent);

    IPage pageCCC(Page<Temperatures> page);

    void add(@Param("messageBody") String messageBody);

    void addtwo(@Param("messageBody") String messageBody);

    IPage pageCCCC(Page<temperaturepingjiabiao> page);

    IPage getdp(Page<Dapengname> page);

    IPage getgl(Page<guanli> page);

    //IPage dapeng(IPage<dapengname> page, @Param(Constants.WRAPPER) Wrapper wrapper);
}
