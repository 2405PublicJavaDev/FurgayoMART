package com.mart.boot.product.model.service;

import java.util.List;

import com.mart.boot.product.model.vo.ProductVO;

public interface ProductService {

	/**
	 * 전체 상품 조회 Service
	 * @return List<ProductVO>
	 */
	List<ProductVO> selectList();
	

}
