package com.mart.boot.product.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mart.boot.product.model.service.ProductService;
import com.mart.boot.product.model.vo.ProductVO;

@Controller
@RequestMapping("/productpage")
public class UserpController {

	@Autowired
	private ProductService pService;
	
	// 한식 카테고리 페이지
	@GetMapping("/koreanfood")
	public String showKoreanFood(Model model) {
	    List<ProductVO> pList = pService.selectKoreanFood(1);
	    List<ProductVO> filteredProducts = pList.stream()
	            .filter(product -> product.getCategoryNo() == 1)
	            .collect(Collectors.toList());
	    for (ProductVO product : pList) {
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
	    }
	    model.addAttribute("pList", pList);
	    model.addAttribute("totalProducts", pList.size());
	    model.addAttribute("pageTitle", "전체 상품 목록");
	    model.addAttribute("pList", filteredProducts);
	    return "product/user/koreanfood";
	}
	// 중식 카테고리 페이지
	@GetMapping("/chinesefood")
	public String showChinesesFood(Model model) {
	    List<ProductVO> pList = pService.selectChinesesFood(2);
	    List<ProductVO> filteredProducts = pList.stream()
	            .filter(product -> product.getCategoryNo() == 2)
	            .collect(Collectors.toList());
	    for (ProductVO product : pList) {
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
	    }
	    model.addAttribute("pList", pList);
	    model.addAttribute("totalProducts", pList.size());
	    model.addAttribute("pageTitle", "전체 상품 목록");
	    model.addAttribute("pList", filteredProducts);
	    return "product/user/chinesefood";
	}
	
	// 일식 카테고리 페이지
		@GetMapping("/japanesefood")
		public String showJapaneseFood(Model model) {
		    List<ProductVO> pList = pService.selectJapaneseFood(3);
		    List<ProductVO> filteredProducts = pList.stream()
		            .filter(product -> product.getCategoryNo() == 3)
		            .collect(Collectors.toList());
		    for (ProductVO product : pList) {
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
		    }
		    model.addAttribute("pList", pList);
		    model.addAttribute("totalProducts", pList.size());
		    model.addAttribute("pageTitle", "전체 상품 목록");
		    model.addAttribute("pList", filteredProducts);
		    return "product/user/japanesefood";
		}
	
	@GetMapping("/detailList/{pNo}")
	public String showProductDetail(@PathVariable("pNo") int pNo, Model model) {
		ProductVO product = pService.selectOne(pNo);
	    // imageUrl이 이미 설정되어 있다고 가정합니다.
	    // 만약 설정되어 있지 않다면, 여기서 설정할 수 있습니다.
//	    if (product.getImageUrl() == null || product.getImageUrl().isEmpty()) {
//	        product.setImageUrl("/images/" + product.getImageUrl() + ".jpg");
//	    }
		product.setImageUrl("/images/product_" + product.getpNo() + ".jpg");
	    model.addAttribute("product", product);
	    return "product/user/detailList";
	    
	}

	// 상품 페이지 
	@GetMapping("/list")
	public String showProductListpage(Model model) {
	    List<ProductVO> pList = pService.selecpagetList();
	    
//	    LocalDate today = LocalDate.now();
//	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//	    
//	    List<ProductVO> saleOffProducts = pList.stream()
//	        .filter(product -> {
//	            LocalDate expirationDate = LocalDate.parse(product.getExpiration(), formatter);
//	            long daysUntilExpiration = ChronoUnit.DAYS.between(today, expirationDate);
//	            return daysUntilExpiration <= 7 && daysUntilExpiration > 0;
//	        })
//	        .collect(Collectors.toList());
	    
	    for (ProductVO product : pList) {
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
	    }
	    model.addAttribute("pList", pList);
	    model.addAttribute("totalProducts", pList.size());
	    model.addAttribute("pageTitle", "전체 상품 목록");
	    return "product/user/list";
	}
	
	
	
}
