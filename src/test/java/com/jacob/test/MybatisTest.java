package com.jacob.test;

import com.jacob.dao.IAccountDao;
import com.jacob.dao.IRoleDao;
import com.jacob.dao.IUserDao;
import com.jacob.domain.*;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
            System.out.println("--------------每个用户的信息---------------");
            System.out.println(user);
//            System.out.println(user.getAccounts());
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
        System.out.println("保存操作之前：" + user);
        userDao.saveUser(user);
        //这已经将id直接赋值给我们当前的user了
        System.out.println("保存操作之后:" + user);
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

    @Test
    public void testFindByVo() {
        IUserDao iUserDao = sqlSession.getMapper(IUserDao.class);
        QureyVo vo = new QureyVo();
        User user = new User();
        user.setUsername("%王%");
        vo.setUser(user);
        List<User> users = iUserDao.findByVo(vo);
        for (User user1 : users) {
            System.out.println(user1);
        }
    }

    @Test
    public void testFindInIds() {
        IUserDao iUserDao = sqlSession.getMapper(IUserDao.class);
        QureyVo vo = new QureyVo();
        List<Integer> list = new ArrayList<Integer>();
        list.add(41);
        list.add(42);
        list.add(43);
        vo.setIds(list);
        List<User> users = iUserDao.findUserInIds(vo);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testFindAllAccount() {
        IAccountDao accountDao = sqlSession.getMapper(IAccountDao.class);
        List<Account> accounts = accountDao.findAll();
        for (Account account : accounts) {
            System.out.println("----------------每个Account的信息------------------");
            System.out.println(account);
            System.out.println(account.getUser());
        }
    }
    @Test
    public void testFindAllAccountUser() {
        IAccountDao accountDao = sqlSession.getMapper(IAccountDao.class);
        List<AccountUser> accounts = accountDao.findAllAccount();
        for (AccountUser au : accounts) {
            System.out.println(au);
        }
    }
    @Test
    public void testFindAllRole() {
        IRoleDao roleDao = sqlSession.getMapper(IRoleDao.class);
        List<Role> roles = roleDao.findAll();
        for (Role role : roles) {
            System.out.println(role);
        }
    }
}
