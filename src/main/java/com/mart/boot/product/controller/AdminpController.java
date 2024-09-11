package com.mart.boot.product.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mart.boot.common.pagenation.Criteria;
import com.mart.boot.common.pagenation.PagenationInfo;
import com.mart.boot.product.model.service.ProductService;
import com.mart.boot.product.model.vo.ProductDetailVO;
import com.mart.boot.product.model.vo.ProductImageVO;
import com.mart.boot.product.model.vo.ProductVO;

@Controller
@RequestMapping("/admin/product")
public class AdminpController {
	
	private final ProductService pService;
	
	public AdminpController(ProductService pService) {
		this.pService = pService;
	}
	
	// 관리자_상품 등록 페이지
	@GetMapping("/register")
	public String showRegisterForm() {
		return "product/admin/register";
	}
	// 관리자_상품 등록
	@PostMapping("/register")
	public String addProduct(ProductVO product
			, @RequestParam("imgMain") MultipartFile imgMain
			, @RequestParam("imgCook") MultipartFile imgCook
			, @RequestParam("imgComponent") MultipartFile imgComponent
			, @RequestParam("pComponent") String pComponent
			, @RequestParam("cook") String cook
			, @RequestParam("content") String content
			) throws IllegalStateException, IOException {
		
		int result = pService.addProduct(product, imgMain, imgCook, imgComponent, pComponent, cook, content);
		return "redirect:/admin/product/list";
	}
	
	// 관리자_상품 기본 및 상세 정보 조회
	@GetMapping("/{pNo}/modify")
	public String showUpdateForm(@PathVariable Integer pNo, Model model) {
		ProductVO product = pService.selectById(pNo);
	    model.addAttribute("product", product);
	    return "product/admin/modify";
	}
	// 관리자_상품 수정
	@PostMapping("/{pNo}/modify")
	public String updateProduct(RedirectAttributes redirectAttributes
			, ProductVO product, Model model
			, @ModelAttribute ProductDetailVO productDetail
			, @ModelAttribute ProductImageVO productImage
			) throws IllegalStateException, IOException {
		int productUpdateResult = pService.updateProduct(product);
	    int productDetailUpdateResult = pService.updateProductDetail(productDetail);

	    if (productUpdateResult > 0 && productDetailUpdateResult > 0) {
	        redirectAttributes.addFlashAttribute("message", "수정되었습니다.");
	    } else {
	        redirectAttributes.addFlashAttribute("message", "수정에 실패했습니다.");
	    }
	    
		return "redirect:/admin/product/list";
	}

	// 관리자_상품 전체 및 조건 검색
	@GetMapping("/list")
	public String searchProducts(Model model, Criteria criteria
			, @RequestParam(value = "startDate", required = false) String startDate
			, @RequestParam(value = "endDate", required = false) String endDate
			, @RequestParam(value = "categoryNo", required = false) Integer categoryNo
			, @RequestParam(value = "remainingDays", required = false) Integer remainingDays
			, @RequestParam(value = "pName", required = false) String pName){
		
		startDate = (startDate != null && startDate.isEmpty()) ? null : startDate;
	    endDate = (endDate != null && endDate.isEmpty()) ? null : endDate;
	    
		Map<String, Object> searchMap = new HashMap<>();
	    searchMap.put("startDate", startDate);
	    searchMap.put("endDate", endDate);
	    searchMap.put("categoryNo", categoryNo);
	    searchMap.put("remainingDays", remainingDays);
	    searchMap.put("pName", pName);

	    List<ProductVO> pList;
	    int prsnPageNo = criteria.getPrsnPageNo();
	    int cntntPerPage = criteria.getCntntPerPage();
	    int startRow = (prsnPageNo - 1) * cntntPerPage + 1;
	    int totalCount;
	    int endRow;

	    PagenationInfo pagenationInfo = new PagenationInfo(criteria);

	    // 조건 검색이 있는 경우
	    if (startDate != null || endDate != null || categoryNo != null || remainingDays != null || pName != null) {
	        // 검색 조건이 하나라도 있는 경우
	        totalCount = pService.getTotalCount(searchMap);
	        endRow = Math.min(prsnPageNo * cntntPerPage, totalCount);
	        searchMap.put("startRow", startRow);
	        searchMap.put("endRow", endRow);
	        pList = pService.searchProducts(searchMap);
	    } else {
	        // 전체 조회의 경우
	        totalCount = pService.getAllCount();
	        endRow = Math.min(prsnPageNo * cntntPerPage, totalCount);
	        pList = pService.selectList(prsnPageNo, cntntPerPage);
	    }
	    
	    pagenationInfo.setTotCnt(totalCount);

	    model.addAttribute("pList", pList);
	    model.addAttribute("pagenationInfo", pagenationInfo);
	    model.addAttribute("searchMap", searchMap);
	    model.addAttribute("totalCount", totalCount);
	    model.addAttribute("startDate", startDate);
	    model.addAttribute("endDate", endDate);
	    return "product/admin/list";
	}

	// 관리자_상품 삭제
	@GetMapping("/delete/{pNo}")
	public String deleteProduct(@PathVariable("pNo") Integer pNo 
								, RedirectAttributes redirectAttributes) {
		int result = pService.deleteProduct(pNo);
		if(result > 0) {
			redirectAttributes.addFlashAttribute("message", "삭제가 완료되었습니다.");
		} else {
			redirectAttributes.addFlashAttribute("message", "삭제에 실패했습니다.");
		}
		return "redirect:/admin/product/list";
	}
	
	// 관리자_선택 상품 삭제
	@PostMapping("/delete/check")
	public String selectDelete(@RequestParam(value = "chk", required = false) List<Integer> pNos
							, RedirectAttributes redirectAttributes) {
		if(pNos != null && !pNos.isEmpty()) {
			for(Integer pNo : pNos) {
				pService.deleteProduct(pNo);
			}
			redirectAttributes.addFlashAttribute("message", "선택한 상품이 삭제되었습니다.");
		} 
		return "redirect:/admin/product/list";
	}

}
