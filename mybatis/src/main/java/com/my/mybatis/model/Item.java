package com.my.mybatis.model;

import java.util.Date;

public class Item {
	
	/**
	 * id INT NOT NULL  AUTO_INCREMENT,
		itemsname VARCHAR(32) NOT NULL COMMENT '商品名称',
		price FLOAT(10,1) NOT NULL COMMENT '商品定价',
		detail TEXT COMMENT '商品描述',
		pic VARCHAR(64) DEFAULT NULL COMMENT '商品图片',
		createtime DATETIME NOT NULL COMMENT '生产日期',
	 */
	private int id;
	private String itemsname;
	private float price;
	private String detail;
	private String pic;
	private Date createTime;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getItemsname() {
		return itemsname;
	}
	public void setItemsname(String itemsname) {
		this.itemsname = itemsname;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Override
	public String toString() {
		return "Item [id=" + id + ", itemsname=" + itemsname + ", price=" + price + ", detail=" + detail + ", pic="
				+ pic + ", createTime=" + createTime + "]";
	}
	
}
