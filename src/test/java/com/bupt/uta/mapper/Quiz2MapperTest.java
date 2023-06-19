package com.bupt.uta.mapper;

import com.bupt.uta.entity.Quiz2;
import com.bupt.uta.entity.Quiz2Vo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <p></p>
 * <p>
 * <PRE>
 * <BR>    修改记录
 * <BR>-----------------------------------------------
 * <BR>    修改日期         修改人          修改内容
 * </PRE>
 *
 * @author jingxp
 * @version 1.0
 * @date Created in 2023年06月17日 10:46
 * @since 1.0
 */
@SpringBootTest
class Quiz2MapperTest {

    @Autowired
    private Quiz2Mapper quiz2Mapper;
    @Test
    void getQuiz2ByMag() {
        Quiz2Vo quiz2Vo = Quiz2Vo.builder().minMag(101.4).maxMag(102).net("ak").build();
        List<Quiz2> ak = quiz2Mapper.getQuiz2ByMag1(quiz2Vo);
        for (Quiz2 item: ak) {
            System.out.println(item);
        }
    }

    @Test
    void addMag() {
        Quiz2Vo quiz2Vo = Quiz2Vo.builder().minMag(1.4).maxMag(2).net("ak").numMag(100).build();
        quiz2Mapper.addMag(quiz2Vo);
    }

    @Test
    void updateByTime() {
        quiz2Mapper.updateByTime("1000005",100);
    }
}
