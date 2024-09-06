package com.mart.boot.product.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mart.boot.product.model.mapper.ProductMapper;
import com.mart.boot.product.model.service.ProductService;
import com.mart.boot.product.model.vo.ProductDetailVO;
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
		
		ProductDetailVO productDetail = new ProductDetailVO();
	    productDetail.setpNo(product.getpNo());
	    productDetail.setImgMain(imgMain != null ? imgMain.getOriginalFilename() : null);
	    productDetail.setImgCook(imgCook != null ? imgCook.getOriginalFilename() : null);
	    productDetail.setImgComponent(imgComponent != null ? imgComponent.getOriginalFilename() : null);
	    productDetail.setPComponent(pComponent != null ? pComponent : "");  
	    productDetail.setCook(cook != null ? cook : "");                  
	    productDetail.setContent(content != null ? content : ""); 
	    
		return "redirect:/admin/product/list";
	}
	
	// 관리자_상품 상세 정보 조회
	@GetMapping("/modify/{pNo}")
	public String showUpdateForm(@PathVariable("pNo") Integer pNo, Model model) {
		ProductVO product = pService.selectOneWithDetail(pNo);
		model.addAttribute("product", product);
		return "product/admin/modify";
	}
	// 관리자_상품 수정
	@PostMapping("/modify")
	public String updateProduct(ProductVO product, ProductDetailVO productDetail
			, @RequestParam("imgMain") MultipartFile imgMain
			, @RequestParam("imgCook") MultipartFile imgCook
			, @RequestParam("imgComponent") MultipartFile imgComponent
			, RedirectAttributes redirectAttributes) throws IllegalStateException, IOException {
			int result = pService.updateProduct(product, productDetail, imgMain, imgCook, imgComponent);
		    if (result > 0) {
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
		model.addAttribute("pList", pList);
		return "product/admin/list";
	}
	
	// 관리자_상품 삭제
	@GetMapping("/delete/{pNo}")
	public String deleteProduct(@PathVariable("pNo") Integer pNo, RedirectAttributes redirectAttributes) {
		System.out.println(pNo);
		int result = pService.deleteProduct(pNo);
		if(result > 0) {
			redirectAttributes.addFlashAttribute("message", "삭제가 완료되었습니다.");
		} else {
			redirectAttributes.addFlashAttribute("message", "삭제에 실패했습니다.");
		}
		return "redirect:/admin/product/list";
	}

}
