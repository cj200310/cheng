package com.wms.db_test.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wms.db_test.entity.User;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author db_test
 * @since 2024-05-23
 */
public interface UserService extends IService<User> {

    IPage pageC(IPage<User> page);

    IPage pageCC(IPage<User> page, Wrapper wrapper);

    /*void pageCC(double temperatureC, double humidityPercent);

    IPage pageCCC(Page<Temperatures> page);*/

}
