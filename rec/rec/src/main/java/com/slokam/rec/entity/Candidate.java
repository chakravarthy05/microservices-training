package com.slokam.rec.entity; 
 import java.io.Serializable; 
 import javax.persistence.*; 
 import java.util.*; 
@Entity 
public class Candidate implements Serializable { 

@Id 
 @GeneratedValue 
  private Integer  id;
  private String  name;
   @ManyToOne() 
@JoinColumn(name="fkid27") 
   private Qualification  qual;
  private Integer  exp;

 @ManyToMany 
   @JoinTable(name="fkTable28",
   joinColumns = {@JoinColumn(name="fkColumn29")},
   inverseJoinColumns = {@JoinColumn(name="fkColumn30")})  private List<Skill>  skillset;
  private String  email;
  private Long  phone;
public Integer getId() {
		return id;
	}
public void setId(Integer id) {
		this.id = id;
	}
public String getName() {
		return name;
	}
public void setName(String name) {
		this.name = name;
	}
public Qualification getQual() {
		return qual;
	}
public void setQual(Qualification qual) {
		this.qual = qual;
	}
public Integer getExp() {
		return exp;
	}
public void setExp(Integer exp) {
		this.exp = exp;
	}
public List<Skill> getSkillset() {
		return skillset;
	}
public void setSkillset(List<Skill> skillset) {
		this.skillset = skillset;
	}
public String getEmail() {
		return email;
	}
public void setEmail(String email) {
		this.email = email;
	}
public Long getPhone() {
		return phone;
	}
public void setPhone(Long phone) {
		this.phone = phone;
	}
}