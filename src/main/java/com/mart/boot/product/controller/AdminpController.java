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
			, ProductVO product
			, @ModelAttribute ProductDetailVO productDetail
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

	// 관리자_전체 상품 조회
	@GetMapping("/list")
	public String showProductList(Model model) {
		List<ProductVO> pList = pService.selectList();
		int totalCount = pService.getAllCount(pList);
		if (pList == null || pList.isEmpty()) {
	        model.addAttribute("message", "조회할 상품이 없습니다.");
	    }
	    model.addAttribute("pList", pList);
	    model.addAttribute("totalCount",totalCount);
		return "product/admin/list";
	}

	// 관리자_상품 조건 검색
	@GetMapping("/search")
	public String searchProducts(Model model
			, @RequestParam(value = "startDate", required = false) String startDate
			, @RequestParam(value = "endDate", required = false) String endDate
			, @RequestParam(value = "categoryNo", required = false) Integer categoryNo
			, @RequestParam(value = "remainingDays", required = false) Integer remainingDays
			, @RequestParam(value = "pName", required = false) String pName) {
		Map<String, Object> searchMap = new HashMap<>();
	    searchMap.put("startDate", startDate);
	    searchMap.put("endDate", endDate);
	    searchMap.put("categoryNo", categoryNo);
	    searchMap.put("remainingDays", remainingDays);
	    searchMap.put("pName", pName);
	    
	    List<ProductVO> pList = pService.searchProducts(searchMap);
		int totalCount = pService.getTotalCount(searchMap);
		
		model.addAttribute("pList", pList);
		model.addAttribute("totalCount",totalCount);
		if (pList == null || pList.isEmpty()) {
	        model.addAttribute("message", "조회할 상품이 없습니다.");
	    }
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
