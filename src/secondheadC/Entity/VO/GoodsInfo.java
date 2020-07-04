package secondheadC.Entity.VO;

import javax.swing.JLabel;

public class GoodsInfo {
	String title,sellReason,classificationName,location,userac,goodsPictureName,salesStatus;
	Double price,initialPrice,freight;
	JLabel GoodsPicture;
	Integer pkid;
	
	public int getPkid() {
		return pkid;
	}
	public void setPkid(int pkid) {
		this.pkid = pkid;
	}
	
	public String getGoodsPictureName() {
		return goodsPictureName;
	}
	public void setGoodsPictureName(String goodsPictureName) {
		this.goodsPictureName = goodsPictureName;
	}
	public JLabel getGoodsPicture() {
		return GoodsPicture;
	}
	public void setGoodsPicture(JLabel goodsPicture) {
		GoodsPicture = goodsPicture;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSellReason() {
		return sellReason;
	}
	public void setSellReason(String sellReason) {
		this.sellReason = sellReason;
	}
	public String getClassificationName() {
		return classificationName;
	}
	public void setClassificationName(String classificationName) {
		this.classificationName = classificationName;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getUserac() {
		return userac;
	}
	public void setUserac(String userac) {
		this.userac = userac;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getInitialPrice() {
		return initialPrice;
	}
	public void setInitialPrice(double initialPrice) {
		this.initialPrice = initialPrice;
	}
	public double getFreight() {
		return freight;
	}
	public void setFreight(double freight) {
		this.freight = freight;
	}
	public String getSalesStatus() {
		return salesStatus;
	}
	public void setSalesStatus(String salesStatus) {
		this.salesStatus = salesStatus;
	}
}
