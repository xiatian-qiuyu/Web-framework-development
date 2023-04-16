package com.briup.mapper;

import com.briup.bean.Shop;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Mapper
@ResponseBody
public interface IShopMapper {
    void updateSalesVolume(Long shopId,Long salesVolume);
    void updateVisitVolume(Long shopId,Long visitVolume);
//q:怎么实现每次点击商品详情页并且时间超过30，访问量+1
//a: 1.在商品详情页中，每次点击商品详情页，都会访问数据库，查询该商品的访问量
//q:为什么要查询访问量
//a: 1.为了判断是否超过30秒
//   2.为了访问量+1

    Shop findShopById(Long id);

    //通过折扣查询商品，按销量排序
    List<Shop> findByDiscountOrderBySalesVolumeDesc(boolean discount);

    //添加商品到购物车
    void addShopToShopCar(Long shopId,Long userId,int num);

    //根据商品类型查找商品列表
    List<Shop> findByCategoryId(Long categoryId);
    List<Shop> findByNameContaining(String shopName);
    List<Shop> findAllShopByVisitVolumeDesc();
    Shop findShopById(long id);

    List<Shop> findByRecommendOrderBySalesVolumeDesc();
}
