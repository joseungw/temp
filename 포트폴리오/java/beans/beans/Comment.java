package beans;

public class Comment {
	private int comnum;
	private String postnum;
	private String comid;
	private String comnick;
	private String comcon;	//내용
	private String comdate;
	private String recomnum;	//댓글참조번호 //대댓글번호
	
	public int getComnum() {
		return comnum;
	}
	public void setComnum(int comnum) {
		this.comnum = comnum;
	}
	public String getPostnum() {
		return postnum;
	}
	public void setPostnum(String postnum) {
		this.postnum = postnum;
	}
	public String getComid() {
		return comid;
	}
	public void setComid(String comid) {
		this.comid = comid;
	}
	public String getComnick() {
		return comnick;
	}
	public void setComnick(String comnick) {
		this.comnick = comnick;
	}
	public String getComcon() {
		return comcon;
	}
	public void setComcon(String comcon) {
		this.comcon = comcon;
	}
	public String getComdate() {
		return comdate;
	}
	public void setComdate(String comdate) {
		this.comdate = comdate;
	}
	public String getRecomnum() {
		return recomnum;
	}
	public void setRecomnum(String recomnum) {
		this.recomnum = recomnum;
	}
	
	
}
