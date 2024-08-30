package com.wms.db_test.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.wms.db_test.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author db_test
 * @since 2024-05-23
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {


    IPage pageC(IPage<User> page);

    IPage pageCC(IPage<User> page,@Param(Constants.WRAPPER) Wrapper wrapper);
}
