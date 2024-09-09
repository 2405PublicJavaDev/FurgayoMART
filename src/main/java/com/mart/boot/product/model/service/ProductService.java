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
	int getTotalCount(Map<String, Object> searchMap);

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
	 * 관리자_상품 기본 정보 수정 Service
	 * @param product
	 * @return int
	 */
	int updateProduct(ProductVO product);
	
	/**
	 * 관리자_상품 상세 정보 수정 Service
	 * @param productDetail
	 * @param imgComponent 
	 * @param imgCook 
	 * @param imgMain 
	 * @return
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	int updateProductDetail(ProductDetailVO productDetail, MultipartFile imgMain, MultipartFile imgCook, MultipartFile imgComponent) throws IllegalStateException, IOException;
	
	/**
	 * 관리자_상품 기본 정보 Service
	 * @param pNo
	 * @return
	 */
	ProductVO selectById(Integer pNo);

	/**
	 * 관리자_상품 삭제 Service
	 * @param pNo
	 * @return ProductVO
	 */
	int deleteProduct(Integer pNo);

	
	/**
	 * 상품 리스트 조회
	 * @param pNo
	 * @return ProductVO
	 */
	ProductVO selectOne(int pNo);

	/**
	 * 상품 품목 리스트
	 * @return List<ProductVO>
	 */
	List<ProductVO> selecpagetList();

	/** 
	 * 사용자 한식 페이지
	 * @return List<ProductVO>
	 */
	List<ProductVO> selectKoreanFood(int categoryNo);

	/**
	 * 사용자 중식 페이지
	 * @param categoryNo
	 * @return
	 */
	List<ProductVO> selectChinesesFood(int categoryNo);

	/**
	 * 사용자 일식 페이지
	 * @param i
	 * @return List<ProductVO>
	 */
	List<ProductVO> selectJapaneseFood(int categoryNo);

}
