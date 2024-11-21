package com.slokam.rec.repo;
				import org.springframework.data.jpa.repository.JpaRepository;
				import org.springframework.stereotype.Repository;
				import com.slokam.rec.entity.Joining;
				@Repository
				public interface JoiningRepo extends JpaRepository<Joining, Integer> {

				}
