package com.mart.boot.purchase.model.service;

import com.mart.boot.product.model.vo.ProductVO;
import com.mart.boot.purchase.model.vo.PurchaseVO;

public interface PurchaseService {

	ProductVO getProductByPNo(int pNo);

	String processPurchase(PurchaseVO purchaseVO) throws Exception;

}
