package com.itheima.ssm.service;

import com.itheima.ssm.domain.Orders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IOrdersService {

    //订单详情查询
    public Orders findById(Integer ordersId)throws Exception;

//    public List<Orders> findAll() throws Exception;

    public List<Orders> findAll(int page,int size) throws Exception;

}
