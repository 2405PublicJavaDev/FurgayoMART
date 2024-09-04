package com.mart.boot.product.store.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.mart.boot.product.model.vo.ProductVO;
import com.mart.boot.product.store.ProductStore;

public class ProductStoreImpl implements ProductStore{
	
	private SqlSession session;

	@Override
	public List<ProductVO> selectList() {
		List<ProductVO> pList = session.selectList("ProductMapper.selectList");
		return pList;
	}

}
