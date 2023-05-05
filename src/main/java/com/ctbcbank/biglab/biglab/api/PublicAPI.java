package com.ctbcbank.biglab.biglab.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.ctbcbank.biglab.biglab.bean.AttractionBean;
import com.ctbcbank.biglab.biglab.bean.PublicAPIBean;

public class PublicAPI {
	public List<AttractionBean> getPublicAPIData(String uri) {
		List<String> dataList = new ArrayList<>();
		ResponseEntity<String> responseEntity = null;
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders header = new HttpHeaders();
		HttpEntity<String> requestEntity = new HttpEntity<String>("body", header);
		responseEntity = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, String.class);
		String response = responseEntity.getBody(); 
		dataList.add(response);
//		log.info("size={}",dataList.size());
		List<AttractionBean> beanList = parseData(dataList);
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
				bean.setDescription(dataObject.getString("introduction").replace("&nbsp;","").replace("&mdash;", "—").replace("&ldquo;","“").replace("&rdquo;", "”")); 
				bean.setInfo(dataObject.getString("summary")); 
				bean.setOpenTime(dataObject.getString("open_time"));
//				log.info("i={}, bean={}",i,bean.getName());
//				break;
				allBeanList.add(bean);
			}
		}
		return allBeanList;
	}
	
	public List<String> getAllDistrict(List<AttractionBean> beanList) {
		List<String> districtList = new ArrayList<>();
		for(AttractionBean bean : beanList) {
			if(!districtList.contains(bean.getDistrict())) {
				districtList.add(bean.getDistrict());				
			}
		}
		return districtList;
	}
	
//	public String getMapValue(Map<String, List<String>> districtMap) {
//		for(String key: districtMap.keySet()) {
//			for(int i = 0; i < districtMap.values().size(); i++) {
//				String value = districtMap.get(key).get(i);	
//				return value;
//			}
//		}
//		return null;
//	}
}
