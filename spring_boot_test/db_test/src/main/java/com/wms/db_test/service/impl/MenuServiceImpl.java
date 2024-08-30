package com.wms.db_test.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wms.db_test.entity.Menu;
import com.wms.db_test.mapper.MenuMapper;
import com.wms.db_test.service.MenuService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author db_test
 * @since 2024-05-26
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

}
