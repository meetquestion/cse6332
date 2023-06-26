package com.bupt.uta.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bupt.uta.entity.Quiz3;
import com.bupt.uta.entity.Quiz3Vo;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


/**
 * @author jingxp
 * @version 1.0
 * @date Created in 2023年06月15日 13:12
 * @since 1.0
 */
public interface Quiz3Service extends IService<Quiz3> {
    List<Quiz3> getQuiz3ByMag(Integer minNum, Integer maxNum);

    int addMag(Quiz3Vo quiz3Vo);

    List<Quiz3> randomQuery(Integer minNum,  Integer maxNum,  Integer num);
}
