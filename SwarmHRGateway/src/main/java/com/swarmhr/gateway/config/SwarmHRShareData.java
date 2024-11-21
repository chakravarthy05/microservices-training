package com.swarmhr.gateway.config;

import java.util.HashMap;
import java.util.Map;

public class SwarmHRShareData {
	private Map<String, Map<String, Object>> map;

	public SwarmHRShareData() {
		this.map = new HashMap<String, Map<String, Object>>();
	}

	public void addSharedData(String threadId, Map<String, Object> dataMap) {
		this.map.put(threadId, dataMap);
	}

	public void removeSharedData(String threadId) {
		this.map.remove(threadId);
	}

	public Map<String, Object> getSharedData(String threadId) {
		return this.map.get(threadId);
	}
}
