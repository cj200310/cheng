package com.wms.db_test.controller;


import com.wms.db_test.commom.Result;
import com.wms.db_test.entity.Control;
import com.wms.db_test.entity.Shebei;
import com.wms.db_test.service.ControlService;
import com.wms.db_test.service.ShebeiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author db_test
 * @since 2024-07-03
 */
@RestController
@RequestMapping("/control")
public class ControlController {

    @Autowired
    private ControlService controlService;

    //新增
    @PostMapping("/save")
    public Result save(@RequestBody Control control) {
        return controlService.save(control)?Result.suc():Result.fail();
    }
    //更新
    @PostMapping("/update")
    public Result update(@RequestBody Control control) {
        System.out.println(control);
        return controlService.updateById(control)?Result.suc():Result.fail();
    }
    //删除
    @GetMapping("/del")
    public Result del(@RequestParam String id) {
        return controlService.removeById(id)?Result.suc():Result.fail();
    }

}
