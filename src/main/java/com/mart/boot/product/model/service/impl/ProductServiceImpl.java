package com.mart.boot.product.model.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mart.boot.common.utility.Util;
import com.mart.boot.product.model.mapper.ProductMapper;
import com.mart.boot.product.model.service.ProductService;
import com.mart.boot.product.model.vo.ProductDetailVO;
import com.mart.boot.product.model.vo.ProductImageVO;
import com.mart.boot.product.model.vo.ProductVO;

@Service
public class ProductServiceImpl implements ProductService {
	
	private final ProductMapper pMapper;
	private final String FILE_PATH = "C:/FurgayoMART/src/main/resources/static/images/";
	
	public ProductServiceImpl(ProductMapper pMapper) {
		this.pMapper = pMapper;
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
		
		ProductDetailVO productDetail = new ProductDetailVO();
		productDetail.setpNo(pNo);
		productDetail.setImgMain(imgMain != null ? imgMain.getOriginalFilename() : null);
		productDetail.setImgCook(imgCook != null ? imgCook.getOriginalFilename() : null);
		productDetail.setImgComponent(imgComponent != null ? imgComponent.getOriginalFilename() : null);
		productDetail.setPComponent(pComponent != null ? pComponent : ""); // 폼에서 전달된 값을 사용
		productDetail.setCook(cook != null ? cook : ""); // 폼에서 전달된 값을 사용
		productDetail.setContent(content != null ? content : ""); // 폼에서 전달된 값을 사용
	    
	    // Product Detail 저장
	    int detailResult = pMapper.addProductDetail(productDetail);
	    
		if(imgMain != null) {
			processProductImg(imgMain, product.getpNo(), FILE_PATH, "/images/product/main");
		}
		
		if(imgCook != null) {
			processProductImg(imgCook, product.getpNo(), FILE_PATH, "/images/product/cook");
		}
		
		if(imgComponent != null) {
			processProductImg(imgComponent, product.getpNo(), FILE_PATH, "/images/product/component");
		}
		return result;
	}
	// 관리자_상품 이미지 등록
	private void processProductImg(MultipartFile imgFile, int pNo, String fileSavePath, String dbFilePath) throws IllegalStateException, IOException {
		String FileName = imgFile.getOriginalFilename();
		String FileRename = Util.fileRename(FileName);
		imgFile.transferTo(new File(fileSavePath + FileRename));
		
		ProductImageVO productImg = new ProductImageVO();
		productImg.setFileName(FileName);
		productImg.setFileRename(FileRename);
		productImg.setFilePath(dbFilePath);
		productImg.setpNo(pNo);
		
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
	        processProductImg(imgMain, product.getpNo(), FILE_PATH, "/images/");
	    }
	    if (imgCook != null && !imgCook.isEmpty()) {
	        processProductImg(imgCook, product.getpNo(), FILE_PATH, "/images/");
	    }
	    if (imgComponent != null && !imgComponent.isEmpty()) {
	        processProductImg(imgComponent, product.getpNo(), FILE_PATH, "/images/");
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
		int result = pMapper.deleteProduct(pNo);
		return result;
	}



}
