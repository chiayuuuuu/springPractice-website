package com.ctbcbank.biglab.biglab.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctbcbank.biglab.biglab.bean.AccountBean;
import com.ctbcbank.biglab.biglab.repository.AccountBeanRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AccountBeanServiceCrudImplement implements AccountBeanService {
	@Autowired
	private AccountBeanRepository repository;

	@Override
	public List<AccountBean> getAllAttraction() {
		List<AccountBean> accountList = new ArrayList<>();
		for (AccountBean bean : repository.findAll()) {
			accountList.add(bean);
		}
		return accountList;
	}

	@Override
	public List<String> getAccountStatus(String uid, String pwd) {
		log.info("uid={},pwd={}",uid,pwd);
		String status = "";
		List<String> statusList = new ArrayList<>();
		for (AccountBean bean : repository.findAll()) {
			if (bean.getUid().equals("user") && bean.getUid().equals(uid) && bean.getPassword().equals(pwd)) {
				status = "user success";
			}else if (bean.getUid().equals("user") && bean.getUid().equals(uid)
					&& !bean.getPassword().equals(pwd)) {
				status = "user's password wrong";
			}else if (bean.getUid().equals("admin") && bean.getUid().equals(uid)
					&& bean.getPassword().equals(pwd)) {
				status = "admin success";
			}else if (bean.getUid().equals("admin") && bean.getUid().equals(uid)
					&& !bean.getPassword().equals(pwd)) {
				status = "admin's password wrong";
			}
			if(!status.equals(""))break;
		}
		if(status.equals("")) {
			status = "wrong";
		}
		statusList.add(status);
		return statusList;
	}

}
