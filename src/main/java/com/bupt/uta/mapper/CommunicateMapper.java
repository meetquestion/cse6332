package com.bupt.uta.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bupt.uta.entity.Communicate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommunicateMapper extends BaseMapper<Communicate> {

    @Select({
            "<script>",
            "select * " +
                    "from communicate where sender_id = #{senderId} and receiver_id = #{receiverId}" ,
            "union all " +
                    " select * " +
                    "from communicate where sender_id = #{receiverId} and receiver_id = #{senderId}",
            "</script>"
    })
    List<Communicate> getCommunicateList(Long senderId,Long receiverId);
}
