package com.mart.boot.product.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mart.boot.product.model.service.ProductService;
import com.mart.boot.product.model.vo.ProductVO;

@Controller
@RequestMapping("/product")
public class AdminpController {
	
	private final ProductService pService;
	
	public AdminpController(ProductService pService) {
		this.pService = pService;
	}
	
	// 상품 등록 페이지
	@GetMapping("/register")
	public String showRegisterForm() {
		return "product/admin/productRegister";
	}

	// 전체 상품 조회
	@GetMapping("/list")
	public String showProductList(Model model) {
		List<ProductVO> pList = pService.selectList();
		model.addAttribute("pList", pList);
		return "product/admin/productList";
	}
}
