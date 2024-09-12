package com.mart.boot.purchase.model.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.mart.boot.product.model.vo.ProductVO;
import com.mart.boot.purchase.model.vo.PurchaseVO;

@Mapper
public interface PurchaseMapper {

	ProductVO selectProductByPNo(int pNo);

	void insertPurchase(PurchaseVO purchaseVO);

}
