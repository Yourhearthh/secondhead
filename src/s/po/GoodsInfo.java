package s.po;

import java.io.Serializable;

public class GoodsInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer Intgepkid;
	private String Title;
	private String SellReason;
	private Double Price;
	private Double Initialprice;
	private Double Freight;
	private Integer ClassificationId;
	private String SalesStatus;
	private java.sql.Date CreateTime;
	private java.sql.Date ModifyTime;
	private Integer Location;
	private Integer fk_user_id;

	public String getSalesStatus() {
		return SalesStatus;
	}

	public void setSalesStatus(String salesStatus) {
		SalesStatus = salesStatus;
	}

	public Integer getIntgepkid() {
		return Intgepkid;
	}

	public void setIntgepkid(Integer intgepkid) {
		Intgepkid = intgepkid;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getSellReason() {
		return SellReason;
	}

	public void setSellReason(String sellReason) {
		SellReason = sellReason;
	}

	public Double getPrice() {
		return Price;
	}

	public void setPrice(Double price) {
		Price = price;
	}

	public Double getInitialprice() {
		return Initialprice;
	}

	public void setInitialprice(Double initialprice) {
		Initialprice = initialprice;
	}

	public Double getFreight() {
		return Freight;
	}

	public void setFreight(Double freight) {
		Freight = freight;
	}

	public Integer getClassificationId() {
		return ClassificationId;
	}

	public void setClassificationId(Integer classificationId) {
		ClassificationId = classificationId;
	}

	public java.sql.Date getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(java.sql.Date createTime) {
		CreateTime = createTime;
	}

	public java.sql.Date getModifyTime() {
		return ModifyTime;
	}

	public void setModifyTime(java.sql.Date modifyTime) {
		ModifyTime = modifyTime;
	}

	public Integer getLocation() {
		return Location;
	}

	public void setLocation(Integer location) {
		Location = location;
	}

	public Integer getFk_user_id() {
		return fk_user_id;
	}

	public void setFk_user_id(Integer fk_user_id) {
		this.fk_user_id = fk_user_id;
	}

}
