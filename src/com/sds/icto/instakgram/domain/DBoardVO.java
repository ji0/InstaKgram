package com.sds.icto.instakgram.domain;

public class DBoardVO {
	
	
	private Long no;
	private String pic_ref;
	private String content;
	private Long like_cnt;
	private Long member_no;
	private String member_name;
	private String reg_date;
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getPic_ref() {
		return pic_ref;
	}
	public void setPic_ref(String pic_ref) {
		this.pic_ref = pic_ref;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Long getLike_cnt() {
		return like_cnt;
	}
	public void setLike_cnt(Long like_cnt) {
		this.like_cnt = like_cnt;
	}
	public Long getMember_no() {
		return member_no;
	}
	public void setMember_no(Long member_no) {
		this.member_no = member_no;
	}
	public String getMember_name() {
		return member_name;
	}
	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	

}
