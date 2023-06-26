package com.bupt.uta.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.bupt.uta.Service.EarthquakeService;
import com.bupt.uta.common.R;
import com.bupt.uta.entity.Earthquake;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Slf4j
@Controller
@RequestMapping("/api/earthquake")
public class EarthquakeController {
    @Autowired
    private EarthquakeService earthquakeService;

    //通过controller返回html界面
    @RequestMapping("/index")
    public  String indexJumpPage(){
        return "earthquake";
    }

    @ResponseBody
    @RequestMapping(value = "/greather5/{num}",method = RequestMethod.GET)
    public R<Integer> greather5(@PathVariable String num){
        try {
            LambdaQueryWrapper<Earthquake> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(Earthquake::getQuakeStatus,1)
                    .gt(Earthquake::getMag,num);
            int count = earthquakeService.count(wrapper);
            return R.success(count);
        } catch (Exception e) {
            log.error("e:",e.getMessage());
            return R.error("error");
        }
    }

    /*@ResponseBody
    @RequestMapping(value = "/searchForRange",method = RequestMethod.GET)
    public List<Map<Integer,Integer>> searchForRange(){

    }*/

    @ResponseBody
    @RequestMapping(value = "/randomNum/{num}",method = RequestMethod.GET)
    public R<List<Earthquake>> randomNum(@PathVariable Integer num){
        if( num > 1000){
            return R.error("The number of queries can't exceed 1000");
        }
        List<Earthquake> list = new ArrayList<>();
        try {
            list = earthquakeService.randomNum(num);
        } catch (Exception e) {
            log.error("e:",e.getMessage());
           R.error("error");
        }
        return R.success(list);
    }
}
