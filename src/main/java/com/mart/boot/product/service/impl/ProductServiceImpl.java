package com.mart.boot.product.service.impl;

import java.util.List;

import com.mart.boot.product.model.vo.ProductVO;
import com.mart.boot.product.service.ProductService;
import com.mart.boot.product.store.ProductStore;

public class ProductServiceImpl implements ProductService {
	
	private ProductStore pStore;

	@Override
	public List<ProductVO> selectList() {
		List<ProductVO> pList = pStore.selectList();
		return pList;
	}

}
