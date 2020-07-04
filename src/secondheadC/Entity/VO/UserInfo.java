package secondheadC.Entity.VO;

import javax.swing.JLabel;

public class UserInfo {
	String headPortraitPath,accountNumber,nickname,passWords,sex,birthday,cityName,phoneNumber;
	
	JLabel headPortrait;
	
	public String getHeadPortraitPath() {
		return headPortraitPath;
	}

	public void setHeadPortraitPath(String headPortraitPath) {
		this.headPortraitPath = headPortraitPath;
	}

	
	public JLabel getHeadPortrait() {
		return headPortrait;
	}

	public void setHeadPortrait(JLabel headPortrait) {
		this.headPortrait = headPortrait;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPassWords() {
		return passWords;
	}

	public void setPassWords(String passWords) {
		this.passWords = passWords;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	

	@Override
	public String toString() {
		return "UserInfo [headPortraitPath=" + ", accountNumber=" + accountNumber + ", nickname="
				+ nickname + ", passWords=" + passWords + ", sex=" + sex + ", birthday=" + birthday + ", cityName="
				+ cityName + ", phoneNumber=" + phoneNumber + ", onlineStatus="+ "]";
	}
}
