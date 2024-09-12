package com.mart.boot.cart.model.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mart.boot.cart.model.mapper.CartMapper;
import com.mart.boot.cart.model.service.CartService;
import com.mart.boot.cart.model.vo.CartVO;
import com.mart.boot.member.model.mapper.MemberMapper;
import com.mart.boot.member.model.vo.MemberVO;
import com.mart.boot.product.model.mapper.ProductMapper;
import com.mart.boot.product.model.vo.ProductVO;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<CartVO> getCartItems(Long memberNo) {
        return cartMapper.selectCartItems(memberNo);
    }

    @Override
    public boolean addToCart(Long memberNo, int pNo, int quantity) {
        CartVO existingItem = cartMapper.selectCartItemByProductAndMember(memberNo, pNo);
        if (existingItem != null) {
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
            return cartMapper.updateCartItem(existingItem) > 0;
        } else {
            CartVO newItem = new CartVO();
            newItem.setMemberNo(memberNo);
            newItem.setpNo(pNo);
            newItem.setQuantity(quantity);
            newItem.setAddedAt(LocalDateTime.now());
            
            // 상품 정보 설정
            ProductVO product = productMapper.selectOne(pNo);
            if (product != null) {
                newItem.setpName(product.getpName());
                newItem.setpPrice(product.getpPrice());
                newItem.setImageUrl(product.getImageUrl());
                newItem.setSale(product.getSale());
            }
            
            return cartMapper.insertCartItem(newItem) > 0;
        }
    }

    @Override
    @Transactional
    public boolean removeFromCart(Long cartItemNo, Long memberNo) {
    	try {
    		int result = cartMapper.deleteCartItem(cartItemNo, memberNo);
    		return result > 0;
    	} catch (Exception e) {
    		e.printStackTrace();
    		return false;
    	}
    }

    @Override
    public boolean updateCartItemQuantity(Long cartItemNo, Long memberNo, int quantity) {
        return cartMapper.updateCartItemQuantity(cartItemNo, memberNo, quantity) > 0;
    }
}