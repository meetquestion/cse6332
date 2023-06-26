package com.bupt.uta.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.bupt.uta.Service.Quiz3Service;
import com.bupt.uta.common.R;
import com.bupt.uta.entity.Quiz3;
import com.bupt.uta.entity.Quiz3Vo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


/**

 * @version 1.0
 * @date Created in 3033年06月15日 16:03
 * @since 1.0
 */
@Slf4j
@Controller
@RequestMapping("/api/quiz3")
public class Quiz3Controller {

    @Autowired
    private Quiz3Service quiz3Service;

    //通过controller返回html界面
    @RequestMapping("/index")
    public  String indexJumpPage(){
        return "indexquiz3";
    }

    //delete
    @ResponseBody
    @RequestMapping(value = "/delete/{city}",method = RequestMethod.PUT)
    public R<String> deleteQuiz3(@PathVariable String city){
        if(city == null){
            return R.error("error,name is null");
        }
        try {
            LambdaQueryWrapper<Quiz3> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(Quiz3::getCity,city);
            Quiz3 Quiz3 = quiz3Service.getOne(wrapper);
            if(Quiz3 != null){
                UpdateWrapper<Quiz3> updateWrapper = new UpdateWrapper<>();
                updateWrapper.eq("city",city);
                updateWrapper.set("status",0);
                quiz3Service.update(updateWrapper);
            }else{
                return R.error("Quiz3 is not exist!");
            }
        }catch (Exception e){
            return R.error("error");
        }
        return R.success(city);
    }
    //getOne
    @ResponseBody
    @RequestMapping(value = "/queryByTime/{city}",method = RequestMethod.GET)
    public R<List<Quiz3>> select(@PathVariable String city){
        try{
            LambdaQueryWrapper<Quiz3> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(Quiz3::getCity,city)
                    .eq(Quiz3::getStatus,1);
            Quiz3 Quiz3 = quiz3Service.getOne(wrapper);
            List<Quiz3> list = new ArrayList<>();
            list.add(Quiz3);
            return R.success(list);
        }catch (Exception e){
            return R.error("error");
        }
    }
    @ResponseBody
    @RequestMapping(value = "/queryByMag/{minNum}/{maxNum}",method = RequestMethod.GET)
    public R<List<Quiz3>> select(@PathVariable Integer minNum,@PathVariable Integer maxNum){
        try{
            List<Quiz3> list = quiz3Service.getQuiz3ByMag(minNum,maxNum);
            return R.success(list);
        }catch (Exception e){
            return R.error("error");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/randomQuery/{minNum}/{maxNum}/{num}",method = RequestMethod.GET)
    public R<List<Quiz3>> randomQuery(@PathVariable Integer minNum,@PathVariable Integer maxNum,@PathVariable Integer num){
        try{
            List<Quiz3> list = quiz3Service.randomQuery(minNum,maxNum,num);
            return R.success(list);
        }catch (Exception e){
            return R.error("error");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/addMagNum",method = RequestMethod.POST)
    public R<Integer> addMagNum(@RequestBody Quiz3Vo quiz3Vo){
        try{
            int i = quiz3Service.addMag(quiz3Vo);
            /*UpdateWrapper<Quiz3> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("state",quiz3Vo.getState())
                    .eq("status",1)
                    .gt("population",quiz3Vo.getMinNum())
                    .lt("population",quiz3Vo.getMaxNum())
                    .setSql("population = mapopulationg + " + quiz3Vo.getIncNum());
            boolean update = quiz3Service.update(updateWrapper);*/
            return R.success(i);
        }catch (Exception e){
            return R.error("error");
        }
    }

    //add,修改
    /*@ResponseBody
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public R<Quiz3> addToQuiz3(@RequestBody Quiz3 quiz3){
        if(quiz3 == null){
            return R.error("error,cart is null");
        }
        try{
            //如果原来有，修改操作，数量相加
            LambdaQueryWrapper<Quiz3> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(Quiz3::getQuiz3Time, quiz3.getQuiz3Time())
                    .eq(Quiz3::getStatus, 1);
            Quiz3 Quiz = quiz3Service.getOne(lambdaQueryWrapper);
            if(Quiz !=null){
                UpdateWrapper<Quiz3> wrapper = new UpdateWrapper<>();
                wrapper.eq("quiz3_time",quiz3.getQuiz3Time());
                if(quiz3.getLatitude()!= "" && quiz3.getLatitude() !=null){
                    wrapper.set("latitude",quiz3.getLatitude());
                }
                if(quiz3.getLongitude()!= "" && quiz3.getLongitude() != null){
                    wrapper.set("longitude",quiz3.getLongitude());
                }
                if(quiz3.getMag()!= 0){
                    wrapper.set("mag",quiz3.getMag());
                }
                if(quiz3.getNet() !="" && quiz3.getNet() != null){
                    wrapper.set("net",quiz3.getNet());
                }
                if(quiz3.getPlace() !="" && quiz3.getPlace() !=null){
                    wrapper.set("place",quiz3.getPlace());
                }
                quiz3Service.update(wrapper);
                // return new data
                Quiz3 one = quiz3Service.getOne(lambdaQueryWrapper);
                return R.success(one);
            }else{
                //没有，新增
                quiz3.setStatus(1);
                quiz3Service.save(quiz3);
                return R.success(quiz3);
            }
        }catch (Exception e){
            log.error(e.getMessage(), e);
            return R.error("error");
        }
    }*/
}
