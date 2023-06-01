package com.ctbcbank.biglab.biglab.service;

import java.util.List;

import com.ctbcbank.biglab.biglab.bean.AccountBean;

public interface AccountBeanService {
	List<AccountBean> getAllAttraction();
	List<String> getAccountStatus(String uid, String pwd);
}
