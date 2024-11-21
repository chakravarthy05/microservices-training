package com.slokam.rec.repo;
				import org.springframework.data.jpa.repository.JpaRepository;
				import org.springframework.stereotype.Repository;
				import com.slokam.rec.entity.Shortlisted;
				@Repository
				public interface ShortlistedRepo extends JpaRepository<Shortlisted, Integer> {

				}
