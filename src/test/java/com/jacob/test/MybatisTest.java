package com.jacob.test;

import com.jacob.dao.IUserDao;
import com.jacob.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MybatisTest {
    InputStream in;
    SqlSession sqlSession;

    @Before
    public void init() throws Exception {
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        sqlSession = factory.openSession();
    }

    @After
    public void destroy() throws Exception {
        sqlSession.commit();
        sqlSession.close();
        in.close();
    }

    @Test
    public void testFindAll() {
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testSave() {
        User user = new User();
        user.setUsername("Jacob last insertID");
        user.setAddress("电子科技大学");
        Date date = new Date(97, 3, 7);
        user.setBirthday(date);
        user.setSex("男");
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        System.out.println("保存操作之前："+user);
        userDao.saveUser(user);
        //这已经将id直接赋值给我们当前的user了
        System.out.println("保存操作之后:"+user);
    }

    @Test
    public void testUpdate() {
        User user = new User();
        user.setId(50);
        user.setUsername("Jacob");
        user.setAddress("电子科技大学");
        Date date = new Date(97, 3, 7);
        user.setBirthday(date);
        user.setSex("女");
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        userDao.updateUser(user);
    }

    @Test
    public void testDelete() {
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        userDao.deleteUser(48);
    }

    @Test
    public void testFindOne() {
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        User user = userDao.findById(50);
        System.out.println(user);
    }

    @Test
    public void testFindByName() {
        IUserDao iUserDao = sqlSession.getMapper(IUserDao.class);
        //一般采用这种方法。
        List<User> users = iUserDao.findByName("%王%");
        //这种一般不采用
//        List<User> users = iUserDao.findByName("王");
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testFindTotal() {
        IUserDao iUserDao = sqlSession.getMapper(IUserDao.class);
        int count = iUserDao.findTotal();
        System.out.println(count);
    }
}
