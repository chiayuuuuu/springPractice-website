package com.ctbcbank.biglab.biglab.api;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.ctbcbank.biglab.biglab.bean.AttractionBean;
import com.ctbcbank.biglab.biglab.bean.NewTaipei;
import com.ctbcbank.biglab.biglab.bean.Taipei;

import lombok.extern.slf4j.Slf4j;
@Slf4j
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
		for (String data : dataList) {
			JSONObject object = new JSONObject(data);
			JSONArray dataArray = object.getJSONArray("data");
			for (int i = 0; i < dataArray.length(); i++) {
				JSONObject dataObject = (JSONObject) dataArray.get(i);
				AttractionBean bean = new AttractionBean();
				bean.setCounty(dataObject.getString("address").replace(" ", "").substring(3, 6));
				bean.setName(dataObject.getString("name"));
				bean.setDistrict(dataObject.getString("district"));
				bean.setAddress(dataObject.getString("address"));
				bean.setTelephone(dataObject.getString("tel").replace("+886-", "0"));
				bean.setDescription(dataObject.getString("introduction").replace("&nbsp;", "").replace("&mdash;", "—")
						.replace("&ldquo;", "“").replace("&rdquo;", "”"));
				bean.setInfo(dataObject.getString("summary"));
				bean.setOpenTime(dataObject.getString("open_time"));
//				log.info("i={}, bean={}",i,bean.getName());
//				break;
				allBeanList.add(bean);
			}
		}
		return allBeanList;
	}

	public List<String> getAllTainanDistrict(List<AttractionBean> beanList) {
		List<String> districtList = new ArrayList<>();
		for (AttractionBean bean : beanList) {
			if (!districtList.contains(bean.getDistrict())) {
				districtList.add(bean.getDistrict());
			}
		}
		return districtList;
	}

	public List<String> getAllTaipeiDistrict(List<Taipei> beanList) {
		List<String> districtList = new ArrayList<>();
		for (Taipei bean : beanList) {
			if (!districtList.contains(bean.getDistrict())) {
				districtList.add(bean.getDistrict());
			}
		}
		return districtList;
	}
	
	public List<String> getAllNewTaipeiDistrict(List<NewTaipei> beanList) {
		List<String> districtList = new ArrayList<>();
		for (NewTaipei bean : beanList) {
			if (!districtList.contains(bean.getDistrict())) {
				districtList.add(bean.getDistrict());
			}
		}
		return districtList;
	}

	public String readTxt(File file){
		String data = "";
		InputStreamReader fs = null;
		BufferedReader bfin = null;
		try {
			fs = new InputStreamReader(new FileInputStream(file));
		    bfin = new BufferedReader(fs);
		    String line = null;
			while ((line = bfin.readLine()) != null) {
				data += line;
			}
		} catch (Exception e) {
			log.error(e.toString());
		}finally{
			try {if(fs!=null)fs.close();} catch (IOException e) {e.printStackTrace();}
			try {if(bfin!=null)bfin.close();} catch (IOException e) {e.printStackTrace();}
		}//read try end
		return data;
	}

	
	public List<Taipei> parseTaipeiData(String data) {
		List<Taipei> TaipeiList = new ArrayList<>();

		JSONObject object = new JSONObject(data);
		JSONArray dataArray = object.getJSONArray("data");
		for (int i = 0; i < dataArray.length(); i++) {
			JSONObject dataObject = (JSONObject) dataArray.get(i);
			Taipei bean = new Taipei();
			if(dataObject.getString("address").contains("臺北市")) {
				bean.setCounty(dataObject.getString("address").replace(" ", "").substring(3, 6));
				bean.setName(dataObject.getString("name"));
				bean.setDistrict(dataObject.getString("distric"));
				bean.setAddress(dataObject.getString("address"));
				bean.setTelephone(dataObject.getString("tel").replace("+886-", "0"));
				bean.setDescription(dataObject.getString("introduction"));
//					.replace("&ldquo;", "“").replace("&rdquo;", "”"));
				bean.setInfo(dataObject.getString("url"));
				bean.setOpenTime(dataObject.getString("open_time"));
//				log.info("i={}, bean={}",i,bean.getName());
////				break;
				TaipeiList.add(bean);
			}else {
				continue;
			}
		}
		log.info("Taipei size={}",TaipeiList.size());
		return TaipeiList;
	}
	
	public List<NewTaipei> parseNewTaipeiData(String data) {
		List<NewTaipei> NewTaipeiList = new ArrayList<>();
		JSONObject object = new JSONObject(data);
		JSONArray dataArray = object.getJSONArray("data");
		for (int i = 0; i < dataArray.length(); i++) {
			JSONObject dataObject = (JSONObject) dataArray.get(i);
			NewTaipei bean = new NewTaipei();
			if(dataObject.getString("address").contains("新北市")) {
				bean.setCounty(dataObject.getString("address").replace(" ", "").substring(3, 6));
				bean.setName(dataObject.getString("name"));
				bean.setDistrict(dataObject.getString("distric"));
				bean.setAddress(dataObject.getString("address"));
				bean.setTelephone(dataObject.getString("tel").replace("+886-", "0"));
				bean.setDescription(dataObject.getString("introduction"));
				bean.setInfo(dataObject.getString("url"));
				bean.setOpenTime(dataObject.getString("open_time"));
				NewTaipeiList.add(bean);
			}else {
				continue;
			}
		}
		log.info("NewTaipei size={}",NewTaipeiList.size());
		return NewTaipeiList;
	}

}
