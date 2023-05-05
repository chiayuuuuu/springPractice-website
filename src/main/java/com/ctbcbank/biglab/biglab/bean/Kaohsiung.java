package com.ctbcbank.biglab.biglab.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Kaohsiung {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String county;
	private String district;
//	private String county_eng;
}
