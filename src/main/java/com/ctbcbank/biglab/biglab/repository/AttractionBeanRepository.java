package com.ctbcbank.biglab.biglab.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ctbcbank.biglab.biglab.bean.AttractionBean;

@Repository
public interface AttractionBeanRepository extends CrudRepository<AttractionBean, Long>{

}
