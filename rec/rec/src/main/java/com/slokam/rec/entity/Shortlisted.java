package com.slokam.rec.entity; 
 import java.io.Serializable; 
 import javax.persistence.*; 
 import java.util.*; 
@Entity 
public class Shortlisted implements Serializable { 

@Id 
 @GeneratedValue 
  private Integer  id;
   @ManyToOne() 
@JoinColumn(name="fkid36") 
   private Candidate  candidate;
   @ManyToOne() 
@JoinColumn(name="fkid37") 
   private Position  propos;
  private Double  prosal;
  private Double  expsal;
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
public Position getPropos() {
		return propos;
	}
public void setPropos(Position propos) {
		this.propos = propos;
	}
public Double getProsal() {
		return prosal;
	}
public void setProsal(Double prosal) {
		this.prosal = prosal;
	}
public Double getExpsal() {
		return expsal;
	}
public void setExpsal(Double expsal) {
		this.expsal = expsal;
	}
}