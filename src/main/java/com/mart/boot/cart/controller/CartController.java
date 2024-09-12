package com.mart.boot.cart.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mart.boot.cart.model.service.CartService;
import com.mart.boot.cart.model.vo.CartVO;
import com.mart.boot.member.model.vo.MemberVO;
import com.mart.boot.product.model.service.ProductService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/view")
    public String viewCart(Model model, HttpSession session) {
        Long memberNo = (Long) session.getAttribute("memberNo");
        if (memberNo == null) {
            return "redirect:/login";
        }
        List<CartVO> cartItems = cartService.getCartItems(memberNo);
        
        for (CartVO item : cartItems) {
            if (item.getImageUrl() != null && !item.getImageUrl().isEmpty()) {
                item.setImageUrl("/images/" + item.getImageUrl());
            } else {
                String imageName = "product_" + item.getpNo() + ".jpg";
                Path imagePath = Paths.get("src/main/resources/static/images/" + imageName);
                if (Files.exists(imagePath)) {
                    item.setImageUrl("/images/" + imageName);
                } else {
                    item.setImageUrl("/images/noimage.jpg");
                }
            }
        }
        
        model.addAttribute("cartItems", cartItems);
        return "cart/cart-page";
    }

    @PostMapping("/add")
    @ResponseBody
    public String addToCart(@RequestParam("pNo") int pNo,
                            @RequestParam("quantity") int quantity,
                            HttpSession session) {
        Long memberNo = (Long) session.getAttribute("memberNo");
        if (memberNo == null) {
            return "login_required";
        }
        boolean added = cartService.addToCart(memberNo, pNo, quantity);
        return added ? "success" : "failed";
    }

    @PostMapping("/remove")
    @ResponseBody
    public String removeFromCart(@RequestParam("cartItemNo") Long cartItemNo,
                                 HttpSession session) {
        Long memberNo = (Long) session.getAttribute("memberNo");
        if (memberNo == null) {
            return "login_required";
        }
        boolean removed = cartService.removeFromCart(cartItemNo, memberNo);
        return removed ? "success" : "failed";
    }

    @PostMapping("/update")
    @ResponseBody
    public String updateCartQuantity(@RequestParam("cartItemNo") Long cartItemNo,
                                     @RequestParam("quantity") int quantity,
                                     HttpSession session) {
        Long memberNo = (Long) session.getAttribute("memberNo");
        if (memberNo == null) {
            return "login_required";
        }
        boolean updated = cartService.updateCartItemQuantity(cartItemNo, memberNo, quantity);
        return updated ? "success" : "failed";
    }
}