package com.swarmhr.gateway.util;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.swarmhr.gateway.message.SwarmHrGatewayMessageEnum;
import com.swarmhr.gateway.vo.SwarmHrGatewayErrorObject;

public class SwamHrGatewayErrorTransformer {

	public static List<SwarmHrGatewayErrorObject> getErrorList_1(SwarmHrGatewayMessageEnum msgEnum){
		return getErrorList_2(msgEnum,null);
	}
	
	public static List<SwarmHrGatewayErrorObject> getErrorList_2(SwarmHrGatewayMessageEnum msgEnum, String[] values){
		List<SwarmHrGatewayErrorObject> errList = new LinkedList<>();
		if(values == null || values.length == 0) {
			errList.add(new SwarmHrGatewayErrorObject(msgEnum.getCode(), msgEnum.getMessage()));
		}else {
			return getErrorListWithEnums(Arrays.asList(new SwarmHrGatewayMessageEnum[]{msgEnum}), Arrays.asList(new String[] []{values}));
		}
		return errList;
	}
	
	public static List<SwarmHrGatewayErrorObject> getErrorListWithEnums(List<SwarmHrGatewayMessageEnum> msgEnumList, List<String[]> valuesList){
		List<SwarmHrGatewayErrorObject> errList = new LinkedList<>();
		
		// Initialize the counter
		AtomicInteger counter = new AtomicInteger(0);
		
		if (msgEnumList != null && msgEnumList.size()>0) {
			msgEnumList.stream().forEach(msgEnum -> {
				if(valuesList == null || valuesList.size() == 0) {
					errList.add(new SwarmHrGatewayErrorObject(msgEnum.getCode(), msgEnum.getMessage()));
				}else {
					String[] values = valuesList.get(counter.get());
					if(values == null || values.length == 0) {
						errList.add(new SwarmHrGatewayErrorObject(msgEnum.getCode(), msgEnum.getMessage()));
					}else {
						
					}
				}
				counter.incrementAndGet();
			});
		}
		return errList;
	}
}
