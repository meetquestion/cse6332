package com.bupt.uta.Service.Impl;

import com.bupt.uta.Service.OperationService;
import com.bupt.uta.entity.Operation;
import com.bupt.uta.entity.Product;
import com.bupt.uta.mapper.OperationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class OperationServiceImpl implements OperationService {
    @Autowired
    OperationMapper operationMapper;

    @Override
    public List<Operation> getAllOperation(){
        List<Operation> operationList = operationMapper.selectList(null);
        return operationList;
    }

    @Override
    public String addOperation(String operation){
        Operation o=new Operation();
        o.setOperation(operation);
        Date date = new Date();
//        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        o.setTime(date);
        try {
            operationMapper.insert(o);
        }catch (Exception e){
            return "Some error happen";
        }
        return "Insert successfully";
    }
}
