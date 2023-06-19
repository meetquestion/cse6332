package com.bupt.uta.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bupt.uta.entity.People;

import java.util.List;


public interface PeopleService extends IService<People> {

    List<People> getPeopleList();
    int updateByName(People people);
}
