package com.slokam.rec.entity; 
 import java.io.Serializable; 
 import javax.persistence.*; 
 import java.util.*; 
@Entity 
public class Application implements Serializable { 

@Id 
 @GeneratedValue 
  private Integer  id;
   @ManyToOne() 
@JoinColumn(name="fkid31") 
   private Candidate  candidate;
   @ManyToOne() 
@JoinColumn(name="fkid32") 
   private Openning  openning;
  private Date  time;
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
public Openning getOpenning() {
		return openning;
	}
public void setOpenning(Openning openning) {
		this.openning = openning;
	}
public Date getTime() {
		return time;
	}
public void setTime(Date time) {
		this.time = time;
	}
}