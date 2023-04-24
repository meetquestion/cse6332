package com.bupt.uta.Service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bupt.uta.Service.CommunicateService;
import com.bupt.uta.entity.Communicate;
import com.bupt.uta.mapper.CommunicateMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CommunicateServiceImpl extends ServiceImpl<CommunicateMapper, Communicate> implements CommunicateService {

    @Autowired
    private CommunicateMapper communicateMapper;
    @Override
    public List<Communicate> getCommunicateList(Long senderId, Long receiverId) {

        try{
            if(senderId!=null&&receiverId!=null){
                List<Communicate> communicateList = communicateMapper.getCommunicateList(senderId, receiverId);
                List<Communicate> communicateList1 = communicateList.stream().sorted(Comparator.comparing(Communicate::getCreateTime))
                        .collect(Collectors.toList());

                return  communicateList1;
            }else{
                log.info("senderId：{},receiverId:{}",senderId,receiverId);
                return null;
            }
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return null;
    }
}
