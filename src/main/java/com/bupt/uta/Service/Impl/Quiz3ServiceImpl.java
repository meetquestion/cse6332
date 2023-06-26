package com.bupt.uta.Service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bupt.uta.Service.Quiz3Service;
import com.bupt.uta.entity.Quiz3;
import com.bupt.uta.entity.Quiz3Vo;
import com.bupt.uta.mapper.Quiz3Mapper;
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
public class Quiz3ServiceImpl extends ServiceImpl<Quiz3Mapper, Quiz3> implements Quiz3Service {

    @Autowired
    private Quiz3Mapper quiz3Mapper;


    @Override
    public List<Quiz3> getQuiz3ByMag(Integer minMag, Integer maxMag) {
        List<Quiz3> list = quiz3Mapper.getQuiz3ByMag(minMag, maxMag);
        return list;
    }

    @Override
    public int addMag(Quiz3Vo quiz2Vo) {
        int i = quiz3Mapper.addMag(quiz2Vo);
        return i;
    }

    @Override
    public List<Quiz3> randomQuery(Integer minNum, Integer maxNum, Integer num) {
        return quiz3Mapper.randomQuery(minNum, maxNum, num);
    }

}
