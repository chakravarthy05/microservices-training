package com.slokam.rec.repo;
				import org.springframework.data.jpa.repository.JpaRepository;
				import org.springframework.stereotype.Repository;
				import com.slokam.rec.entity.Openning;
				@Repository
				public interface OpenningRepo extends JpaRepository<Openning, Integer> {

				}
