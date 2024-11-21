package com.slokam.rec.repo;
				import org.springframework.data.jpa.repository.JpaRepository;
				import org.springframework.stereotype.Repository;
				import com.slokam.rec.entity.Application;
				@Repository
				public interface ApplicationRepo extends JpaRepository<Application, Integer> {

				}
