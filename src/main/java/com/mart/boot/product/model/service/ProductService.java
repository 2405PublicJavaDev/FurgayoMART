package com.mart.boot.product.model.service;

import java.util.List;

import com.mart.boot.product.model.vo.ProductVO;

public interface ProductService {

	/**
	 * 전체 상품 조회 Service
	 * @return List<ProductVO>
	 */
	List<ProductVO> selectList();

	/**
	 * 상품 상세 페이지 
	 * @param pNo
	 * @return
	 */
	ProductVO selectOne(int pNo);
	

}
