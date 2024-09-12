package com.mart.boot.product.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.mart.boot.product.model.vo.ProductDetailVO;
import com.mart.boot.product.model.vo.ProductImageVO;
import com.mart.boot.product.model.vo.ProductVO;

@Mapper
public interface ProductMapper {

	/** 
	 * 관리자_상품 이미지 타입 Mapper
	 * @param pNo
	 * @return List<ProductImageVO>
	 */
	List<ProductImageVO> selectProductImageList(Integer pNo);

	/**
	 * 관리자_상품 전체 개수 조회 Mapper
	 * @param pList
	 * @return
	 */
	int getAllCount(List<ProductVO> pList);

	/**
	 * 관리자_상품 조건 개수 조회 Mapper
	 * @param searMap
	 * @return int
	 */
	int getTotalCount(Map<String, Object> searMap);

	/**
	 * 관리자_상품 조건 검색 Mapper
	 * @return
	 */
	List<ProductVO> searchProducts(Map<String, Object> searMap);
	
	/**
	 * 관리자_전체 상품 조회 Mapper
	 * @param
	 * @return List<ProductVO>
	 */
	List<ProductVO> selectList();
	
	/**
	 * 관리자_상품 기본 정보 등록 Mapper
	 * @param inputProduct
	 * @return ProductVO
	 */
	int addProduct(ProductVO product);
	/**
	 * 관리자_상품 상세 정보 등록 Mapper
	 * @param productDetail
	 * @return
	 */
	int addProductDetail(ProductDetailVO productDetail);
	/**
	 * 관리자_상품 이미지 등록 Mapper
	 * @param productImg
	 * @return
	 */
	int addProductFile(ProductImageVO productImg);
	
	/**
	 * 관리자_상품 기본 및 상세 정보 Mapper
	 * @param pNo
	 * @return ProductVO
	 */
	ProductVO selectById(Integer pNo);
	/**
	 * 관리자_상품 기본 정보 수정 Mapper
	 * @param product
	 * @return int
	 */
	int updateProduct(ProductVO product);
	/**
	 * 관리자_상품 상세 정보 수정 Mapper
	 * @param productDetail
	 * @return int
	 */
	int updateProductDetail(ProductDetailVO productDetail);
	
	/**
	 * 관리자_상품 정보 이미지 Mapper
	 * @param getpNo
	 * @return ProductDetailVO
	 */
	ProductDetailVO selectImageById(int getpNo);

	/**
	 * 관리자_상품 삭제 Mapper
	 * @param pNo
	 * @return int
	 */
	int deleteProduct(Integer pNo);
	
	/**
	 * 관리자_상품 이미지 삭제 Mapper
	 * @param pNo
	 * @return int
	 */
	int deleteProductImage(Integer pNo);

	/**
	 * 사용자 상품 페이지
	 * @return List<ProductVO>
	 */
	List<ProductVO> selecpagetList();

	/** 
	 * 사용자 상품 상세 페이지
	 * @param pNo
	 * @return
	 */
	ProductVO selectOne(int pNo);

	/**
	 * 사용자 한식 페이지
	 * @return
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
	 * @param categoryNo
	 * @return
	 */
	List<ProductVO> selectJapaneseFood(int categoryNo);

	/**
	 * 관리자_상품 상세 정보 삭제 Mapper
	 * @param pNo
	 * @return int
	 */
	int deleteProductDetail(Integer pNo);

	/**
	 * 관리자_선택 상품 삭제 Mapper
	 * @param pNo
	 * @return int
	 */
	int selectDelete(Integer pNo);

	/** 
	 * 사용자 인기 페이지
	 * @return List<ProductVO>
	 */
	List<ProductVO> selectPopularList();

	/**
	 * 사용자 신규상품 페이지
	 * @return List<ProductVO>
	 */
	List<ProductVO> selectNewProducts();

	/** 
	 * 사용자 유통기한 페이지
	 * @return List<ProductVO> 
	 */
	List<ProductVO> showExpirationList();

	/**
	 * 메인페이지 상품
	 * @return
	 */
	List<ProductVO> mainNewProducts();

	/**
	 * 구매완료페이지 상품명 조회
	 * @param getpName
	 * @return
	 */
	ProductVO getProductByPname(String getpName);

}
