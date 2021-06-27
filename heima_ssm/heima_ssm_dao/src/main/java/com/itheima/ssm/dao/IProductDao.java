package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IProductDao {



    @Insert("insert into product(productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus)" +
            "value(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice}," +
            "#{productDesc},#{productStatus})")
    public  void save(Product product);


    //根据id查询产品
    @Select("Select * from product where id=#{id}")
    public Product findById(Integer id)throws Exception;

    @Select("select * from product")
    public List<Product> findAll() throws Exception;

}
