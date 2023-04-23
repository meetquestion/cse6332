package com.bupt.uta.Service;

import com.bupt.uta.entity.Operation;
import com.bupt.uta.entity.Product;

import java.util.List;

public interface OperationService {
    List<Operation> getAllOperation();
    String addOperation(String operation);
}
