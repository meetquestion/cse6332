package com.bupt.uta.Service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.bupt.uta.entity.Quiz2;
import com.bupt.uta.entity.Quiz2Vo;

import java.util.List;


/**
 * @author jingxp
 * @version 1.0
 * @date Created in 2023年06月15日 13:12
 * @since 1.0
 */
public interface Quiz2Service extends IService<Quiz2> {
    List<Quiz2> getQuiz2ByMag(double minMag, double maxMag, String net);

    int addMag(Quiz2Vo quiz2Vo);
}
