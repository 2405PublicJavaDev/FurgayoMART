package com.mart.boot.product.model.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.mart.boot.product.model.vo.ProductDetailVO;
import com.mart.boot.product.model.vo.ProductImageVO;
import com.mart.boot.product.model.vo.ProductVO;

public interface ProductService {

	/**
	 * 관리자_상품 이미지 타입 Service
	 * @param pNo
	 * @param string
	 * @return List<ProductImageVO>
	 */
	List<ProductImageVO> selectProductImageList(Integer pNo);
	
	/**
	 * 관리자_상품 개수 조회 Service
	 * @param searMap
	 * @return int
	 */
	int getTotalCount(Map<String, Object> searchMap);

	/**
	 * 관리자_상품 전체 개수 조회 Service
	 * @param 
	 * @return int
	 */
	int getAllCount();
	
	/**
	 * 관리자_상품 조건 검색 Service
	 * @param searchMap
	 * @return List<ProductVO>
	 */
	List<ProductVO> searchProducts(Map<String, Object> searchMap);

	/**
	 * 관리자_전체 상품 조회 Service
	 * @param prsnPageNo
	 * @param cntntPerPage
	 * @return
	 */
	List<ProductVO> selectList(int prsnPageNo, int cntntPerPage);

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
	 * @return int
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	int updateProductDetail(ProductDetailVO productDetail) throws IllegalStateException, IOException;
	
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

	/**
	 * 사용자 인기 페이지
	 * @return List<ProductVO>
	 */
	List<ProductVO> selectPopularList();

	/** 
	 * 사용자 신규 페이지
	 * @return List<ProductVO>
	 */
	List<ProductVO> selectNewProducts();

	/**
	 * 사용자 유통기한 페이지
	 * @return
	 */
	List<ProductVO> showExpirationList();

	/**
	 * 메인페이지 신규상품
	 * @return
	 */
	List<ProductVO> mainNewProducts();

	
	/**
	 * 사용자 상세페이지
	 * @param pNo
	 * @return ProductVO
	 */
	ProductVO getProductWithDetail(int pNo);

}


