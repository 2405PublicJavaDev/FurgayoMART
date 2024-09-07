package com.mart.boot.product.model.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.mart.boot.product.model.vo.ProductDetailVO;
import com.mart.boot.product.model.vo.ProductVO;

public interface ProductService {

	/**
	 * 관리자_상품 개수 조회 Service
	 * @param searMap
	 * @return int
	 */
	int getTotalCount(Map<String, Object> searMap);

	/**
	 * 관리자_상품 전체 개수 조회 Service
	 * @param pList
	 * @return int
	 */
	int getAllCount(List<ProductVO> pList);
	
	/**
	 * 관리자_상품 조건 검색 Service
	 * @param searchMap
	 * @return List<ProductVO>
	 */
	List<ProductVO> searchProducts(Map<String, Object> searchMap);

	/**
	 * 관리자_전체 상품 조회 Service
	 * @return List<ProductVO>
	 */
	List<ProductVO> selectList();

	/**
	 * 관리자_상품 등록 Service
	 * @param product
	 * @param imgMain
	 * @param imgCook
	 * @param imgComponent
	 * @param pComponent
	 * @param cook
	 * @param content
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	int addProduct(ProductVO product, MultipartFile imgMain, MultipartFile imgCook, MultipartFile imgComponent,
			String pComponent, String cook, String content) throws IllegalStateException, IOException;

	/**
	 * 관리자_상품 기본 및 상세 정보 Service
	 * @param pNo
	 * @return ProductVO
	 */
	ProductVO selectOneWithDetail(Integer pNo);
	/**
	 * 관리자_상품 수정 Service
	 * @param product
	 * @param imgMain
	 * @param imgCook
	 * @param imgComponent
	 * @return int
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	int updateProduct(ProductVO product, ProductDetailVO productDetail, MultipartFile imgMain, MultipartFile imgCook, MultipartFile imgComponent) throws IllegalStateException, IOException;
	
	/**
	 * 관리자_상품 삭제 Service
	 * @param pNo
	 * @return ProductVO
	 */
	int deleteProduct(Integer pNo);


}
