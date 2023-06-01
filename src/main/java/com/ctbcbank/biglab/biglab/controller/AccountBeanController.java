package com.ctbcbank.biglab.biglab.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ctbcbank.biglab.biglab.bean.AccountBean;
import com.ctbcbank.biglab.biglab.service.AccountBeanService;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/api/account")
public class AccountBeanController {
	@Autowired
	private AccountBeanService accountBeanService;

	@GetMapping("/all")
	public List<AccountBean> getAllAccount() {
		return accountBeanService.getAllAttraction();
	}

	@PostMapping("/status")
	public ResponseEntity<?> getAccountStatus(@RequestBody AccountBean bean) {
		log.info("userid={},userpwd={}",bean.getUid(),bean.getPassword());
		return new ResponseEntity<>(accountBeanService.getAccountStatus(bean.getUid(), bean.getPassword()),
				HttpStatus.OK);
	}

}
