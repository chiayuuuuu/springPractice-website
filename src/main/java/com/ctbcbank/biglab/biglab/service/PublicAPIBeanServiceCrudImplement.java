package com.ctbcbank.biglab.biglab.service;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ctbcbank.biglab.biglab.bean.AttractionBean;
import com.ctbcbank.biglab.biglab.bean.PublicAPIBean;
import com.ctbcbank.biglab.biglab.repository.AttractionBeanRepository;
import com.ctbcbank.biglab.biglab.repository.PublicAPIRepository;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class PublicAPIBeanServiceCrudImplement implements PublicAPIBeanService{
	@Autowired
	private AttractionBeanRepository repository;
	@Autowired
	private PublicAPIRepository apiRepository;
	List<String> dataList = new ArrayList<>();
	List<AttractionBean> beanList = new ArrayList<>();
	@Override
	public List<AttractionBean> getPublicAPIData(String uri) {
		ResponseEntity<String> responseEntity = null;
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders header = new HttpHeaders();
		HttpEntity<String> requestEntity = new HttpEntity<String>("body", header);
		responseEntity = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, String.class);
		String response = responseEntity.getBody(); 
		dataList.add(response);
//		log.info("size={}",dataList.size());
		beanList = parseData(dataList);
		return beanList;

	}
	
	public List<AttractionBean> parseData(List<String> dataList) {
		List<AttractionBean> allBeanList = new ArrayList<>();
		for(String data : dataList) {
			JSONObject object = new JSONObject(data);
			JSONArray dataArray = object.getJSONArray("data");
			for(int i = 0; i < dataArray.length(); i++) {
				JSONObject dataObject = (JSONObject) dataArray.get(i);
				AttractionBean bean = new AttractionBean();
				bean.setCounty(dataObject.getString("address").replace(" ", "").substring(3,6));
				bean.setName(dataObject.getString("name"));
				bean.setDistrict(dataObject.getString("district"));
				bean.setAddress(dataObject.getString("address"));
				bean.setTelephone(dataObject.getString("tel").replace("+886-", "0"));
				bean.setDescription(dataObject.getString("introduction").replace("&nbsp;","").replace("&mdash;", "—")); 
				bean.setInfo(dataObject.getString("summary"));
				bean.setOpenTime(dataObject.getString("open_time"));
//				log.info("i={}, bean={}",i,bean.getName());
//				break;
				allBeanList.add(bean);
			}
		}
		return allBeanList;
	}

	@Override
	public void saveApiData(List<AttractionBean> beanList) {
		log.info("saveApiData size={}",beanList.size());
		for(AttractionBean bean : beanList) {
//			log.info("bean service={}",beanList.get(i).getName());
			repository.save(bean);
		}
	}

	@Override
	public PublicAPIBean getPublicAPIBeanById(long id) {
		PublicAPIBean bean = apiRepository.findById(id).get();
		return bean;
	}

	@Override
	public void removePublicAPIBeanById(long id) {
		apiRepository.deleteById(id);
	}

	@Override
	public List<PublicAPIBean> getAllPublicAPI() {
		List<PublicAPIBean> publicAPIBeanList = new ArrayList<>();
		for(PublicAPIBean bean : apiRepository.findAll()) {
			publicAPIBeanList.add(bean);
		}
		return publicAPIBeanList;
	}

	@Override
	public PublicAPIBean createOrUpdatedAPI(PublicAPIBean bean) {
		return apiRepository.save(bean);
	}


}
