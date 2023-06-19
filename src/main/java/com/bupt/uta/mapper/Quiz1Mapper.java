package com.bupt.uta.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bupt.uta.entity.Quiz1;
import com.bupt.uta.entity.Quiz1Vo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p></p>
 * <p>
 * <PRE>
 *
 * @author jingxp
 * @version 1.0
 * @since 1.0
 */
@Mapper
@Repository
public interface Quiz1Mapper extends BaseMapper<Quiz1> {
    @Select("select * from quiz1 where status = 1")
    List<Quiz1> getQuiz1List();

    @Select({
            "select * from quiz1" +
            " where status = 1 " +
                    "AND id &gt; #{startId} " +
                    "AND id &lt; #{endId}"
    })
    List<Quiz1> queryQuiz1ById(Quiz1Vo quiz1);

    @Select({
            "select * from quiz1" +
                    " where status = 1 " +
                    "AND grade &gt; #{startGrade} " +
                    "AND grade &lt; #{endGrade}"
    })
    List<Quiz1> queryQuiz1ByGrade(Quiz1Vo quiz1);
}
