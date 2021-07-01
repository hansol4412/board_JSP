package domain;

public class Page {
	private int pageno; // ���� ������ ��ȣ
	private int prev_pageno; // ���� ������ ��ȣ
	private int page_sno; // ���� �׷� ���� ��ȣ
	private int page_eno; // ���� �׷� �� ��ȣ
	private int next_pageno; //���� ������ ��ȣ
	
	
	public Page(int pageno, int prev_pageno, int page_sno, int page_eno, int next_pageno) {
		this.pageno = pageno;
		this.prev_pageno = prev_pageno;
		this.page_sno = page_sno;
		this.page_eno = page_eno;
		this.next_pageno = next_pageno;
	}
	

	public int getPageno() {
		return pageno;
	}
	
	public void setPageno(int pageno) {
		this.pageno = pageno;
	}
	
	public int getPrev_pageno() {
		return prev_pageno;
	}
	
	public void setPrev_pageno(int prev_pageno) {
		this.prev_pageno = prev_pageno;
	}
	
	public int getPage_sno() {
		return page_sno;
	}
	
	public void setPage_sno(int page_sno) {
		this.page_sno = page_sno;
	}
	
	public int getPage_eno() {
		return page_eno;
	}
	
	public void setPage_eno(int page_eno) {
		this.page_eno = page_eno;
	}
	
	public int getNext_pageno() {
		return next_pageno;
	}
	
	public void setNext_pageno(int next_pageno) {
		this.next_pageno = next_pageno;
	}
	
	
}
