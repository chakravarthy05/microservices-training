package com.slokam.rec.entity; 
 import java.io.Serializable; 
 import javax.persistence.*; 
 import java.util.*; 
@Entity 
public class Joining implements Serializable { 

@Id 
 @GeneratedValue 
  private Integer  id;
   @ManyToOne() 
@JoinColumn(name="fkid41") 
   private Candidate  candidate;
   @ManyToOne() 
@JoinColumn(name="fkid42") 
   private Location  location;
  private Date  time;
  private String  empid;
public Integer getId() {
		return id;
	}
public void setId(Integer id) {
		this.id = id;
	}
public Candidate getCandidate() {
		return candidate;
	}
public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}
public Location getLocation() {
		return location;
	}
public void setLocation(Location location) {
		this.location = location;
	}
public Date getTime() {
		return time;
	}
public void setTime(Date time) {
		this.time = time;
	}
public String getEmpid() {
		return empid;
	}
public void setEmpid(String empid) {
		this.empid = empid;
	}
}