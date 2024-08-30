package com.wms.db_test.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.db_test.commom.QueryPageParam;
import com.wms.db_test.commom.Result;
import com.wms.db_test.entity.Shebei;
import com.wms.db_test.service.ShebeiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author db_test
 * @since 2024-07-02
 */
@RestController
@RequestMapping("/shebei")
public class ShebeiController {

    @Autowired
    private ShebeiService shebeiService;

    //新增
    @PostMapping("/save")
    public Result save(@RequestBody Shebei shebei) {
        System.out.println(shebei);
        return shebeiService.save(shebei)?Result.suc():Result.fail();
    }
    //更新
    @PostMapping("/update")
    public Result update(@RequestBody Shebei shebei) {
        return shebeiService.updateById(shebei)?Result.suc():Result.fail();
    }
    //删除
    @GetMapping("/del")
    public Result del(@RequestParam String id) {
        return shebeiService.removeById(id)?Result.suc():Result.fail();
    }


    @PostMapping("/shebei")
    public Result listPage6(@RequestBody QueryPageParam query) {


        HashMap param = query.getParam();
        String shebeiname = (String)param.get("shebeiname");

        Page<Shebei> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<Shebei> lambdaQueryWrapper = new LambdaQueryWrapper();
        if(StringUtils.isNotBlank(shebeiname)){
            lambdaQueryWrapper.like(Shebei::getShebeiname,shebeiname);
        }

        IPage result= shebeiService.shebei(page,lambdaQueryWrapper);
        System.out.println("total"+result.getTotal());
        System.out.println(result.getRecords());

        return Result.suc(result.getRecords(),result.getTotal());
    }

    @PostMapping("/dp")
    public Result listPage7(@RequestBody QueryPageParam query) {


        HashMap param = query.getParam();
        String dpname = (String)param.get("dpname");

        Page<Shebei> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        IPage result= shebeiService.dpc(page,dpname);
        System.out.println("total"+result.getTotal());
        System.out.println(result.getRecords());

        return Result.suc(result.getRecords(),result.getTotal());
    }

}
