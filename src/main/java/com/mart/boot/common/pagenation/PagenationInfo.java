package com.mart.boot.common.pagenation;

public class PagenationInfo {
	
	private Criteria criteria;
	
	private int totCnt; // 전체 데이터 개수
	private int totPageCnt; // 전체 페이지 개수
	private int firstPageNo; // 페이지 리스트 첫 번호
	private int lastPageNo; // 페이지 리스트 마지막 번호
	private int firstIdx; // 현재 페이지에서 출력할 첫 번째 데이터의 위치 index
	private boolean prev; // 이전 페이지 존재 여부
	private boolean next; // 다음 페이지 존재 여부
	
	public PagenationInfo(Criteria criteria) {
		if(criteria.getPrsnPageNo()<1) {
			criteria.setPrsnPageNo(1);
		}
		this.criteria = criteria;
	}

	public Criteria getCriteria() {
		return criteria;
	}

	public void setCriteria(Criteria criteria) {
		this.criteria = criteria;
	}

	public int getTotCnt() {
		return totCnt;
	}

	public void setTotCnt(int totCnt) {
		this.totCnt = totCnt;
		
		if(totCnt > 0) {
			paginate(); // 페이지 계산 메서드
		}
	}

	private void paginate() {
		// 전체 페이지 수 계산
		// 현재 페이지 번호가 전체 페이지 수보다 크면 현재 페이지 번호에 전체 페이지 수를 저장
		// ex. 전체 데이터 개수 : 42, 페이지당 출력할 데이터 개수 : 10 -> 전체 페이지 개수 = ((42-1)/10)+1 = 5.1
		totPageCnt = ((totCnt - 1) / criteria.getCntntPerPage()) + 1;
		if(criteria.getPrsnPageNo() > totCnt) {
			criteria.setPrsnPageNo(totPageCnt);
		}
		// 페이지 리스트 첫 페이지/마지막 페이지 번호
		// (현재 페이지 번호(8) - 1 / 페이지 네비게이션 바에 표시할 페이지 번호의 개수(5)) * (페이지 네비게이션 바에 표시할 페이지 번호의 개수(5) + 1)
		// 현재 페이지 8번 > 6~10번 페이지 그룹에 속하며, 페이지 네비게이션 바에서 표시될 첫 번째 페이지 번호는 6 
		firstPageNo = ((criteria.getPrsnPageNo() - 1) / criteria.getPagesize()) * criteria.getPagesize() + 1;
		// 첫번째 페이지 번호 + 페이지 네비게이션 바에 표시할 페이지 번호의 개수 - 1
		// 4 + 5 - 1 = 8
		lastPageNo = firstPageNo + criteria.getPagesize() - 1;
		if(lastPageNo > totPageCnt) {
			lastPageNo = totPageCnt;
		}
		// 쿼리문에서 사용할 값
		// (현재 페이지 번호 - 1) * 페이지당 출력할 데이터 개수
		// ex. (3 - 1) * 10 = 20
		firstIdx = (criteria.getPrsnPageNo() - 1) * criteria.getCntntPerPage();
		// 이전/다음 페이지 존재 확인
		prev = firstPageNo != 1;
		next = (lastPageNo * criteria.getCntntPerPage()) < totCnt;
	}

	public int getTotPageCnt() {
		return totPageCnt;
	}

	public void setTotPageCnt(int totPageCnt) {
		this.totPageCnt = totPageCnt;
	}

	public int getFirstPageNo() {
		return firstPageNo;
	}

	public void setFirstPageNo(int firstPageNo) {
		this.firstPageNo = firstPageNo;
	}

	public int getLastPageNo() {
		return lastPageNo;
	}

	public void setLastPageNo(int lastPageNo) {
		this.lastPageNo = lastPageNo;
	}

	public int getFirstIdx() {
		return firstIdx;
	}

	public void setFirstIdx(int firstIdx) {
		this.firstIdx = firstIdx;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	@Override
	public String toString() {
		return "PaginationInfo [criteria=" + criteria + ", totCnt=" + totCnt + ", totPageCnt=" + totPageCnt
				+ ", firstPageNo=" + firstPageNo + ", lastPageNo=" + lastPageNo + ", firstIdx=" + firstIdx + ", prev="
				+ prev + ", next=" + next + "]";
	}
	
}
