package s.po;

import java.io.Serializable;

public class Transactioninfo implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer pkid;
	private java.sql.Date TransactionTime;

	public Integer getPkid() {
		return pkid;
	}

	public void setPkid(Integer pkid) {
		this.pkid = pkid;
	}

	public java.sql.Date getTransactionTime() {
		return TransactionTime;
	}

	public void setTransactionTime(java.sql.Date transactionTime) {
		TransactionTime = transactionTime;
	}

	public Integer getFk_goods_id() {
		return fk_goods_id;
	}

	public void setFk_goods_id(Integer fk_goods_id) {
		this.fk_goods_id = fk_goods_id;
	}

	public Integer getFk_Selleruser_id() {
		return fk_Selleruser_id;
	}

	public void setFk_Selleruser_id(Integer fk_Selleruser_id) {
		this.fk_Selleruser_id = fk_Selleruser_id;
	}

	public Integer getFk_Buyeruser_id() {
		return fk_Buyeruser_id;
	}

	public void setFk_Buyeruser_id(Integer fk_Buyeruser_id) {
		this.fk_Buyeruser_id = fk_Buyeruser_id;
	}

	private Integer fk_goods_id;
	private Integer fk_Selleruser_id;
	private Integer fk_Buyeruser_id;

}
