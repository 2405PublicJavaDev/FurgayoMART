package com.mart.boot.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mart.boot.product.model.vo.ProductVO;
import com.mart.boot.product.service.ProductService;

@RestController
@RequestMapping("/product")
public class AdminpController {
	
	private final ProductService pService;
	
	@Autowired
	public AdminpController(ProductService pService) {
		this.pService = pService;
	}

	@GetMapping("/list")
	public List<ProductVO> showProductList() {
		List<ProductVO> pList = pService.selectList();
		return pList;
	}
	
}
