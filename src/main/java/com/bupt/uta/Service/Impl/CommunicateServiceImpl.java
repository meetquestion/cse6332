package com.bupt.uta.Service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bupt.uta.Service.CommunicateService;
import com.bupt.uta.entity.Communicate;
import com.bupt.uta.mapper.CommunicateMapper;
import org.springframework.stereotype.Service;

@Service
public class CommunicateServiceImpl extends ServiceImpl<CommunicateMapper, Communicate> implements CommunicateService {
}
