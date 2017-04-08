package com.my.mybatis;

import java.io.IOException;  
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;  
import org.junit.Test;

import com.my.mybatis.model.Item;
import com.my.mybatis.model.Order;

public class ItemTest {  
         //定义一个会话工厂  
         private SqlSessionFactory  sqlSessionFactory;  
         @Before  
         public void init() throws IOException {  
                   //加载配置文件  
                   String resource  =  "sqlMapConfig.xml";  
                   //加载文件到输入流  
                   InputStream inputStream = Resources.getResourceAsStream(resource);  
                   //创建会话工厂  
                   sqlSessionFactory= new SqlSessionFactoryBuilder().build(inputStream);  
         }  
         /*  
          * 测试根据Id查询用户获取单条记录  
          */  
         @Test  
         public void testBody(){  
                   //通过sqlSessionFactory创建sqlSession  
                   SqlSession sqlSession = sqlSessionFactory.openSession();  
                   testForEachSql(sqlSession);
         } 
         
        public void testDynamicsql(SqlSession sqlSession) {
        	Item item = null;
        	Map<String, Object> parms = new HashMap<String, Object>();
        	//parms.put("id", 1);
        	parms.put("itemsname", "笔记本");
        	try {
				item = sqlSession.selectOne("itemDemo.selectItem",parms);
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				sqlSession.close();
			}
        	
        	System.out.println(item);
        }
        
        public void testSetSql(SqlSession sqlSession) {
        	Map<String, Object> parms = new HashMap<String, Object>();
        	parms.put("id", 1);
        	parms.put("itemsname", "笔记本1");
        	parms.put("price", 1);
        	parms.put("createTime", new Date());
        	try {
				sqlSession.update("itemDemo.updateItemIfNecessary",parms);
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				sqlSession.commit();
				sqlSession.close();
			}
		}
        
        public void testForEachSql(SqlSession sqlSession) {
			List<Integer> ids = new ArrayList<Integer>();
			ids.add(1);
			ids.add(2);
			ids.add(3);
			List<Item> items = null;
			try {
				items = sqlSession.selectList("itemDemo.selectItemIn",ids);
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				sqlSession.close();
			}
			if (items != null && items.size() > 0) {
				for (Item item : items) {
					System.out.println(item);
				}
			}
		}
        
}  