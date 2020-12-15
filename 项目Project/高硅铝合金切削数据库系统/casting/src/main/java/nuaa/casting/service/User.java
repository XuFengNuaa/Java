package nuaa.casting.service;

public class User {
	private String username;
	private String userrole;
	private String password;
	private String userrolenum;
	private String phonenumber;
	private String stuff_num;
	private String remark;

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserrole() {
		return userrole;
	}
	public void setUserrole(String userrole) {
		this.userrole = userrole;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserrolenum() {
		return userrolenum;
	}
	public void setUserrolenum(String userrolenum) {
		this.userrolenum = userrolenum;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getStuff_num() {
		return stuff_num;
	}
	public void setStuff_num(String stuff_num) {
		this.stuff_num = stuff_num;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
	public String toString() {
		return "User [username=" + username + ", userrole=" + userrole + ", password=" + password + ", userrolenum="
				+ userrolenum + ", phonenumber=" + phonenumber + ", stuff_num=" + stuff_num + ", remark=" + remark
				+ "]";
	}
	
}
