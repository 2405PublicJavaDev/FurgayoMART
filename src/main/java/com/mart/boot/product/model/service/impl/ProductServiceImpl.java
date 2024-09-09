package com.mart.boot.product.model.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mart.boot.common.utility.ProductUtil;
import com.mart.boot.product.model.mapper.ProductMapper;
import com.mart.boot.product.model.service.ProductService;
import com.mart.boot.product.model.vo.ProductDetailVO;
import com.mart.boot.product.model.vo.ProductVO;


@Service
public class ProductServiceImpl implements ProductService {
	
	private final ProductMapper pMapper;
	private final String FILE_PATH_MAIN = "C:/Users/KDY/Desktop/backend/bootworkspace/FurgayoMART/src/main/resources/static/images/main/";
	private final String FILE_PATH_COOK = "C:/Users/KDY/Desktop/backend/bootworkspace/FurgayoMART/src/main/resources/static/images/cook/";
	private final String FILE_PATH_COMPONENT = "C:/Users/KDY/Desktop/backend/bootworkspace/FurgayoMART/src/main/resources/static/images/component/";
	
	
	public ProductServiceImpl(ProductMapper pMapper) {
		this.pMapper = pMapper;
	}
	
	// 관리자_상품 전체 개수
	@Override
	public int getAllCount(List<ProductVO> pList) {
		return pMapper.getAllCount(pList);
	}

	// 관리자_상품 개수
	@Override
	public int getTotalCount(Map<String, Object> searchMap) {
		return pMapper.getTotalCount(searchMap);
	}
	
	// 관리자_상품 조건 검색
	@Override
	public List<ProductVO> searchProducts(Map<String, Object> searchMap) {
		List<ProductVO> pSearchList = pMapper.searchProducts(searchMap);
		return pSearchList;
	}

	// 관리자_전체 상품 조회
	@Override
	public List<ProductVO> selectList() {
		List<ProductVO> pList = pMapper.selectList();
		return pList;
	}

	// 관리자_상품 등록
	@Override
	public int addProduct(ProductVO product, MultipartFile imgMain, MultipartFile imgCook, MultipartFile imgComponent, String pComponent, String cook, String content) throws IllegalStateException, IOException {
		int result = pMapper.addProduct(product);
		int pNo = product.getpNo();
		int categoryNo = product.getCategoryNo();
		
		ProductDetailVO productDetail = new ProductDetailVO();
		productDetail.setpNo(pNo);
		productDetail.setpComponent(pComponent != null ? pComponent : ""); // 폼에서 전달된 값을 사용
		productDetail.setCook(cook != null ? cook : ""); // 폼에서 전달된 값을 사용
		productDetail.setContent(content != null ? content : ""); // 폼에서 전달된 값을 사용
		productDetail.setCategoryNo(categoryNo);
	    
		if (imgMain != null && !imgMain.isEmpty()) {
			String imgMainFileName = ProductUtil.processProductImg(imgMain, pNo, FILE_PATH_MAIN, "MAIN", pMapper);
			productDetail.setImgMain(imgMainFileName != null ? imgMainFileName : null);
		}
		
		if (imgCook != null && !imgCook.isEmpty()) {
			String imgCookFileName = ProductUtil.processProductImg(imgCook, pNo, FILE_PATH_COOK, "COOK", pMapper);
			productDetail.setImgCook(imgCookFileName != null ? imgCookFileName : null);
		}
		
		if (imgComponent != null && !imgComponent.isEmpty()) {
			String imgComponentFileName = ProductUtil.processProductImg(imgComponent, pNo, FILE_PATH_COMPONENT, "COMPONENT", pMapper);
			productDetail.setImgComponent(imgComponentFileName != null ? imgComponentFileName : null);
		}
	    
		// Product Detail 저장
	    int detailResult = pMapper.addProductDetail(productDetail);
	    
		return result;
	}

	// 관리자_상품 기본 정보
	@Override
	public ProductVO selectById(Integer pNo) {
		return pMapper.selectById(pNo);
	}

	// 관리자_상품 기본 정보 수정
	@Override
	public int updateProduct(ProductVO product) {
		
		return pMapper.updateProduct(product);
	}
	// 관리자_상품 상세 정보 수정
	@Override
	public int updateProductDetail(ProductDetailVO productDetail) throws IllegalStateException, IOException {
		ProductDetailVO existDetail = pMapper.selectImageById(productDetail.getpNo());

	    // imgMainFile 처리
	    if (productDetail.getImgMainFile() != null && !productDetail.getImgMainFile().isEmpty()) {
	        String imgMainFilename = ProductUtil.processProductImg(productDetail.getImgMainFile(), productDetail.getpNo(), FILE_PATH_MAIN, "MAIN", pMapper);
	        productDetail.setImgMain(imgMainFilename);  // DB에 저장할 파일명 설정
	    } else {
	        productDetail.setImgMain(existDetail.getImgMain());
	    }

	    // imgCookFile 처리
	    if (productDetail.getImgCookFile() != null && !productDetail.getImgCookFile().isEmpty()) {
	        String imgCookFilename = ProductUtil.processProductImg(productDetail.getImgCookFile(), productDetail.getpNo(), FILE_PATH_COOK, "COOK", pMapper);
	        productDetail.setImgCook(imgCookFilename);  // DB에 저장할 파일명 설정
	    } else {
	        productDetail.setImgCook(existDetail.getImgCook());
	    }

	    // imgComponentFile 처리
	    if (productDetail.getImgComponentFile() != null && !productDetail.getImgComponentFile().isEmpty()) {
	        String imgComponentFilename = ProductUtil.processProductImg(productDetail.getImgComponentFile(), productDetail.getpNo(), FILE_PATH_COMPONENT, "COMPONENT", pMapper);
	        productDetail.setImgComponent(imgComponentFilename);  // DB에 저장할 파일명 설정
	    } else {
	        productDetail.setImgComponent(existDetail.getImgComponent());
	    }
	    
	    return pMapper.updateProductDetail(productDetail);
	}


	// 관리자_상품 삭제
	@Override
	public int deleteProduct(Integer pNo) {
		pMapper.deleteProductImage(pNo);
		pMapper.deleteProductDetail(pNo);
		int result = pMapper.deleteProduct(pNo);
		return result;
	}

	// 사용자 상품 상세 페이지 
	@Override
	public ProductVO selectOne(int pNo) {
		ProductVO product = pMapper.selectOne(pNo);
		return product;
	}

	// 사용자 상품 페이지
	@Override
	public List<ProductVO> selecpagetList() {
		List<ProductVO> pList = pMapper.selecpagetList();
		return pList;
	}

	// 사용자 카테고리별 상품 페이지
	@Override
	public List<ProductVO> selectKoreanFood(int categoryNo) {
	    List<ProductVO> pList = pMapper.selectKoreanFood(categoryNo);
	    return pList;
	}

	@Override
	public List<ProductVO> selectChinesesFood(int categoryNo) {
		List<ProductVO> pList = pMapper.selectChinesesFood(categoryNo);
		return pList;
	}

	@Override
	public List<ProductVO> selectJapaneseFood(int categoryNo) {
		List<ProductVO> pList = pMapper.selectJapaneseFood(categoryNo);
		return pList;
	}

}
