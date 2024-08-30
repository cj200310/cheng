package com.wms.db_test.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wms.db_test.entity.Shebei;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author db_test
 * @since 2024-07-02
 */
public interface ShebeiService extends IService<Shebei> {

    IPage shebei(Page<Shebei> page, Wrapper wrapper);

    IPage dpc(Page<Shebei> page, String dpname);
}
