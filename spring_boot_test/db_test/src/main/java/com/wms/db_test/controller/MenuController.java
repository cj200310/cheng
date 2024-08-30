package com.wms.db_test.controller;


import com.wms.db_test.commom.Result;
import com.wms.db_test.entity.Menu;
import com.wms.db_test.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author db_test
 * @since 2024-05-26
 */
@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @GetMapping("/list")
    public Result list(@RequestParam String roleId) {
        List list = menuService.lambdaQuery().like(Menu::getMenuright,roleId).list();
        return Result.suc(list);

    }
}
