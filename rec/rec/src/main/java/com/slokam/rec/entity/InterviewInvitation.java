package com.slokam.rec.entity; 
 import java.io.Serializable; 
 import javax.persistence.*; 
 import java.util.*; 
@Entity 
public class InterviewInvitation implements Serializable { 

@Id 
 @GeneratedValue 
  private Integer  id;
   @ManyToOne() 
@JoinColumn(name="fkid33") 
   private Candidate  candidate;
   @ManyToOne() 
@JoinColumn(name="fkid34") 
   private Location  location;
  private Date  time;
   @ManyToOne() 
@JoinColumn(name="fkid35") 
   private InteviewLevel  level;
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
public InteviewLevel getLevel() {
		return level;
	}
public void setLevel(InteviewLevel level) {
		this.level = level;
	}
}