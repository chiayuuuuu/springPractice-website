package com.ctbcbank.biglab.biglab.bean;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class PublicAPIBean {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String county;
	private String county_eng;
	private String url;
	private Date createdAt;
	private Date updatedAt;
	@PrePersist
	public void createDate() {
		this.createdAt = new Date();
	}

	@PreUpdate
	public void updateDate() {
		this.updatedAt = new Date();
	}
}
