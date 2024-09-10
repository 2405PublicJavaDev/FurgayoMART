package com.mart.boot.common.utility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import com.mart.boot.product.model.mapper.ProductMapper;
import com.mart.boot.product.model.vo.ProductImageVO;

public class ProductUtil {

    public static String processProductImg(MultipartFile imgFile, int pNo, String fileSavePath, String imgType, ProductMapper pMapper) throws IllegalStateException, IOException {
    	if (imgFile != null && !imgFile.isEmpty()) {
    		String FileName = imgFile.getOriginalFilename();
    		String FileRename = "product_" + pNo + ".jpg";
            // 파일을 실제로 저장
            imgFile.transferTo(new File(fileSavePath + FileRename));
            String webPath = "/images/" + imgType.toLowerCase() + "/" + FileRename;
            
            ProductImageVO productImage = new ProductImageVO();
            productImage.setFileName(FileName);
            productImage.setFileRename(FileRename);
            productImage.setFilePath(webPath);
            productImage.setpNo(pNo);
            productImage.setImageType(imgType);

            pMapper.addProductFile(productImage);
            
            return webPath;
        }
        return null;
    }
}
