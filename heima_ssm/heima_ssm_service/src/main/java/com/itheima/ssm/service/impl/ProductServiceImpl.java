package com.itheima.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.itheima.ssm.dao.IProductDao;
import com.itheima.ssm.domain.Product;
import com.itheima.ssm.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements IProductService {


    @Autowired
    private IProductDao productDao;

    @Override
    public void save(Product product) throws Exception {
        productDao.save(product);
    }

//    @Override
//    public List<Product> findAll() throws Exception {
//        return productDao.findAll();

    @Override
    public List<Product> findAll(int page ,int size) throws Exception {
        //参数pageNum是页码值 参数pageSize代表是每页显示条数
        PageHelper.startPage(page,size);
        return productDao.findAll();

    }
}
