package com.bupt.uta.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bupt.uta.entity.People;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

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
 * @date Created in 2023年06月13日 12:06
 * @since 1.0
 */
@Mapper
@Repository
public interface PeopleMapper extends BaseMapper<People> {

    @Select("select * from people where status = 1")
    List<People> getPeopleList();
}
