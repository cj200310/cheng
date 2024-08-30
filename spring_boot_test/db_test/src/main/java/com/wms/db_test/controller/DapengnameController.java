package com.wms.db_test.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.db_test.commom.QueryPageParam;
import com.wms.db_test.commom.Result;
import com.wms.db_test.entity.Dapengname;
import com.wms.db_test.entity.Temperatures;
import com.wms.db_test.service.DapengnameService;
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
@RequestMapping("/dapengname")
public class DapengnameController {

    @Autowired
    private DapengnameService dapengnameService;

    //新增
    @PostMapping("/save")
    public Result save(@RequestBody Dapengname dapengname) {
        return dapengnameService.save(dapengname)?Result.suc():Result.fail();
    }
    //更新
    @PostMapping("/update")
    public Result update(@RequestBody Dapengname dapengname) {
        return dapengnameService.updateById(dapengname)?Result.suc():Result.fail();
    }
    //删除
    @GetMapping("/del")
    public Result del(@RequestParam String id) {
        return dapengnameService.removeById(id)?Result.suc():Result.fail();
    }


    @PostMapping("/dapeng")
    public Result listPage6(@RequestBody QueryPageParam query) {


        HashMap param = query.getParam();
        String dapengName = (String)param.get("dapengname");

        Page<Dapengname> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<Dapengname> lambdaQueryWrapper = new LambdaQueryWrapper();
        if(StringUtils.isNotBlank(dapengName)){
            lambdaQueryWrapper.like(Dapengname::getDapengname,dapengName);
        }

        IPage result= dapengnameService.dapeng(page,lambdaQueryWrapper);
        System.out.println("total"+result.getTotal());

        return Result.suc(result.getRecords(),result.getTotal());
    }

}
