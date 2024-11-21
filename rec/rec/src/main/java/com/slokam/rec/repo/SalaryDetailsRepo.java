package com.slokam.rec.repo;
				import org.springframework.data.jpa.repository.JpaRepository;
				import org.springframework.stereotype.Repository;
				import com.slokam.rec.entity.SalaryDetails;
				@Repository
				public interface SalaryDetailsRepo extends JpaRepository<SalaryDetails, Integer> {

				}
