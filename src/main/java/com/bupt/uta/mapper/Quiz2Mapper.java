package com.bupt.uta.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bupt.uta.entity.Quiz2;
import com.bupt.uta.entity.Quiz2Vo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
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
public interface Quiz2Mapper extends BaseMapper<Quiz2> {
    @Select("SELECT * FROM" +
            "(" +
            "   SELECT * FROM quiz2" +
            "   WHERE status = 1 AND net = #{net}" +
            "     AND mag < #{maxMag} AND mag > #{minMag}" +
            "   ORDER BY mag DESC" +
            "   LIMIT 3" +
            ") AS t1 " +
            "UNION ALL " +
            " SELECT * FROM" +
            "(" +
            "   SELECT * FROM quiz2" +
            "   WHERE status = 1 AND net = #{net}" +
            "     AND mag < #{maxMag} AND mag > #{minMag}" +
            "   ORDER BY mag ASC" +
            "   LIMIT 3" +
            ") AS t2" )
    List<Quiz2> getQuiz2ByMag1(Quiz2Vo quiz2Vo);


    @Select("SELECT * FROM" +
            "(" +
            "   SELECT * FROM quiz2" +
            "   WHERE status = 1 AND net = #{net}" +
            "     AND mag < #{maxMag} AND mag > #{minMag}" +
            "   ORDER BY mag DESC" +
            "   LIMIT 3" +
            ") AS t1 " +
            "UNION ALL " +
            " SELECT * FROM" +
            "(" +
            "   SELECT * FROM quiz2" +
            "   WHERE status = 1 AND net = #{net}" +
            "     AND mag < #{maxMag} AND mag > #{minMag}" +
            "   ORDER BY mag ASC" +
            "   LIMIT 3" +
            ") AS t2")
    List<Quiz2> getQuiz2ByMag(double minMag, double maxMag,String net);

    /*@Select("select * from quiz2 where net = #{net} and mag &gt #{maxMag} and mag &lt #{minMag} ORDER BY mag desc LIMIT 3" +
            " union all select * from quiz2 where net = #{net} and mag &gt #{maxMag} and mag &lt #{minMag} ORDER BY mag asc LIMIT 3 ")
    List<Quiz2> getQuiz2ByMag(String minMag, String maxMag,String net);*/

    @Update("update quiz2 set mag = mag + #{numMag} where  net = #{net} and status = 1  and mag > #{minMag} and mag < #{maxMag}")
    int addMag(Quiz2Vo quiz2Vo);

    @Update("update quiz2 set mag = mag + #{numMag} where  quiz2_time = #{quiz2Time} and status = 0")
    int updateByTime(String quiz2Time,double numMag);
}
