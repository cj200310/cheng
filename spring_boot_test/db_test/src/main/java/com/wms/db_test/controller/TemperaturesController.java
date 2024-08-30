package com.wms.db_test.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.db_test.commom.QueryPageParam;
import com.wms.db_test.commom.Result;
import com.wms.db_test.entity.Dapengname;
import com.wms.db_test.entity.Temperatures;
import com.wms.db_test.entity.guanli;
import com.wms.db_test.entity.temperaturepingjiabiao;
import com.wms.db_test.service.TemperaturesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author db_test
 * @since 2024-06-04
 */
@RestController
public class TemperaturesController {
    @Autowired
    private TemperaturesService temperaturesService;

    @PostMapping("/temperatures")
    public int listPage(@RequestBody QueryPageParam query) {

        HashMap param = query.getParam();//存储键值对的数据结构。
        double temperatureC = (double)param.get("temperatureC");
        double humidityPercent = (double)param.get("humidityPercent");

        temperaturesService.pageCC(temperatureC,humidityPercent);

        return 200;
    }

    @PostMapping("/list")
    public Result listPage2(@RequestBody QueryPageParam query) {

        Page<Temperatures> page = new Page();
        page.setCurrent(query.getPageNum());//当前页
        page.setSize(query.getPageSize());//每页记录数

        IPage result= temperaturesService.pageCCC(page);

        return Result.suc(result.getRecords());

    }

    @PostMapping("/list2")
    public Result listPage3(@RequestBody QueryPageParam query) {

        Page<temperaturepingjiabiao> page = new Page();
        page.setCurrent(query.getPageNum());//当前页
        page.setSize(query.getPageSize());//每页记录数

        IPage result= temperaturesService.pageCCCC(page);

        return Result.suc(result.getRecords());

    }

    @PostMapping("/list3")
    public Result listPage4(@RequestBody QueryPageParam query) {

        Page<Dapengname> page = new Page();
        page.setCurrent(query.getPageNum());//当前页
        page.setSize(query.getPageSize());//每页记录数

        IPage result= temperaturesService.getdp(page);

        return Result.suc(result.getRecords());

    }

    @PostMapping("/list4")
    public Result listPage5(@RequestBody QueryPageParam query) {

        Page<guanli> page = new Page();
        page.setCurrent(query.getPageNum());//当前页
        page.setSize(query.getPageSize());//每页记录数

        IPage result= temperaturesService.getgl(page);

        return Result.suc(result.getRecords());

    }



}
