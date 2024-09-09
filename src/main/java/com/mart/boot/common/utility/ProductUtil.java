package com.mart.boot.common.utility;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.mart.boot.product.model.mapper.ProductMapper;
import com.mart.boot.product.model.vo.ProductImageVO;

public class ProductUtil {

    public static void processProductImg(MultipartFile imgFile, int pNo, String fileSavePath, String dbFilePath, String imgType, ProductMapper pMapper) throws IllegalStateException, IOException {
    	String FileName = imgFile.getOriginalFilename();
		String FileRename = "product_" + pNo + ".jpg";
		imgFile.transferTo(new File(fileSavePath + FileRename));

        // 파일을 실제로 저장
        imgFile.transferTo(new File(fileSavePath + FileRename));

        // 파일 정보 객체 생성
        ProductImageVO productImg = new ProductImageVO();
        productImg.setFileName(FileName);
        productImg.setFileRename(FileRename);
        productImg.setFilePath(dbFilePath);
        productImg.setpNo(pNo);
        productImg.setImageType(imgType);

        // 파일 정보를 DB에 저장하는 로직 (Mapper 호출)
        pMapper.addProductFile(productImg);
    }
}
