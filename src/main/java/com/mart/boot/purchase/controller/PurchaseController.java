package com.mart.boot.purchase.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mart.boot.product.model.service.ProductService;
import com.mart.boot.product.model.vo.ProductVO;
import com.mart.boot.purchase.model.service.PurchaseService;
import com.mart.boot.purchase.model.vo.PurchaseVO;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/purchase")
public class PurchaseController {
	
	@Autowired
	private PurchaseService pService;
	
	@Autowired 
	private ProductService productService;
	
	@GetMapping("/product/{pNo}")
	public String showPurchasePage(@PathVariable int pNo
			, Model model) {
		ProductVO product = pService.getProductByPNo(pNo);
        if (product.getImageUrl() != null && !product.getImageUrl().isEmpty()) {
            product.setImageUrl("/images/" + product.getImageUrl());
        } else {
            String imageName = "product_" + product.getpNo() + ".jpg";
            Path imagePath = Paths.get("src/main/resources/static/images/" + imageName);
            if (Files.exists(imagePath)) {
                product.setImageUrl("/images/" + imageName);
            } else {
                product.setImageUrl("/images/noimage.jpg");
            }
        }
			model.addAttribute("product", product);
		return "purchase/purchase-page";
	}
	
	@PostMapping("/complete")
	public String completePurchase(@ModelAttribute PurchaseVO purchaseVO
			, ProductVO productVO
			, HttpSession session
			, Model model) {
		Long memberNo = (Long) session.getAttribute("memberNo");
		purchaseVO.setMemberNo(memberNo);
		purchaseVO.setPurchaseStatus("결제대기");
		purchaseVO.setPurchaseDate(LocalDateTime.now());
		
        try {
            String bankInfo = pService.processPurchase(purchaseVO);
            ProductVO product = productService.getProductByPname(productVO.getpName());
            model.addAttribute("bankInfo", bankInfo);
            model.addAttribute("purchase", purchaseVO);
            model.addAttribute("product", product);
            return "purchase/purchase-complete";
        } catch (Exception e) {
            model.addAttribute("error", "결제 처리 중 오류가 발생했습니다." + e.getMessage());
            return "purchase/purchase-error";
        }
	}
	
}
