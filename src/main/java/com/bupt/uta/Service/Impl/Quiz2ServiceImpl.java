package com.bupt.uta.Service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bupt.uta.Service.Quiz1Service;
import com.bupt.uta.Service.Quiz2Service;
import com.bupt.uta.entity.Quiz1;
import com.bupt.uta.entity.Quiz2;
import com.bupt.uta.entity.Quiz2Vo;
import com.bupt.uta.mapper.Quiz1Mapper;
import com.bupt.uta.mapper.Quiz2Mapper;
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
public class Quiz2ServiceImpl extends ServiceImpl<Quiz2Mapper, Quiz2> implements Quiz2Service {

    @Autowired
    private Quiz2Mapper quiz2Mapper;


    @Override
    public List<Quiz2> getQuiz2ByMag(double minMag, double maxMag, String net) {
        List<Quiz2> list = quiz2Mapper.getQuiz2ByMag(minMag, maxMag, net);
        return list;
    }

    @Override
    public int addMag(Quiz2Vo quiz2Vo) {
        int i = quiz2Mapper.addMag(quiz2Vo);
        return i;
    }

}
