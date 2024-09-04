package com.mart.boot.product.store;

import java.util.List;

import com.mart.boot.product.model.vo.ProductVO;

public interface ProductStore {

	/**
	 * 전체 상품 조회 Store
	 * @return List<ProductVO>
	 */
	List<ProductVO> selectList();

}
