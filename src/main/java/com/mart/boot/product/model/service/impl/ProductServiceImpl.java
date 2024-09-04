package com.mart.boot.product.model.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mart.boot.product.model.mapper.ProductMapper;
import com.mart.boot.product.model.service.ProductService;
import com.mart.boot.product.model.vo.ProductVO;

@Service
public class ProductServiceImpl implements ProductService {
	
	private final ProductMapper pMapper;
	
	public ProductServiceImpl(ProductMapper pMapper) {
		this.pMapper = pMapper;
	}
	
	@Override
	public List<ProductVO> selectList() {
		List<ProductVO> pList = pMapper.selectList();
		return pList;
	}

}
