package com.wms.db_test.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wms.db_test.entity.Shebei;
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
public interface ShebeiMapper extends BaseMapper<Shebei> {

    IPage shebei(Page<Shebei> page, @Param(Constants.WRAPPER) Wrapper wrapper);

    IPage dpc(Page<Shebei> page, @Param("dpname") String dpname);
}
