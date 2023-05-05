package com.ctbcbank.biglab.biglab.runner;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ctbcbank.biglab.biglab.api.PublicAPI;
import com.ctbcbank.biglab.biglab.bean.AttractionBean;
import com.ctbcbank.biglab.biglab.bean.DistrictBean;
import com.ctbcbank.biglab.biglab.bean.Kaohsiung;
import com.ctbcbank.biglab.biglab.bean.PublicAPIBean;
import com.ctbcbank.biglab.biglab.bean.Taipei;
import com.ctbcbank.biglab.biglab.repository.AttractionBeanRepository;
import com.ctbcbank.biglab.biglab.repository.DistrictBeanRepository;
import com.ctbcbank.biglab.biglab.repository.KaohsiungRepository;
import com.ctbcbank.biglab.biglab.repository.PublicAPIRepository;
import com.ctbcbank.biglab.biglab.repository.TaipeiRepository;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
public class AttractionRunnable implements CommandLineRunner{
	@Autowired
	private AttractionBeanRepository repository;
	@Autowired
	private PublicAPIRepository apiRepository;
	@Autowired
	private DistrictBeanRepository districtRepository;
	@Autowired
	private TaipeiRepository taipeiRepository;
	@Autowired
	private KaohsiungRepository kaohsiungRepository;
	@Override
	public void run(String... args) throws Exception {
		List<PublicAPIBean> apiList = new ArrayList<>();
		PublicAPIBean apiBean = new PublicAPIBean();
		PublicAPIBean apiBean2 = new PublicAPIBean();
		PublicAPIBean apiBean3 = new PublicAPIBean();
		apiBean.setCounty("臺南市");
		apiBean.setCounty_eng("Tainan");
		apiBean.setUrl("https://opengov.tainan.gov.tw/OpenApi/api/service/Get/bb7ba3ff-1974-4400-9a41-2aa803045550");
		apiBean2.setCounty("高雄市");
		apiBean2.setCounty_eng("Kaohsiung");
		apiBean2.setUrl("https://api.kcg.gov.tw/api/service/get/9c8e1450-e833-499c-8320-29b36b7ace5c");
		apiBean3.setCounty("台北市");
		apiBean3.setCounty_eng("Taipei");

		apiRepository.save(apiBean);
		apiRepository.save(apiBean2);
		apiRepository.save(apiBean3);
		
		// 取得所有api資料
		for(PublicAPIBean bean : apiRepository.findAll()) {
			apiList.add(bean);
		}
//		log.info("apiList={}",apiList);
		PublicAPI api = new PublicAPI();
		List<AttractionBean> beanList = api.getPublicAPIData("https://opengov.tainan.gov.tw/OpenApi/api/service/Get/bb7ba3ff-1974-4400-9a41-2aa803045550");
		
		List<String> districtList = api.getAllDistrict(beanList);
		log.info("All publicAPIData amount = {}", beanList.size());
		for(AttractionBean bean : beanList) {
			repository.save(bean);		
		}
//		log.info("district= {}",districtList.toString());
		for(String district : districtList) {
			DistrictBean districtBean = new DistrictBean();
			districtBean.setCounty(apiBean.getCounty());
			districtBean.setDistrict(district);
			districtRepository.save(districtBean);
		}
		
		Taipei taipei = new Taipei();
		taipei.setCounty("台北市");
		taipei.setDistrict("北投區");
//		taipei.setCounty_eng("Taipei");
		Taipei taipei2 = new Taipei();
		taipei2.setCounty("台北市");
		taipei2.setDistrict("士林區");
//		taipei2.setCounty_eng("Taipei");
		taipeiRepository.save(taipei);
		taipeiRepository.save(taipei2);
		Kaohsiung kaohsiung = new Kaohsiung();
		Kaohsiung kaohsiung2 = new Kaohsiung();
		kaohsiung.setCounty("高雄市");
		kaohsiung.setDistrict("燕巢區");
//		kaohsiung.setCounty_eng("Kaohsiung");
		kaohsiung2.setCounty("高雄市");
		kaohsiung2.setDistrict("岡山區");
//		kaohsiung2.setCounty_eng("Kaohsiung");
		kaohsiungRepository.save(kaohsiung);
		kaohsiungRepository.save(kaohsiung2);
	}
	
//	public static List<AttractionBean> getPublicAPIData(String uri) {
//		List<String> dataList = new ArrayList<>();
//		ResponseEntity<String> responseEntity = null;
//		RestTemplate restTemplate = new RestTemplate();
//		HttpHeaders header = new HttpHeaders();
//		HttpEntity<String> requestEntity = new HttpEntity<String>("body", header);
//		responseEntity = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, String.class);
//		String response = responseEntity.getBody(); 
//		dataList.add(response);
////		log.info("size={}",dataList.size());
//		beanList = parseData(dataList);
//		return beanList;
//
//	}
//	
//	public static List<AttractionBean> parseData(List<String> dataList) {
//		List<AttractionBean> allBeanList = new ArrayList<>();
//		for(String data : dataList) {
//			JSONObject object = new JSONObject(data);
//			JSONArray dataArray = object.getJSONArray("data");
//			for(int i = 0; i < dataArray.length(); i++) {
//				JSONObject dataObject = (JSONObject) dataArray.get(i);
//				AttractionBean bean = new AttractionBean();
//				bean.setCounty(dataObject.getString("address").replace(" ", "").substring(3,6));
//				bean.setName(dataObject.getString("name"));
//				bean.setDistrict(dataObject.getString("district"));
//				bean.setAddress(dataObject.getString("address"));
//				bean.setTelephone(dataObject.getString("tel").replace("+886-", "0"));
//				bean.setDescription(dataObject.getString("introduction").replace("&nbsp;","").replace("&mdash;", "—")); 
//				bean.setInfo(dataObject.getString("summary"));
//				bean.setOpenTime(dataObject.getString("open_time"));
////				log.info("i={}, bean={}",i,bean.getName());
////				break;
//				allBeanList.add(bean);
//			}
//		}
//		return allBeanList;
//	}
}
