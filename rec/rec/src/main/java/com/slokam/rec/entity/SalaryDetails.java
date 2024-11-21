package com.slokam.rec.entity; 
 import java.io.Serializable; 
 import javax.persistence.*; 
 import java.util.*; 
@Entity 
public class SalaryDetails implements Serializable { 

@Id 
 @GeneratedValue 
  private Integer  id;
  private Double  basic;
  private Double  hra;
public Integer getId() {
		return id;
	}
public void setId(Integer id) {
		this.id = id;
	}
public Double getBasic() {
		return basic;
	}
public void setBasic(Double basic) {
		this.basic = basic;
	}
public Double getHra() {
		return hra;
	}
public void setHra(Double hra) {
		this.hra = hra;
	}
}