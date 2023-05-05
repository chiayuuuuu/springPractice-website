package com.ctbcbank.biglab.biglab.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ctbcbank.biglab.biglab.bean.AttractionBean;
import com.ctbcbank.biglab.biglab.bean.PublicAPIBean;
import com.ctbcbank.biglab.biglab.service.PublicAPIBeanService;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/publicApi")
public class PublicAPIController {
	@Autowired
	private PublicAPIBeanService apiBeanService;
	List<AttractionBean> beanList = new ArrayList<>();

//	@GetMapping("/all")
//	public void getAllAPIData() {
//		log.info("取回所有的api資料");
//		String uri = "https://opengov.tainan.gov.tw/OpenApi/api/service/Get/bb7ba3ff-1974-4400-9a41-2aa803045550";
//		this.beanList = apiBeanService.getPublicAPIData(uri);
//		log.info("All publicAPIData amount={}", beanList.size());
//		apiBeanService.saveApiData(beanList);
//	}
	
	@GetMapping("/allAPI")
	public List<PublicAPIBean> getAllPublicAPI() {
		log.info("取回所有的api");
		return apiBeanService.getAllPublicAPI();
	}
	@GetMapping("/{ApiId}")
	public ResponseEntity<?> getPublicAPIById(@PathVariable String ApiId) {
		PublicAPIBean bean = apiBeanService.getPublicAPIBeanById(Long.parseLong(ApiId)); 
		return new ResponseEntity<>(bean, HttpStatus.OK);
	}
	@PostMapping("")
	public ResponseEntity<?> createOrUpdateNewPublicAPI(@RequestBody PublicAPIBean bean) {
		if (bean.getId() == 0) {
			apiBeanService.createOrUpdatedAPI(bean);
			return new ResponseEntity<>(bean,HttpStatus.OK);

		} else {
			Date createDate = apiBeanService.getPublicAPIBeanById(bean.getId()).getCreatedAt();
			PublicAPIBean updatedBean = bean;
			updatedBean.setCreatedAt(createDate);
			apiBeanService.createOrUpdatedAPI(updatedBean);
			log.info("get updatedBean:{}", bean);
			return new ResponseEntity<>(updatedBean,HttpStatus.OK);
		}
		
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//			
//		}
	}
	
	@DeleteMapping("/{ApiId}")
	public ResponseEntity<?> removePublicAPIById(@PathVariable String ApiId) {
		apiBeanService.removePublicAPIBeanById(Long.parseLong(ApiId));
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
