package com.bupt.uta.Service.Impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bupt.uta.Service.CartService;
import com.bupt.uta.Service.PeopleService;
import com.bupt.uta.entity.Cart;
import com.bupt.uta.entity.CartVo;
import com.bupt.uta.entity.People;
import com.bupt.uta.mapper.CartMapper;
import com.bupt.uta.mapper.PeopleMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PeopleServiceImpl extends ServiceImpl<PeopleMapper, People> implements PeopleService {

    @Autowired
    private PeopleMapper peopleMapper;

    @Override
    public List<People> getPeopleList() {
        List<People> peopleList = peopleMapper.getPeopleList();
        return peopleList;
    }

    @Override
    public int updateByName(People people) {
        int i = 0;
        try{
            i = peopleMapper.updateById(people);
        }catch (Exception e){
            log.error("error",e);
        }
        return i;
    }

}
