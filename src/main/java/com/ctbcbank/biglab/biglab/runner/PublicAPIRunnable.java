package com.ctbcbank.biglab.biglab.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ctbcbank.biglab.biglab.bean.PublicAPIBean;
import com.ctbcbank.biglab.biglab.repository.PublicAPIRepository;

@Component
public class PublicAPIRunnable implements CommandLineRunner{
	@Autowired
	private PublicAPIRepository repository;
	
	@Override
	public void run(String... args) throws Exception {
//		PublicAPIBean bean = new PublicAPIBean();
//		PublicAPIBean bean2 = new PublicAPIBean();
//		bean.setCounty("台南市");
//		bean.setUrl("https://opengov.tainan.gov.tw/OpenApi/api/service/Get/bb7ba3ff-1974-4400-9a41-2aa803045550");
//		bean2.setCounty("高雄市");
//		bean2.setUrl("https://api.kcg.gov.tw/api/service/get/9c8e1450-e833-499c-8320-29b36b7ace5c");
//		repository.save(bean);
//		repository.save(bean2);
	}
	
}
