package com.mart.boot.product.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.mart.boot.product.model.vo.ProductVO;

@Mapper
public interface ProductMapper {

	/**
	 * 전체 상품 조회 Mapper
	 * @param
	 * @return List<ProductVO>
	 */
	List<ProductVO> selectList();

	/**
	 * 상품 상세 조회 Mapper
	 * @param pNo
	 * @return ProductVO
	 */
	ProductVO selectOne(int pNo);
}
