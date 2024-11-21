package com.slokam.rec.entity; 
 import java.io.Serializable; 
 import javax.persistence.*; 
 import java.util.*; 
@Entity 
public class Offer implements Serializable { 

@Id 
 @GeneratedValue 
  private Integer  id;
   @ManyToOne() 
@JoinColumn(name="fkid38") 
   private Candidate  candidate;
   @ManyToOne() 
@JoinColumn(name="fkid39") 
   private Position  pos;
   @ManyToOne() 
@JoinColumn(name="fkid40") 
   private SalaryDetails  sal;
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
public Position getPos() {
		return pos;
	}
public void setPos(Position pos) {
		this.pos = pos;
	}
public SalaryDetails getSal() {
		return sal;
	}
public void setSal(SalaryDetails sal) {
		this.sal = sal;
	}
}