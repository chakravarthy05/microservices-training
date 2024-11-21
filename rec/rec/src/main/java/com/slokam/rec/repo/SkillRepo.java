package com.slokam.rec.repo;
				import org.springframework.data.jpa.repository.JpaRepository;
				import org.springframework.stereotype.Repository;
				import com.slokam.rec.entity.Skill;
				@Repository
				public interface SkillRepo extends JpaRepository<Skill, Integer> {

				}
