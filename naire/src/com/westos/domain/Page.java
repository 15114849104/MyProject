package com.westos.domain;

import java.util.List;

public class Page {
	//定义十个变量
	private int p;
	private int startPage;
	private int endPage;
	private int prevPage;
	private int nextPage;
	private int maxPage;
	private int startLine;
	private int size;
	private int rowCount;
	private List list;
	
	public Page(int p_,int rowCount_,int size_) {
		p=p_;
		rowCount=rowCount_;
		size=size_;
		
		maxPage=(int) (Math.ceil((rowCount*1.0)/size));//算出总页数
	   // 赶紧限定当前页数的范围，让他在合理范围内
		if(p>maxPage) p=maxPage;
		if(p<1) p=1;
		
		//算出上一页和下一页
		prevPage=p-1;
		nextPage=p+1;
		//算出起始行
		startLine=(p-1)*size;
	
	}

	public int getP() {
		return p;
	}

	public void setP(int p) {
		this.p = p;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getPrevPage() {
		return prevPage;
	}

	public void setPrevPage(int prevPage) {
		this.prevPage = prevPage;
	}

	public int getNextPage() {
		return nextPage;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	public int getMaxPage() {
		return maxPage;
	}

	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}

	public int getStartLine() {
		return startLine;
	}

	public void setStartLine(int startLine) {
		this.startLine = startLine;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getRowCount() {
		return rowCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public String toString() {
		return "Page [endPage=" + endPage + ", list=" + list + ", maxPage="
				+ maxPage + ", nextPage=" + nextPage + ", p=" + p
				+ ", prevPage=" + prevPage + ", rowCount=" + rowCount
				+ ", size=" + size + ", startLine=" + startLine
				+ ", startPage=" + startPage + "]";
	}
	
	

}
