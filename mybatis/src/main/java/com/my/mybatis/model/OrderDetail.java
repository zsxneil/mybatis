package com.my.mybatis.model;


/**
 * 
	订单条目详情类
	条目详情和订单是多对一关系，即一个单独有多个详情条目，包括项目itemid，该商品数量等。
	条目详情和商品条目是多对一关系，即一个条目详情只和一个商品条目对应，但一个商品条目可以有多个条目详情，这里两者的关系在多方维护。
 * @author neil
 *
 */
public class OrderDetail {
	
	/**
	 * id INT NOT NULL AUTO_INCREMENT,
		orders_id INT NOT NULL COMMENT '订单id',
		items_id INT NOT NULL COMMENT '商品id',
		items_num INT  DEFAULT NULL COMMENT '商品购买数量',
		PRIMARY KEY (id),
		KEY `FK_orderdetail_1` (`orders_id`),
		KEY `FK_orderdetail_2` (`items_id`),
		CONSTRAINT `FK_orderdetail_1` FOREIGN KEY (`orders_id`) REFERENCES `orders` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
		CONSTRAINT `FK_orderdetail_2` FOREIGN KEY (`items_id`) REFERENCES `items` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
	*/
	private int id;
	private int itemsNumber;
	private Item item;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getItemsNumber() {
		return itemsNumber;
	}
	public void setItemsNumber(int itemsNumber) {
		this.itemsNumber = itemsNumber;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	@Override
	public String toString() {
		return "OrderDetail [id=" + id + ", itemsNumber=" + itemsNumber + ", item=" + item + "]";
	}
}
