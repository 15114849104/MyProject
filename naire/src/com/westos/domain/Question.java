package com.westos.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * Question entity. @author MyEclipse Persistence Tools
 */

public class Question implements java.io.Serializable {

	// Fields

	private Integer qid;
	private Roles roles;
	private String content;
	private Set scores = new HashSet(0);
	private Set papers = new HashSet(0);

	// Constructors

	/** default constructor */
	public Question() {
	}

	/** full constructor */
	public Question(Roles roles, String content, Set scores, Set papers) {
		this.roles = roles;
		this.content = content;
		this.scores = scores;
		this.papers = papers;
	}

	// Property accessors

	public Integer getQid() {
		return this.qid;
	}

	public void setQid(Integer qid) {
		this.qid = qid;
	}

	public Roles getRoles() {
		return this.roles;
	}

	public void setRoles(Roles roles) {
		this.roles = roles;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Set getScores() {
		return this.scores;
	}

	public void setScores(Set scores) {
		this.scores = scores;
	}

	public Set getPapers() {
		return this.papers;
	}

	public void setPapers(Set papers) {
		this.papers = papers;
	}

	public String toString() {
		return "Question [content=" + content + ", qid=" + qid + "]";
	}
	//自己重写equals方法，原来是比较地址，这里重新写让他们比较内容
	public boolean equals(Object obj) {
		if(obj instanceof Question) {
			Question q = (Question) obj;
			return this.qid.equals(q.qid) && this.content.equals(q.content);
		}
		return false;
	}

}
	
