package com.slokam.rec.repo;
				import org.springframework.data.jpa.repository.JpaRepository;
				import org.springframework.stereotype.Repository;
				import com.slokam.rec.entity.Candidate;
				@Repository
				public interface CandidateRepo extends JpaRepository<Candidate, Integer> {

				}
