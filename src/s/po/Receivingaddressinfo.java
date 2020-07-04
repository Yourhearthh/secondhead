package s.po;

import java.io.Serializable;

public class Receivingaddressinfo implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer pkid;
	private String ConsigneeName;
	private String PhoneNumber;
	private String sheng_name;
	private String shi_name;
	private String qu_name;
	private Integer fk_user_id;
	private String DetailedAddress;
	private Byte DefaultAddress;

	public Integer getPkid() {
		return pkid;
	}

	public void setPkid(Integer pkid) {
		this.pkid = pkid;
	}

	public String getConsigneeName() {
		return ConsigneeName;
	}

	public void setConsigneeName(String consigneeName) {
		ConsigneeName = consigneeName;
	}

	public String getPhoneNumber() {
		return PhoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}

	public String getSheng_name() {
		return sheng_name;
	}

	public void setSheng_name(String sheng_name) {
		this.sheng_name = sheng_name;
	}

	public String getShi_name() {
		return shi_name;
	}

	public void setShi_name(String shi_name) {
		this.shi_name = shi_name;
	}

	public String getQu_name() {
		return qu_name;
	}

	public void setQu_name(String qu_name) {
		this.qu_name = qu_name;
	}

	public Integer getFk_user_id() {
		return fk_user_id;
	}

	public void setFk_user_id(Integer fk_user_id) {
		this.fk_user_id = fk_user_id;
	}

	public String getDetailedAddress() {
		return DetailedAddress;
	}

	public void setDetailedAddress(String detailedAddress) {
		DetailedAddress = detailedAddress;
	}

	public Byte getDefaultAddress() {
		return DefaultAddress;
	}

	public void setDefaultAddress(Byte defaultAddress) {
		DefaultAddress = defaultAddress;
	}

}
