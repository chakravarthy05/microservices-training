package com.slokam.healthcare.dpi.service;

import com.slokam.healthcare.dpi.entity.Treatment;
import com.slokam.healthcare.dpi.request.TreatmentRequestPojo;

public interface ITreatmentService {

	public void saveTreatment(TreatmentRequestPojo treatment  );
	public Treatment getTreatment(Long id);
}
