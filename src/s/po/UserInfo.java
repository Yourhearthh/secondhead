package s.po;

import java.io.Serializable;

public class UserInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer pkid;
	private String AccountNumber;
	private String PassWords;
	private String Nickname;
	private String Sex;
	private java.sql.Date Birthday;
	private Integer ResidentCity;
	private String PhoneNumber;
	private byte[] HeadPortraitData;
	private String HeadPortraitName;
	private Double Balance;

	public Double getBalance() {
		return Balance;
	}

	public void setBalance(Double balance) {
		Balance = balance;
	}

	private java.sql.Date RegisterTime;
	private String OnlineStatus;

	public Integer getPkid() {
		return pkid;
	}

	public void setPkid(Integer pkid) {
		this.pkid = pkid;
	}

	public String getAccountNumber() {
		return AccountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		AccountNumber = accountNumber;
	}

	public String getPassWords() {
		return PassWords;
	}

	public void setPassWords(String passWords) {
		PassWords = passWords;
	}

	public String getNickname() {
		return Nickname;
	}

	public void setNickname(String nickname) {
		Nickname = nickname;
	}

	public String getSex() {
		return Sex;
	}

	public void setSex(String sex) {
		Sex = sex;
	}

	public java.sql.Date getBirthday() {
		return Birthday;
	}

	public void setBirthday(java.sql.Date birthday) {
		Birthday = birthday;
	}

	public Integer getResidentCity() {
		return ResidentCity;
	}

	public void setResidentCity(Integer residentCity) {
		ResidentCity = residentCity;
	}

	public String getPhoneNumber() {
		return PhoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}

	public byte[] getHeadPortraitData() {
		return HeadPortraitData;
	}

	public void setHeadPortraitData(byte[] headPortraitData) {
		HeadPortraitData = headPortraitData;
	}

	public String getHeadPortraitName() {
		return HeadPortraitName;
	}

	public void setHeadPortraitName(String headPortraitName) {
		HeadPortraitName = headPortraitName;
	}

	public java.sql.Date getRegisterTime() {
		return RegisterTime;
	}

	public void setRegisterTime(java.sql.Date registerTime) {
		RegisterTime = registerTime;
	}

	public String getOnlineStatus() {
		return OnlineStatus;
	}

	public void setOnlineStatus(String onlineStatus) {
		OnlineStatus = onlineStatus;
	}

}
