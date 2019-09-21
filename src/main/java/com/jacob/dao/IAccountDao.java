package com.jacob.dao;

import com.jacob.domain.Account;
import com.jacob.domain.AccountUser;

import java.util.List;

public interface IAccountDao {
    /**
     * 查询所有账号，并且还要获取到当前账户的所属用户信息
     * @return
     */
    List<Account> findAll();

    List<AccountUser> findAllAccount();
}
