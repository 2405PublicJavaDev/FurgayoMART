package com.mart.boot.cart.model.vo;

import java.time.LocalDateTime;

public class CartVO {
    private Long cartItemNo; // 장바구니 아이템 고유 ID
    private Long memberNo; // 회원 고유번호
    private int pNo; // 상품번호
    private String pName; // 상품명
    private int pPrice; // 정가
    private String imageUrl; // 상품 이미지 URL
    private int quantity; // 장바구니에 담긴 수량
    private int sale; // 세일 정보
    private LocalDateTime addedAt; // 장바구니에 추가된 시간

    // 기본 생성자
    public CartVO() {}

    // 모든 필드를 포함하는 생성자
    public CartVO(Long cartItemNo, Long memberNo, int pNo, String pName, int pPrice, 
                  String imageUrl, int quantity, int sale, LocalDateTime addedAt) {
        this.cartItemNo = cartItemNo;
        this.memberNo = memberNo;
        this.pNo = pNo;
        this.pName = pName;
        this.pPrice = pPrice;
        this.imageUrl = imageUrl;
        this.quantity = quantity;
        this.sale = sale;
        this.addedAt = addedAt;
    }

    public Long getCartItemNo() {
		return cartItemNo;
	}

	public void setCartItemNo(Long cartItemNo) {
		this.cartItemNo = cartItemNo;
	}

	public Long getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(Long memberNo) {
		this.memberNo = memberNo;
	}

	public int getpNo() {
		return pNo;
	}

	public void setpNo(int pNo) {
		this.pNo = pNo;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public int getpPrice() {
		return pPrice;
	}

	public void setpPrice(int pPrice) {
		this.pPrice = pPrice;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getSale() {
		return sale;
	}

	public void setSale(int sale) {
		this.sale = sale;
	}

	public LocalDateTime getAddedAt() {
		return addedAt;
	}

	public void setAddedAt(LocalDateTime addedAt) {
		this.addedAt = addedAt;
	}

	// 할인 적용 가격 계산 메서드
    public int getDiscountedPrice() {
        return pPrice - (pPrice * sale / 100);
    }

    // 총 가격 계산 메서드
    public int getTotalPrice() {
        return getDiscountedPrice() * quantity;
    }
    
	@Override
	public String toString() {
		return "CartVO [cartItemNo=" + cartItemNo + ", memberNo=" + memberNo + ", pNo=" + pNo + ", pName=" + pName
				+ ", pPrice=" + pPrice + ", imageUrl=" + imageUrl + ", quantity=" + quantity + ", sale=" + sale
				+ ", addedAt=" + addedAt + "]";
	}

}