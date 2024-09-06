package com.mart.boot.common.utility;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class Util {
	
	public static String fileRename(String originalFileName) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
		String date = sdf.format(new Date(System.currentTimeMillis()));
		String ext = originalFileName.substring(originalFileName.lastIndexOf("."));
		String fileRename = date + ext;
		return fileRename;
	}
}
