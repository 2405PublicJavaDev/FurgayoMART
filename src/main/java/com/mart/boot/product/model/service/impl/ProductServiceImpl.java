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
		productDetail.setPComponent(pComponent != null ? pComponent : ""); // 폼에서 전달된 값을 사용
		productDetail.setCook(cook != null ? cook : ""); // 폼에서 전달된 값을 사용
		productDetail.setContent(content != null ? content : ""); // 폼에서 전달된 값을 사용
		productDetail.setCategoryNo(categoryNo);
	    
	    // Product Detail 저장
	    int detailResult = pMapper.addProductDetail(productDetail);
	    
		if(imgMain != null && !imgMain.isEmpty()) {
			processProductImg(imgMain, product.getpNo(), FILE_PATH, "/images/", "MAIN");
		}
		
		if(imgCook != null && !imgCook.isEmpty()) {
			processProductImg(imgCook, product.getpNo(), FILE_PATH, "/images/", "COOK");
		}
		
		if(imgCook != null && !imgComponent.isEmpty()) {
			processProductImg(imgComponent, product.getpNo(), FILE_PATH, "/images/", "COMPONENT");
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
	public int updateProductDetail(ProductDetailVO productDetail, MultipartFile imgMain, MultipartFile imgCook, MultipartFile imgComponent) throws IllegalStateException, IOException {
		ProductDetailVO existDetail = pMapper.selectImageById(productDetail.getpNo());
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

	@Override
	public List<ProductVO> selectPopularList() {
		List<ProductVO> pList = pMapper.selectPopularList();
		return pList;
	}

	@Override
	public List<ProductVO> selectNewProducts() {
		List<ProductVO> pList = pMapper.selectNewProducts();
		return pList;
	}

	@Override
	public List<ProductVO> showExpirationList() {
		List<ProductVO> pList = pMapper.showExpirationList();
		return pList;
	}

	@Override
	public List<ProductVO> mainNewProducts() {
		List<ProductVO> pList = pMapper.mainNewProducts();
		return pList;
	}

}
