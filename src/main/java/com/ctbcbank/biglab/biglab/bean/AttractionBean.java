package com.ctbcbank.biglab.biglab.bean;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class AttractionBean {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String county;
	private String district;
	private String name;
	private String address;
	private String telephone;
	private String openTime;
	@Column(columnDefinition = "TEXT", length = 2000)
	private String description;
	@Column(columnDefinition = "TEXT", length = 1000)
	private String info;
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

	@Override
	public String toString() {
		return "AttractionBean [id=" + id + ", county=" + county + ", name=" + name + ", district=" + district
				+ ", address=" + address + ", openTime=" + openTime + "]";
	}
	
	

	


}
