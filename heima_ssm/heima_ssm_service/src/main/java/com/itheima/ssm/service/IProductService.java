package com.itheima.ssm.service;

import com.itheima.ssm.domain.Product;

import java.util.List;

public interface IProductService {

    public  void save(Product product) throws Exception;

//    public List<Product> findAll() throws  Exception;

    public List<Product> findAll(int page,int size) throws  Exception;

}
