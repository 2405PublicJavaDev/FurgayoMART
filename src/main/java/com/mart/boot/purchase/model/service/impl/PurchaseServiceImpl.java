package com.mart.boot.purchase.model.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mart.boot.product.model.vo.ProductVO;
import com.mart.boot.purchase.model.mapper.PurchaseMapper;
import com.mart.boot.purchase.model.service.PurchaseService;
import com.mart.boot.purchase.model.vo.PurchaseVO;

@Service
public class PurchaseServiceImpl implements PurchaseService {

	@Autowired
	private PurchaseMapper pMapper;
	
	@Override
	public ProductVO getProductByPNo(int pNo) {
		return pMapper.selectProductByPNo(pNo);
	}

	@Override
	public String processPurchase(PurchaseVO purchaseVO) throws Exception {
		// 구매정보저장
		pMapper.insertPurchase(purchaseVO);
		// 무통장입금 정보 생성
		String bankAccount = "123-456-789";
		String bankName = "xx은행";
		String depositAmount = String.format("%,d원", purchaseVO.getTotalAmount());
		String depositDeadline = LocalDateTime.now().plusDays(3).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		
		return String.format("입금 계좌: %s (%s)\n입금액: %s\n입금 기한: %s",
				bankAccount, bankName, depositAmount, depositDeadline);
	}
}
