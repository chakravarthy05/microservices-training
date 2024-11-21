package com.slokam.rec.entity; 
 import java.io.Serializable; 
 import javax.persistence.*; 
 import java.util.*; 
@Entity 
public class Openning implements Serializable { 

@Id 
 @GeneratedValue 
  private Integer  id;
   @ManyToOne() 
@JoinColumn(name="fkid22") 
   private Position  position;
  private Integer  noofope;
   @ManyToOne() 
@JoinColumn(name="fkid23") 
   private Qualification  qual;

 @ManyToMany 
   @JoinTable(name="fkTable24",
   joinColumns = {@JoinColumn(name="fkColumn25")},
   inverseJoinColumns = {@JoinColumn(name="fkColumn26")})  private List<Skill>  skillset;
  private Integer  exp;
public Integer getId() {
		return id;
	}
public void setId(Integer id) {
		this.id = id;
	}
public Position getPosition() {
		return position;
	}
public void setPosition(Position position) {
		this.position = position;
	}
public Integer getNoofope() {
		return noofope;
	}
public void setNoofope(Integer noofope) {
		this.noofope = noofope;
	}
public Qualification getQual() {
		return qual;
	}
public void setQual(Qualification qual) {
		this.qual = qual;
	}
public List<Skill> getSkillset() {
		return skillset;
	}
public void setSkillset(List<Skill> skillset) {
		this.skillset = skillset;
	}
public Integer getExp() {
		return exp;
	}
public void setExp(Integer exp) {
		this.exp = exp;
	}
}