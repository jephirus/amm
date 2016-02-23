package cn.jxust.base.model;

/**
 * @author lt
 * @crateTime 2015-7-12 下午05:36:19   
 */
public class ShortMessage {
	private static final int STASTU_FAIL = 0;
	private static final int STASTU_SUCCESS = 1;
	private Integer id; 
	private String content; //短信内容
	private String postime; //短信发送时间
	private int status;  //短信发送的状态,0:发送失败，1：发送成功
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPostime() {
		return postime;
	}
	public void setPostime(String postime) {
		this.postime = postime;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
}
