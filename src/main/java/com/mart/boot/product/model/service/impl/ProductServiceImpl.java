package com.mart.boot.product.model.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mart.boot.common.utility.ProductUtil;
import com.mart.boot.product.model.mapper.ProductMapper;
import com.mart.boot.product.model.service.ProductService;
import com.mart.boot.product.model.vo.ProductDetailVO;
import com.mart.boot.product.model.vo.ProductImageVO;
import com.mart.boot.product.model.vo.ProductVO;

@Service
public class ProductServiceImpl implements ProductService {
	
	private final ProductMapper pMapper;
	private final String FILE_PATH = "C:/Users/KDY/Desktop/backend/bootworkspace/FurgayoMART/src/main/resources/static/images/";
	
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
		productDetail.setImgMain(imgMain != null ? imgMain.getOriginalFilename() : null);
		productDetail.setImgCook(imgCook != null ? imgCook.getOriginalFilename() : null);
		productDetail.setImgComponent(imgComponent != null ? imgComponent.getOriginalFilename() : null);
		productDetail.setpComponent(pComponent != null ? pComponent : ""); // 폼에서 전달된 값을 사용
		productDetail.setCook(cook != null ? cook : ""); // 폼에서 전달된 값을 사용
		productDetail.setContent(content != null ? content : ""); // 폼에서 전달된 값을 사용
		productDetail.setCategoryNo(categoryNo);
	    
	    // Product Detail 저장
	    int detailResult = pMapper.addProductDetail(productDetail);
	    
		if(imgMain != null && !imgMain.isEmpty()) {
			ProductUtil.processProductImg(imgMain, product.getpNo(), FILE_PATH, "file:///images/", "MAIN", pMapper);
		}
		
		if (imgCook != null && !imgCook.isEmpty()) {
		    ProductUtil.processProductImg(imgCook, product.getpNo(), FILE_PATH, "file:///images/", "COOK", pMapper);
		}

		if (imgComponent != null && !imgComponent.isEmpty()) {
		    ProductUtil.processProductImg(imgComponent, product.getpNo(), FILE_PATH, "file:///images/", "COMPONENT", pMapper);
		}
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
	    // imgMainFile이 있으면 처리
	    if (productDetail.getImgMainFile() != null && !productDetail.getImgMainFile().isEmpty()) {
	        String imgMainFilename = productDetail.getImgMainFile().getOriginalFilename();
	        productDetail.setImgMain(imgMainFilename);
	    } else {
	        // 기존 이미지가 있으면 그대로 사용, 없으면 noimage 사용
	        productDetail.setImgMain(existDetail.getImgMain() != null ? existDetail.getImgMain() : "noimage.jpg");
	    }

	    // imgCookFile이 있으면 처리
	    if (productDetail.getImgCookFile() != null && !productDetail.getImgCookFile().isEmpty()) {
	        String imgCookFilename = productDetail.getImgCookFile().getOriginalFilename();
	        productDetail.setImgCook(imgCookFilename);
	    } else {
	        productDetail.setImgCook(existDetail.getImgCook() != null ? existDetail.getImgCook() : "noimage.jpg");
	    }

	    // imgComponentFile이 있으면 처리
	    if (productDetail.getImgComponentFile() != null && !productDetail.getImgComponentFile().isEmpty()) {
	        String imgComponentFilename = productDetail.getImgComponentFile().getOriginalFilename();
	        productDetail.setImgComponent(imgComponentFilename);
	    } else {
	        productDetail.setImgComponent(existDetail.getImgComponent() != null ? existDetail.getImgComponent() : "noimage.jpg");
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

}
