package s.po;

import java.io.Serializable;

public class GoodsPictureInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer pkid;
	private byte[] GoodsPictureData;
	private String GoodsPictureName;
	private Integer GoodsId;

	public Integer getPkid() {
		return pkid;
	}

	public void setPkid(Integer pkid) {
		this.pkid = pkid;
	}

	public byte[] getGoodsPictureData() {
		return GoodsPictureData;
	}

	public void setGoodsPictureData(byte[] goodsPictureData) {
		GoodsPictureData = goodsPictureData;
	}

	public String getGoodsPictureName() {
		return GoodsPictureName;
	}

	public void setGoodsPictureName(String goodsPictureName) {
		GoodsPictureName = goodsPictureName;
	}

	public Integer getGoodsId() {
		return GoodsId;
	}

	public void setGoodsId(Integer goodsId) {
		GoodsId = goodsId;
	}

}
