package com.bupt.uta.Service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bupt.uta.Service.Quiz1Service;
import com.bupt.uta.entity.Quiz1;
import com.bupt.uta.entity.Quiz1Vo;
import com.bupt.uta.mapper.Quiz1Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p></p>
 * <p>
 * <PRE>
 * </PRE>
 *
 * @author jingxp
 * @version 1.0
 * @date Created in 2023年06月15日 13:14
 * @since 1.0
 */
@Service
public class Quiz1ServiceImpl extends ServiceImpl<Quiz1Mapper, Quiz1> implements Quiz1Service {

    @Autowired
    private Quiz1Mapper quiz1Mapper;

    @Override
    public List<Quiz1> getQuiz1List(){
        List<Quiz1> quiz1List = quiz1Mapper.getQuiz1List();
        return quiz1List;
    }
}
