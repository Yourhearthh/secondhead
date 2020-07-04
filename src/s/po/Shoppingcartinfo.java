package s.po;

import java.io.Serializable;

public class Shoppingcartinfo implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer pkid;
	private Integer GoodsId;
	private Integer fk_user_id;

	public Integer getPkid() {
		return pkid;
	}

	public void setPkid(Integer pkid) {
		this.pkid = pkid;
	}

	public Integer getGoodsId() {
		return GoodsId;
	}

	public void setGoodsId(Integer goodsId) {
		GoodsId = goodsId;
	}

	public Integer getFk_user_id() {
		return fk_user_id;
	}

	public void setFk_user_id(Integer fk_user_id) {
		this.fk_user_id = fk_user_id;
	}

}
