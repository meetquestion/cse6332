package com.bupt.uta.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.bupt.uta.Service.Quiz2Service;
import com.bupt.uta.common.R;
import com.bupt.uta.entity.Quiz2;
import com.bupt.uta.entity.Quiz2Vo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


/**

 * @version 1.0
 * @date Created in 2023年06月15日 16:03
 * @since 1.0
 */
@Slf4j
@Controller
@RequestMapping("/api/quiz2")
public class Quiz2Controller {

    @Autowired
    private Quiz2Service quiz2Service;

    //通过controller返回html界面
    @RequestMapping("/index")
    public  String indexJumpPage(){
        return "indexquiz2";
    }

    @RequestMapping("/test")
    public  String indexTest(){
        return "index_test";
    }


    //delete
    @ResponseBody
    @RequestMapping(value = "/delete/{quiz2Time}",method = RequestMethod.PUT)
    public R<String> deleteQuiz2(@PathVariable String quiz2Time){
        if(quiz2Time == null){
            return R.error("error,name is null");
        }
        try {
            LambdaQueryWrapper<Quiz2> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(Quiz2::getQuiz2Time,quiz2Time);
            Quiz2 Quiz2 = quiz2Service.getOne(wrapper);
            if(Quiz2 != null){
                UpdateWrapper<Quiz2> updateWrapper = new UpdateWrapper<>();
                updateWrapper.eq("Quiz2_time",quiz2Time);
                updateWrapper.set("status",0);
                quiz2Service.update(updateWrapper);
            }else{
                return R.error("Quiz2 is not exist!");
            }
        }catch (Exception e){
            return R.error("error");
        }
        return R.success(quiz2Time);
    }
    //getOne
    @ResponseBody
    @RequestMapping(value = "/queryByTime/{quiz2Time}",method = RequestMethod.GET)
    public R<List<Quiz2>> select(@PathVariable String quiz2Time){
        try{
            LambdaQueryWrapper<Quiz2> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(Quiz2::getQuiz2Time,quiz2Time)
                    .eq(Quiz2::getStatus,1);
            Quiz2 Quiz2 = quiz2Service.getOne(wrapper);
            List<Quiz2> list = new ArrayList<>();
            list.add(Quiz2);
            return R.success(list);
        }catch (Exception e){
            return R.error("error");
        }
    }
    @ResponseBody
    @RequestMapping(value = "/queryByMag/{minMag}/{maxMag}/{net}",method = RequestMethod.GET)
    public R<List<Quiz2>> select(@PathVariable double minMag,@PathVariable double maxMag,@PathVariable String net){
        try{
            List<Quiz2> list = quiz2Service.getQuiz2ByMag(minMag,maxMag,net);
            return R.success(list);
        }catch (Exception e){
            return R.error("error");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/addMagNum",method = RequestMethod.POST)
    public R<Integer> addMagNum(@RequestBody Quiz2Vo quiz2Vo){
        try{
            int i = quiz2Service.addMag(quiz2Vo);
            /*UpdateWrapper<Quiz2> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("net",quiz2Vo.getNet())
                    .eq("status",1)
                    .gt("mag",quiz2Vo.getMinMag())
                    .lt("mag",quiz2Vo.getMaxMag())
                    .setSql("mag = mag + " + quiz2Vo.getNumMag());
            boolean update = quiz2Service.update(updateWrapper);*/
            return R.success(i);
        }catch (Exception e){
            return R.error("error");
        }
    }

    //add,修改
    @ResponseBody
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public R<Quiz2> addToQuiz2(@RequestBody Quiz2 quiz2){
        if(quiz2 == null){
            return R.error("error,cart is null");
        }
        try{
            //如果原来有，修改操作，数量相加
            LambdaQueryWrapper<Quiz2> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(Quiz2::getQuiz2Time, quiz2.getQuiz2Time())
                    .eq(Quiz2::getStatus, 1);
            Quiz2 Quiz = quiz2Service.getOne(lambdaQueryWrapper);
            if(Quiz !=null){
                UpdateWrapper<Quiz2> wrapper = new UpdateWrapper<>();
                wrapper.eq("quiz2_time",quiz2.getQuiz2Time());
                if(quiz2.getLatitude()!= "" && quiz2.getLatitude() !=null){
                    wrapper.set("latitude",quiz2.getLatitude());
                }
                if(quiz2.getLongitude()!= "" && quiz2.getLongitude() != null){
                    wrapper.set("longitude",quiz2.getLongitude());
                }
                if(quiz2.getMag()!= 0){
                    wrapper.set("mag",quiz2.getMag());
                }
                if(quiz2.getNet() !="" && quiz2.getNet() != null){
                    wrapper.set("net",quiz2.getNet());
                }
                if(quiz2.getPlace() !="" && quiz2.getPlace() !=null){
                    wrapper.set("place",quiz2.getPlace());
                }
                quiz2Service.update(wrapper);
                // return new data
                Quiz2 one = quiz2Service.getOne(lambdaQueryWrapper);
                return R.success(one);
            }else{
                //没有，新增
                quiz2.setStatus(1);
                quiz2Service.save(quiz2);
                return R.success(quiz2);
            }
        }catch (Exception e){
            log.error(e.getMessage(), e);
            return R.error("error");
        }
    }
}
