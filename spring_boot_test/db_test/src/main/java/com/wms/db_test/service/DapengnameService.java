package com.wms.db_test.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.db_test.entity.Dapengname;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author db_test
 * @since 2024-07-02
 */
public interface DapengnameService extends IService<Dapengname> {

    IPage dapeng(Page<Dapengname> page, Wrapper wrapper);
}
