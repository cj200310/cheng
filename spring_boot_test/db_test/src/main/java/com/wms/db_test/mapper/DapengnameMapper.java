package com.wms.db_test.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.db_test.entity.Dapengname;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author db_test
 * @since 2024-07-02
 */
@Mapper
public interface DapengnameMapper extends BaseMapper<Dapengname> {

     IPage dapeng(Page<Dapengname> page, @Param(Constants.WRAPPER) Wrapper wrapper);
}
