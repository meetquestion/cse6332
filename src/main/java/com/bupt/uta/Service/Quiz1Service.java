package com.bupt.uta.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bupt.uta.entity.Quiz1;
import com.bupt.uta.entity.Quiz1Vo;

import java.util.List;

/**
 * @author jingxp
 * @version 1.0
 * @date Created in 2023年06月15日 13:12
 * @since 1.0
 */
public interface Quiz1Service extends IService<Quiz1> {
    List<Quiz1> getQuiz1List();
}
