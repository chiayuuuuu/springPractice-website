package com.ctbcbank.biglab.biglab.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ctbcbank.biglab.biglab.bean.PublicAPIBean;

@Repository
public interface PublicAPIRepository extends CrudRepository<PublicAPIBean, Long> {

}
