package com.mart.boot.cart.model.service;

import java.util.List;

import com.mart.boot.cart.model.vo.CartVO;
import com.mart.boot.member.model.vo.MemberVO;

public interface CartService {

    /**
     * 특정 회원의 장바구니 아이템 목록을 조회합니다.
     * @param memberNo 회원 번호
     * @return 장바구니 아이템 목록
     */
    List<CartVO> getCartItems(Long memberNo);

    /**
     * 장바구니에 상품을 추가합니다.
     * @param memberNo 회원 번호
     * @param pNo 상품 번호
     * @param quantity 추가할 수량
     * @return 추가 성공 여부
     */
    boolean addToCart(Long memberNo, int pNo, int quantity);

    /**
     * 장바구니에서 상품을 제거합니다.
     * @param cartItemNo 장바구니 아이템 번호
     * @param memberNo 회원 번호
     * @return 제거 성공 여부
     */
    boolean removeFromCart(Long cartItemNo, Long memberNo);

    /**
     * 장바구니 내 상품의 수량을 업데이트합니다.
     * @param cartItemNo 장바구니 아이템 번호
     * @param memberNo 회원 번호
     * @param quantity 새로운 수량
     * @return 업데이트 성공 여부
     */
    boolean updateCartItemQuantity(Long cartItemNo, Long memberNo, int quantity);
}