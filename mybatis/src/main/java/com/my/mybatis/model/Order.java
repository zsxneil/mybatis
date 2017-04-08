package com.my.mybatis.model;

import java.util.Date;
import java.util.List;

/**
 * Order 和 User是多对一关系，即一个用户可能有多个订单，但一个订单只可能有一个用户，两者的关系由多方维护
 * @author neil
 *
 */
public class Order {
	
	/**
	 * id INT NOT NULL AUTO_INCREMENT,
		user_id INT NOT NULL COMMENT '下单用户id',
		number VARCHAR(30) NOT NULL COMMENT '订单号',
		createtime DATETIME NOT NULL COMMENT '创建订单时间',
		note VARCHAR(100) DEFAULT NULL COMMENT '备注',
		PRIMARY KEY (`id`),
		KEY `FK_orders_1` (`user_id`),
		CONSTRAINT `FK_orders_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
	 */
	private int id;
	private String number;
	private Date createTime;
	private String note;
	private User user;
	private List<OrderDetail> detailList;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<OrderDetail> getDetailList() {
		return detailList;
	}
	public void setDetailList(List<OrderDetail> detailList) {
		this.detailList = detailList;
	}
	@Override
	public String toString() {
		return "Order [id=" + id + ", number=" + number + ", createTime=" + createTime + ", note=" + note + ", user="
				+ user + ", detailList=" + detailList + "]";
	}
	
}
