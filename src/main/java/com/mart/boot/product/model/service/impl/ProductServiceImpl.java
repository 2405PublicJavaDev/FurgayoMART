package com.mart.boot.product.model.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mart.boot.product.model.mapper.ProductMapper;
import com.mart.boot.product.model.service.ProductService;
import com.mart.boot.product.model.vo.ProductDetailVO;
import com.mart.boot.product.model.vo.ProductImageVO;
import com.mart.boot.product.model.vo.ProductVO;

@Service
public class ProductServiceImpl implements ProductService {
	
	private final ProductMapper pMapper;
	private final String FILE_PATH = "C:/bootworkspace/FurgayoMART/src/main/resources/static/images/";
	
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
	public int getTotalCount(Map<String, Object> searMap) {
		return pMapper.getTotalCount(searMap);
	}
	
	// 관리자_상품 조건 검색
	@Override
	public List<ProductVO> searchProducts(Map<String, Object> searchMap) {
		List<ProductVO> pSearchList = pMapper.searchProducts(searchMap);
		System.out.println("검색 결과: " + pSearchList.size());
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
		productDetail.setPComponent(pComponent != null ? pComponent : ""); // 폼에서 전달된 값을 사용
		productDetail.setCook(cook != null ? cook : ""); // 폼에서 전달된 값을 사용
		productDetail.setContent(content != null ? content : ""); // 폼에서 전달된 값을 사용
		productDetail.setCategoryNo(categoryNo);
	    
	    // Product Detail 저장
	    int detailResult = pMapper.addProductDetail(productDetail);
	    
		if(imgMain != null && !imgMain.isEmpty()) {
			processProductImg(imgMain, product.getpNo(), FILE_PATH, "/images/product/main", "MAIN");
		}
		
		if(imgCook != null && !imgCook.isEmpty()) {
			processProductImg(imgCook, product.getpNo(), FILE_PATH, "/images/product/cook", "COOK");
		}
		
		if(imgCook != null && !imgComponent.isEmpty()) {
			processProductImg(imgComponent, product.getpNo(), FILE_PATH, "/images/product/component", "COMPONENT");
		}
		return result;
	}
	// 관리자_상품 이미지 등록
	private void processProductImg(MultipartFile imgFile, int pNo, String fileSavePath, String dbFilePath, String imgType) throws IllegalStateException, IOException {
		String FileName = imgFile.getOriginalFilename();
		String FileRename = "product" + pNo + ".jpg";
		imgFile.transferTo(new File(fileSavePath + FileRename));
		
		ProductImageVO productImg = new ProductImageVO();
		productImg.setFileName(FileName);
		productImg.setFileRename(FileRename);
		productImg.setFilePath(dbFilePath);
		productImg.setpNo(pNo);
		productImg.setImageType(imgType);
		
		pMapper.addProductFile(productImg);	
	}

	// 관리자_상품 기본 및 상세 정보
	@Override
	public ProductVO selectOneWithDetail(Integer pNo) {
		return pMapper.selectOneWithDetail(pNo);
	}
	// 관리자_상품 정보 수정
	@Override
	public int updateProduct(ProductVO product, ProductDetailVO productDetail , MultipartFile imgMain, MultipartFile imgCook, MultipartFile imgComponent) throws IllegalStateException, IOException {
		int result = pMapper.updateProduct(product);

	    // 이미지 업데이트 처리
	    if (imgMain != null && !imgMain.isEmpty()) {
	        processProductImg(imgMain, product.getpNo(), FILE_PATH, "/images/", FILE_PATH);
	    }
	    if (imgCook != null && !imgCook.isEmpty()) {
	        processProductImg(imgCook, product.getpNo(), FILE_PATH, "/images/", FILE_PATH);
	    }
	    if (imgComponent != null && !imgComponent.isEmpty()) {
	        processProductImg(imgComponent, product.getpNo(), FILE_PATH, "/images/", FILE_PATH);
	    }

	    // 상품 상세 정보 업데이트
	    productDetail.setpNo(product.getpNo());
	    int detailResult = pMapper.updateDetail(productDetail);

	    // 둘 다 성공하면 반환
	    return (result > 0 && detailResult > 0) ? 1 : 0;
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
