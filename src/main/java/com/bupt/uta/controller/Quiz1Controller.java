package com.bupt.uta.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.bupt.uta.Service.Quiz1Service;
import com.bupt.uta.common.R;
import com.bupt.uta.entity.Quiz1;
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
@RequestMapping("/api")
public class Quiz1Controller {

    @Autowired
    private Quiz1Service quiz1Service;

    //通过controller返回html界面
    @RequestMapping("/index")
    public  String indexJumpPage(){
        return "indexquiz1";
    }

    //getList
    @ResponseBody
    @RequestMapping(value = "/quiz1List",method = RequestMethod.GET)
    public R<List<Quiz1>> getQuizList(){
        try{
            List<Quiz1> quiz1List = quiz1Service.getQuiz1List();
            return R.success(quiz1List);
        }catch (Exception e){
            return R.error("error");
        }
    }
    //delete
    @ResponseBody
    @RequestMapping(value = "/quiz1/delete/{peopleName}",method = RequestMethod.PUT)
    public R<String> deleteQuiz1(@PathVariable String peopleName){
        if(peopleName == null){
            return R.error("error,name is null");
        }
        try {
            LambdaQueryWrapper<Quiz1> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(Quiz1::getPeopleName,peopleName);
            Quiz1 quiz1 = quiz1Service.getOne(wrapper);
            if(quiz1 != null){
                UpdateWrapper<Quiz1> updateWrapper = new UpdateWrapper<>();
                updateWrapper.eq("people_name",peopleName);
                updateWrapper.set("status",0);
                quiz1Service.update(updateWrapper);
            }else{
                return R.error("Quiz1 is not exist!");
            }
        }catch (Exception e){
            return R.error("error");
        }
        return R.success(peopleName);
    }
    //getOne
    @ResponseBody
    @RequestMapping(value = "/quiz1/queryByName/{peopleName}",method = RequestMethod.GET)
    public R<List<Quiz1>> select(@PathVariable String peopleName){
        try{
            LambdaQueryWrapper<Quiz1> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(Quiz1::getPeopleName,peopleName)
                    .eq(Quiz1::getStatus,1);
            Quiz1 quiz1 = quiz1Service.getOne(wrapper);
            List<Quiz1> list = new ArrayList<>();
            list.add(quiz1);
            return R.success(list);
        }catch (Exception e){
            return R.error("error");
        }
    }
    @ResponseBody
    @RequestMapping(value = "/quiz1/queryById/{startId}/{endId}",method = RequestMethod.GET)
    public R<List<Quiz1>> select(@PathVariable Integer startId,@PathVariable Integer endId){
        try{
            LambdaQueryWrapper<Quiz1> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(Quiz1::getStatus,1)
                    .gt(Quiz1::getId,startId)
                    .lt(Quiz1::getId,endId);
            List<Quiz1> list = quiz1Service.list(wrapper);
            return R.success(list);
        }catch (Exception e){
            return R.error("error");
        }
    }
    @ResponseBody
    @RequestMapping(value = "/quiz1/queryByGrade/{startGrade}/{endGrade}",method = RequestMethod.GET)
    public R<List<Quiz1>> selectByGrade(@PathVariable Integer startGrade,@PathVariable Integer endGrade){
        try{
            LambdaQueryWrapper<Quiz1> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(Quiz1::getStatus,1)
                    .gt(Quiz1::getGrade,startGrade)
                    .lt(Quiz1::getGrade,endGrade);
            List<Quiz1> list = quiz1Service.list(wrapper);
            return R.success(list);
        }catch (Exception e){
            return R.error("error");
        }
    }
    //add,修改
    @ResponseBody
    @RequestMapping(value = "/quiz1/save",method = RequestMethod.POST)
    public R<Quiz1> addToQuiz1(@RequestBody Quiz1 quiz1){
        if(quiz1 == null){
            return R.error("error,cart is null");
        }
        try{
            //如果原来有，修改操作，数量相加
            LambdaQueryWrapper<Quiz1> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(Quiz1::getPeopleName, quiz1.getPeopleName())
                    .eq(Quiz1::getStatus, 1);
            Quiz1 quiz = quiz1Service.getOne(lambdaQueryWrapper);
            if(quiz !=null){
                UpdateWrapper<Quiz1> wrapper = new UpdateWrapper<>();
                wrapper.eq("people_name",quiz1.getPeopleName());
                if(quiz1.getId() !=0){
                    wrapper.set("id",quiz1.getId());
                }
                if(quiz1.getGrade()!=0){
                    wrapper.set("grade",quiz1.getGrade());
                }
                if(quiz1.getNotes()!=null && quiz1.getNotes()!=" "){
                    wrapper.set("notes",quiz1.getNotes());
                }
                quiz1Service.update(wrapper);
                return R.success(quiz);
            }else{
                //没有，新增
                quiz1.setStatus(1);
                quiz1Service.save(quiz1);
                return R.success(quiz1);
            }
        }catch (Exception e){
            log.error(e.getMessage(), e);
            return R.error("error");
        }
    }
}
