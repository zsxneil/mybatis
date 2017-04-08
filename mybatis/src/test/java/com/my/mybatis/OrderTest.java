package com.my.mybatis;

import java.io.IOException;  
import java.io.InputStream;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;  
import org.junit.Test;

import com.my.mybatis.model.Order;

public class OrderTest {  
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
                   testJoinCollection(sqlSession);
         } 
         
         
        public void testFetchLazy(SqlSession sqlSession) {
        	//通过sqlSession操作数据库  
            //第一个参数：statment的位置，等于namespace+statment的id  
            //第二个参数：传入的参数  
            Order order = null;
            try{  
            	order = sqlSession.selectOne("orderDemo.selectOrderByNumber", "1000010");  
            }catch (Exception e) {  
                e.printStackTrace();  
            }finally{  
                //关闭sqlSession  
                //这里放在try/catch语句的finally确保查询之后能关闭sqlSession  
                sqlSession.close();  
            }  
            System.out.println(order.getId());
            System.out.println(order.getUser().getUsername());//触发关联查询
        }
        
        public void testJoinSelect(SqlSession sqlSession) {
        	//通过sqlSession操作数据库  
            //第一个参数：statment的位置，等于namespace+statment的id  
            //第二个参数：传入的参数  
            Order order = null;
            try{  
            	order = sqlSession.selectOne("orderDemo.selectOrder", "1000011");  
            }catch (Exception e) {  
                e.printStackTrace();  
            }finally{  
                //关闭sqlSession  
                //这里放在try/catch语句的finally确保查询之后能关闭sqlSession  
                sqlSession.close();  
            }  
            System.out.println(order);
            System.out.println(order.getUser());
        }
        
        public void testCollection(SqlSession sqlSession) {
        	Order order = null;
        	try {
				order = sqlSession.selectOne("orderDemo.selectOrderWithDetail","1000010");
			}catch (Exception e) {
				e.printStackTrace();
			} finally {
				sqlSession.close();
			}
        	System.out.println(order);
        }
        
        public void testJoinCollection(SqlSession sqlSession) {
        	Order order = null;
        	try {
				order = sqlSession.selectOne("orderDemo.selectOrderJoinDetail","1000010");
			}catch (Exception e) {
				e.printStackTrace();
			} finally {
				sqlSession.close();
			}
        	System.out.println(order);
        }
}  