package com.bupt.uta.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import com.bupt.uta.Service.PeopleService;
import com.bupt.uta.common.R;
import com.bupt.uta.entity.People;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.PriorityQueue;

@Slf4j
@Controller
@RequestMapping("/api")
public class PeopleController {

    @Autowired
    private PeopleService peopleService;
    //通过controller返回html界面
    @RequestMapping("/indexA1")
    public  String indexJumpPage(){
        return "index1";
    }

    @ResponseBody
    @RequestMapping(value = "/peopleList",method = RequestMethod.GET)
    public R<List<People>> getPeopleList() {
        try{

            //PriorityQueue<Integer> q = new PriorityQueue<>((o1, o2)->o2.compareTo(o1));
            List<People> cartVoList = peopleService.getPeopleList();
            return R.success(cartVoList);
        }catch(Exception e){
            return R.error("error");
        }
    }
    //add to cart
    @RequestMapping(value = "/people/save",method = RequestMethod.POST)
    public R<People> addToCart(@RequestBody People people){
        if(people == null){
            return R.error("error,cart is null");
        }
        try{
            //如果原来有，修改操作，数量相加
            LambdaQueryWrapper<People> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(People::getPeopleName, people.getPeopleName())
                    .eq(People::getStatus, people.getStatus());
            People people1 = peopleService.getOne(lambdaQueryWrapper);
            if(people1 !=null){
                people1.setPicture(people.getPicture());
                peopleService.updateByName(people1);
                return R.success(people1);
            }else{
                //没有，新增
                peopleService.save(people);
                return R.success(people);
            }
        }catch (Exception e){
            log.error(e.getMessage(), e);
            return R.error("error");
        }
    }

    //delete to cart
    @RequestMapping(value = "/people/put/{peopleName}",method = RequestMethod.PUT)
    public R<String> deleteToCart(@PathVariable String peopleName) {
        if(peopleName == null){
            return R.error("error,name is null");
        }
        try {
            LambdaQueryWrapper<People> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(People::getPeopleName, peopleName);
            People people = peopleService.getOne(lambdaQueryWrapper);
            if(people!=null){
                //删除
                people.setStatus(0);
                peopleService.updateById(people);
            }else{
                return R.error("Cart is not exist!");
            }
        }catch(Exception e){
            return R.error("error");
        }
        return R.success(peopleName);
    }
}
