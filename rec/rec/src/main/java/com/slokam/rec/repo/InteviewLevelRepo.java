package com.slokam.rec.repo;
				import org.springframework.data.jpa.repository.JpaRepository;
				import org.springframework.stereotype.Repository;
				import com.slokam.rec.entity.InteviewLevel;
				@Repository
				public interface InteviewLevelRepo extends JpaRepository<InteviewLevel, Integer> {

				}
