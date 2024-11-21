package com.slokam.rec.service.impl;

import com.slokam.rec.entity.InterviewInvitation;
import com.slokam.rec.repo.InterviewInvitationRepo;
import com.slokam.rec.service.InterviewInvitationService;
import org.springframework.stereotype.Service;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class InterviewInvitationServiceImpl implements InterviewInvitationService {

      
   

	@Autowired
	private InterviewInvitationRepo repo;
	
	@Override
	public List<InterviewInvitation> getAll() {
		return repo.findAll();
	}

	@Override
	public InterviewInvitation getById(Integer id) {
		
		Optional<InterviewInvitation> opt = repo.findById(id);
		if(opt.isPresent()) {
			return opt.get();
		}else
		{
			return null;
		}
	}

	@Override
	public InterviewInvitation save(InterviewInvitation todo) {
		return repo.save(todo);
	}

	@Override
	public void remove(InterviewInvitation todo) {
		repo.delete(todo);
	}
}
