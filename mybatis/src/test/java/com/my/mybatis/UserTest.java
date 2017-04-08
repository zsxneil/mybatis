package com.my.mybatis;

import java.io.IOException;  
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
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

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.my.mybatis.mapper.UserMapper;
import com.my.mybatis.model.User;  
   
   
public class UserTest {  
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
                   testMapper(sqlSession);
         } 
         
         public void testMapper(SqlSession sqlSession) {
        	 UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        	 PageHelper.startPage(1, 10);
        	 List<User> userList = userMapper.findUserByName("王五");
        	 PageInfo<User> page = new PageInfo<User>((List<User>)userList);
        	 
        	 System.out.println(page);
        	 /*if (userList != null && userList.size() > 0) {
				for (User user : userList) {
					System.out.println(user);
				}
			}*/
         }
         
         public void testDelete(SqlSession sqlSession) {
			sqlSession.delete("com.my.mybatis.mapper.UserMapper.deleteUser", 15);
			sqlSession.commit();
			sqlSession.close();
		}
         
         public void testUpdate(SqlSession session) {
        	 User user = new User();
        	 user.setId(15);
        	 user.setUsername("测试1");
        	 user.setSex("1");
        	 user.setBirthday(new Date());
        	 user.setAddress("深圳市1");
        	 try{ 
        		 session.update("com.my.mybatis.mapper.UserMapper.updateUser", user);
             }catch (Exception e) {  
                 e.printStackTrace();  
             }finally{  
                 //关闭sqlSession  
                 //这里放在try/catch语句的finally确保查询之后能关闭sqlSession
            	 session.commit();
            	 session.close();  
             }  
		}
         
         public void testInsertList(SqlSession sqlSession) {
        	 User user = new User();
        	 user.setUsername("测试");
        	 user.setSex("1");
        	 user.setBirthday(new Date());
        	 user.setAddress("深圳市");
        	 List<User> userList = new ArrayList<User>();
        	 userList.add(user);
        	 int insertNo = 0;
        	 try{ 
        		 insertNo = sqlSession.insert("com.my.mybatis.mapper.UserMapper.insertUserList",userList);
             }catch (Exception e) {  
                 e.printStackTrace();  
             }finally{  
                 //关闭sqlSession  
                 //这里放在try/catch语句的finally确保查询之后能关闭sqlSession
            	 sqlSession.commit();
                 sqlSession.close();  
             }  
             System.out.println(insertNo);
		}
         
         public void testInsert(SqlSession sqlSession) {
        	 User user = new User();
        	 user.setUsername("测试");
        	 user.setSex("1");
        	 user.setBirthday(new Date());
        	 user.setAddress("深圳市");
        	 try{ 
        		 sqlSession.insert("com.my.mybatis.mapper.UserMapper.insertUser",user);
             }catch (Exception e) {  
                 e.printStackTrace();  
             }finally{  
                 //关闭sqlSession  
                 //这里放在try/catch语句的finally确保查询之后能关闭sqlSession
            	 sqlSession.commit();
                 sqlSession.close();  
             }  
             System.out.println(user.getId());
		}
         
         public void testRetObjectList(SqlSession sqlSession) {
        	 List<User> users = null;
        	 try{  
        		 users = sqlSession.selectList("com.my.mybatis.mapper.UserMapper.findUserByName", "王五");  
             }catch (Exception e) {  
                 e.printStackTrace();  
             }finally{  
                 //关闭sqlSession  
                 //这里放在try/catch语句的finally确保查询之后能关闭sqlSession  
                 sqlSession.close();  
             }  
             System.out.println(users.size());
         }
         
         public void testRetObject(SqlSession sqlSession) {
        	 User user = null;
        	 try{  
        		 user = sqlSession.selectOne("com.my.mybatis.mapper.UserMapper.findUserById", 2);  
             }catch (Exception e) {  
                 e.printStackTrace();  
             }finally{  
                 //关闭sqlSession  
                 //这里放在try/catch语句的finally确保查询之后能关闭sqlSession  
                 sqlSession.close();  
             }  
             System.out.println(user);
         }
         
        public void testRetMap(SqlSession sqlSession) {
        	//通过sqlSession操作数据库  
            //第一个参数：statment的位置，等于namespace+statment的id  
            //第二个参数：传入的参数  
            Map<String, Object> ret = null;
            try{  
                ret = sqlSession.selectOne("com.my.mybatis.mapper.UserMapper.selectUser", 1);  
            }catch (Exception e) {  
                e.printStackTrace();  
            }finally{  
                //关闭sqlSession  
                //这里放在try/catch语句的finally确保查询之后能关闭sqlSession  
                sqlSession.close();  
            }  
            System.out.println(ret.size());  
            Set<String> keySet = ret.keySet();
            for (String key : keySet) {
				System.out.println(key + ":" + ret.get(key));
			}
        }
}  