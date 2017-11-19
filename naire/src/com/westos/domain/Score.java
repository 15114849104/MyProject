package com.westos.domain;

/**
 * Score entity. @author MyEclipse Persistence Tools
 */

public class Score implements java.io.Serializable {

	// Fields

	private Integer sid;
	private Question question;
	private User user;
	private Paper paper;
	private Integer score;

	// Constructors

	/** default constructor */
	public Score() {
	}

	/** full constructor */
	public Score(Question question, User user, Paper paper, Integer score) {
		this.question = question;
		this.user = user;
		this.paper = paper;
		this.score = score;
	}

	// Property accessors

	public Integer getSid() {
		return this.sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public Question getQuestion() {
		return this.question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Paper getPaper() {
		return this.paper;
	}

	public void setPaper(Paper paper) {
		this.paper = paper;
	}

	public Integer getScore() {
		return this.score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "Score [score=" + score + ", sid=" + sid + "]";
	}
	

}