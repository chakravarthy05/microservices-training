package com.slokam.rec.service;

import com.slokam.rec.entity.InterviewInvitation;
import com.slokam.rec.repo.InterviewInvitationRepo;

import java.util.*;

public interface InterviewInvitationService {
	
	public List<InterviewInvitation> getAll();
	public InterviewInvitation getById(Integer id);
	public InterviewInvitation save(InterviewInvitation InterviewInvitation);
	public void remove(InterviewInvitation InterviewInvitation);

}
