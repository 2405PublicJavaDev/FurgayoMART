package com.mart.boot.cart.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mart.boot.cart.model.vo.CartVO;

	@Mapper
	public interface CartMapper {

		/**
		 * 특정 회원의 특정 상품에 대한 장바구니 아이템을 조회합니다.
		 * @param memberNo
		 * @param pNo
		 * @return
		 */
		CartVO selectCartItemByProductAndMember(Long memberNo, int pNo);

		/**
		 * 특정 회원의 모든 장바구니 아이템을 조회합니다.
		 * @param memberNo
		 * @return
		 */
		List<CartVO> selectCartItems(Long memberNo);

		/**
		 * 장바구니 아이템을 업데이트합니다.
		 * @param existingItem
		 * @return
		 */
		int updateCartItem(CartVO existingItem);

		/**
		 * 장바구니에서 특정 아이템을 삭제합니다.
		 * @param cartItemNo
		 * @param memberNo
		 * @return
		 */
		int deleteCartItem(@Param("cartItemNo") Long cartItemNo, @Param("memberNo") Long memberNo);

		/**
		 * 장바구니 아이템의 수량을 업데이트합니다.
		 * @param cartItemNo
		 * @param memberNo
		 * @param quantity
		 * @return
		 */
		int updateCartItemQuantity(Long cartItemNo, Long memberNo, int quantity);

		/**
		 * 새로운 장바구니 아이템을 추가합니다.
		 * @param newItem
		 * @return
		 */
		int insertCartItem(CartVO newItem);
}