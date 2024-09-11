package com.mart.boot.product.model.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mart.boot.common.pagenation.Criteria;
import com.mart.boot.common.pagenation.PagenationInfo;
import com.mart.boot.common.utility.ProductUtil;
import com.mart.boot.product.model.mapper.ProductMapper;
import com.mart.boot.product.model.service.ProductService;
import com.mart.boot.product.model.vo.ProductDetailVO;
import com.mart.boot.product.model.vo.ProductImageVO;
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
	
	// 관리자_상품 이미지 타입
	@Override
	public List<ProductImageVO> selectProductImageList(Integer pNo) {
		return pMapper.selectProductImageList(pNo);
	}

	// 관리자_상품 전체 개수
	@Override
	public int getAllCount() {
		return pMapper.getAllCount();
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
	public List<ProductVO> selectList(int prsnPageNo, int cntntPerPage) {
		Criteria criteria = new Criteria();
		criteria.setPrsnPageNo(prsnPageNo);
		criteria.setCntntPerPage(cntntPerPage);
		int totCnt = pMapper.getAllCount();
		PagenationInfo pagenationInfo = new PagenationInfo(criteria);
		pagenationInfo.setTotCnt(totCnt);
		
		int startRow = (prsnPageNo - 1) * cntntPerPage;
		int endRow = startRow + cntntPerPage;
		List<ProductVO> pList = pMapper.getProductList(startRow, endRow);
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
		ProductVO product = pMapper.selectById(pNo);
		
		List<ProductImageVO> imageList = pMapper.selectProductImageList(pNo);
	    // 각 이미지의 타입에 맞게 URL을 설정
	    for (ProductImageVO image : imageList) {
	        switch (image.getImageType()) {
	            case "MAIN":
	                product.setImgMainUrl(image.getFilePath());
	                break;
	            case "COOK":
	                product.setImgCookUrl(image.getFilePath());
	                break;
	            case "COMPONENT":
	                product.setImgComponentUrl(image.getFilePath());
	                break;
	        }
	    }
		return product;
	}

	// 관리자_상품 기본 정보 수정
	@Override
	public int updateProduct(ProductVO product) {
		return pMapper.updateProduct(product);
	}
	// 관리자_상품 상세 정보 수정
	@Override
	public int updateProductDetail(ProductDetailVO productDetail) throws IllegalStateException, IOException {
	    List<ProductImageVO> existImageList = pMapper.selectProductImageList(productDetail.getpNo());

	    // imgMainFile 처리
	    if (productDetail.getImgMainFile() != null && !productDetail.getImgMainFile().isEmpty()) {
	        String newImgMainFilename = ProductUtil.processProductImg(productDetail.getImgMainFile(), productDetail.getpNo(), FILE_PATH_MAIN, "MAIN", pMapper);
	        productDetail.setImgMain(newImgMainFilename);
	    } else {
	        // 기존 이미지 유지
	        productDetail.setImgMain(findExistingImagePath(existImageList, "MAIN"));
	    }

	    // imgCookFile 처리
	    if (productDetail.getImgCookFile() != null && !productDetail.getImgCookFile().isEmpty()) {
	        String newImgCookFilename = ProductUtil.processProductImg(productDetail.getImgCookFile(), productDetail.getpNo(), FILE_PATH_COOK, "COOK", pMapper);
	        productDetail.setImgCook(newImgCookFilename);
	    } else {
	        // 기존 이미지 유지
	        productDetail.setImgCook(findExistingImagePath(existImageList, "COOK"));
	    }

	    // imgComponentFile 처리
	    if (productDetail.getImgComponentFile() != null && !productDetail.getImgComponentFile().isEmpty()) {
	        // 새로운 이미지 파일을 저장하고 기존 이미지를 삭제
	        String newImgComponentFilename = ProductUtil.processProductImg(productDetail.getImgComponentFile(), productDetail.getpNo(), FILE_PATH_COMPONENT, "COMPONENT", pMapper);
	        productDetail.setImgComponent(newImgComponentFilename);
	    } else {
	        // 기존 이미지 유지
	        productDetail.setImgComponent(findExistingImagePath(existImageList, "COMPONENT"));
	    }

	    return pMapper.updateProductDetail(productDetail);
	}

	private String findExistingImagePath(List<ProductImageVO> existingImages, String imageType) {
	    for (ProductImageVO image : existingImages) {
	        if (image.getImageType().equals(imageType)) {
	            return image.getFilePath();
	        }
	    }
	    return null; 
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

