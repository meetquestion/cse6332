package com.bupt.uta.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bupt.uta.entity.Communicate;

import java.util.List;

public interface CommunicateService extends IService<Communicate> {

    List<Communicate> getCommunicateList(Long senderId, Long receiverId);
}
