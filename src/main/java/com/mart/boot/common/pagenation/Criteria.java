package com.mart.boot.common.pagenation;

public class Criteria {
	
	private int prsnPageNo; // 현재 페이지 번호
	private int cntntPerPage; // 페이지당 출력할 데이터 개수
	private int pagesize; // 페이지 네비게이션 바에 표시할 페이지 번호의 개수
	
	public Criteria() {
		this.prsnPageNo = 1;
		this.cntntPerPage = 10;
		this.pagesize = 10;
	}

	public int getPrsnPageNo() {
		return prsnPageNo;
	}

	public void setPrsnPageNo(int prsnPageNo) {
		this.prsnPageNo = prsnPageNo;
	}

	public int getCntntPerPage() {
		return cntntPerPage;
	}

	public void setCntntPerPage(int cntntPerPage) {
		this.cntntPerPage = cntntPerPage;
	}

	public int getPagesize() {
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	@Override
	public String toString() {
		return "Criteria [prsnPageNo=" + prsnPageNo + ", cntntPerPage=" + cntntPerPage + ", pagesize=" + pagesize + "]";
	}

}
