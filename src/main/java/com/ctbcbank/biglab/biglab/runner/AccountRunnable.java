package com.ctbcbank.biglab.biglab.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ctbcbank.biglab.biglab.bean.AccountBean;
import com.ctbcbank.biglab.biglab.repository.AccountBeanRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AccountRunnable implements CommandLineRunner {
	@Autowired
	private AccountBeanRepository repository;

	@Override
	public void run(String... args) throws Exception {
		AccountBean user = new AccountBean();
		AccountBean admin = new AccountBean();
		user.setUid("user");
		user.setPassword("user");
		admin.setUid("admin");
		admin.setPassword("admin");
		repository.save(user);
		repository.save(admin);
	}


}
