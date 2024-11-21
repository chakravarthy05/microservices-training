package com.slokam.rec.repo;
				import org.springframework.data.jpa.repository.JpaRepository;
				import org.springframework.stereotype.Repository;
				import com.slokam.rec.entity.InterviewInvitation;
				@Repository
				public interface InterviewInvitationRepo extends JpaRepository<InterviewInvitation, Integer> {

				}
