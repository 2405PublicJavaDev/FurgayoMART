package com.mart.boot.product.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

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

	@GetMapping("/detailList/{pNo}")
	public String showProductDetail(@PathVariable("pNo") int pNo, Model model) {
		// selectOne 메서드를 사용하지 않고 임시 데이터를 생성합니다.
		ProductVO product = new ProductVO();
		product.setpNo(pNo);
		product.setImageUrl("/images/product_" + pNo + ".jpg");
		// 필요한 다른 속성들도 설정해주세요.

		model.addAttribute("product", product);
		return "product/user/detailList";
	}

	@GetMapping("/list")
	public String showProductListpage(Model model) {
		List<ProductVO> pList = pService.selectList();

		for (ProductVO product : pList) {
			if (product.getImageUrl() == null || product.getImageUrl().isEmpty()) {
				String imageName = "product_" + product.getpNo() + ".jpg";
				Path imagePath = Paths.get("src/main/resources/static/images/" + imageName);
				if (Files.exists(imagePath)) {
					product.setImageUrl("/images/" + imageName);
				} else {
					product.setImageUrl("/images/noimage.jpg");
				}
			} else {
				product.setImageUrl("/images/" + product.getImageUrl());
			}
		}
		model.addAttribute("pList", pList);
		model.addAttribute("totalProducts", pList.size());
		model.addAttribute("pageTitle", "전체 상품 목록");
		return "product/user/list";
	}
}