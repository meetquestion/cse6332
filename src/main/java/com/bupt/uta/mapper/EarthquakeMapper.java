package com.bupt.uta.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bupt.uta.entity.Earthquake;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface EarthquakeMapper extends BaseMapper<Earthquake> {

    @Select("select * from earthquake order by Rand() limit #{num}")
    List<Earthquake> randomNum(Integer num);
}
