package com.ctbcbank.biglab.biglab.controller;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ctbcbank.biglab.biglab.bean.AttractionBean;
import com.ctbcbank.biglab.biglab.repository.AttractionBeanRepository;
import com.ctbcbank.biglab.biglab.service.AttractionBeanService;
import com.ctbcbank.biglab.biglab.service.PublicAPIBeanService;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
@Slf4j
public class ApiDataController {
	List<String> dataList = new ArrayList<>();
	@Getter
	List<AttractionBean> beanList = new ArrayList<>();
	@Autowired
	private AttractionBeanService attractionBeanService;
	@Autowired
	private PublicAPIBeanService apiBeanService;

	@GetMapping("/allData")
	public ResponseEntity<?> getData() {
		ResponseEntity<String> responseEntity = null;
		RestTemplate restTemplate = new RestTemplate();
//		String uri = "https://api.kcg.gov.tw/api/service/get/9c8e1450-e833-499c-8320-29b36b7ace5c";
		String uri = "https://opengov.tainan.gov.tw/OpenApi/api/service/Get/bb7ba3ff-1974-4400-9a41-2aa803045550";
		HttpHeaders header = new HttpHeaders();
		HttpEntity<String> requestEntity = new HttpEntity<String>("body", header);
		responseEntity = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, String.class);
		String response = responseEntity.getBody();
		dataList.add(response);
		log.info("size={}", dataList.size());
		this.beanList = parseData(dataList);
//		log.info("{}",beanList.toString());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	public List<AttractionBean> parseData(List<String> dataList) {
		AttractionBean bean = new AttractionBean();
		List<AttractionBean> allBeanList = new ArrayList<>();
		for (String data : dataList) {
			JSONObject object = new JSONObject(data);
			JSONArray dataArray = object.getJSONArray("data");
			for (int i = 0; i < dataArray.length(); i++) {
				JSONObject dataObject = (JSONObject) dataArray.get(i);
				bean.setName(dataObject.getString("name"));
				bean.setDistrict(dataObject.getString("district"));
				bean.setAddress(dataObject.getString("address"));
//				bean.setDescription(dataObject.getString("introduction").replace("&nbsp;", "").replace("&mdash;", "—"));
				bean.setInfo(dataObject.getString("summary"));
				bean.setOpenTime(dataObject.getString("open_time"));
//				log.info("bean={}",bean);
//				break;
				allBeanList.add(bean);
			}
		}
		return allBeanList;
	}

	@PostMapping("")
	public void saveAttraction() {
//		apiBeanService.saveApiData(beanList);
	}
	
	

}
