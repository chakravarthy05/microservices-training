package com.swarmhr.gateway.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.EntityQuery;
import com.google.cloud.datastore.Key;
import com.google.cloud.datastore.KeyFactory;
import com.google.cloud.datastore.Query;
import com.google.cloud.datastore.QueryResults;
import com.google.cloud.datastore.StringValue;
import com.google.cloud.datastore.StructuredQuery.CompositeFilter;
import com.google.cloud.datastore.StructuredQuery.OrderBy;
import com.google.cloud.datastore.StructuredQuery.PropertyFilter;
import com.google.common.collect.Iterators;

import io.micrometer.core.instrument.util.StringUtils;

@Component
public class DataStoreCommonUtility {
	private static Logger log = LoggerFactory.getLogger(DataStoreCommonUtility.class);
	
	@Autowired
	Datastore datastore;
	
	public QueryResults<Entity> getDatastoreQueryResults(String tableName,String orgId,String orderByField) {
		log.debug("enter getDatastoreQueryResults method");
		QueryResults<Entity> results = null;
		EntityQuery.Builder builder = Query.newEntityQueryBuilder();
		builder.setKind(tableName);
		if(!StringUtils.isEmpty(orgId))
			builder.setNamespace(orgId);
		if(!StringUtils.isEmpty(orderByField))
		builder.setOrderBy(OrderBy.desc(orderByField));
		try {
		 results = datastore.run(builder.build());
		} catch(Exception e) {
			log.debug("Build QueryResult return getting error");
		}
		return results;
	}
	
	public QueryResults<Entity> getDatastoreQueryResultsWithOneParam(String tableName,String orgId,String param,String value) {
		log.debug("enter getDatastoreQueryResultsWithOneParam method");
		QueryResults<Entity> results = null;
		EntityQuery.Builder builder = Query.newEntityQueryBuilder();
		builder.setKind(tableName);
		if(!StringUtils.isEmpty(orgId))
		builder.setNamespace(orgId);
		log.debug("<----param---->"+param);
		log.debug("<----value---->"+value);
		if(!StringUtils.isEmpty(param)) {
			builder.setFilter(PropertyFilter.eq(param, value));
		}
		try {
		 results = datastore.run(builder.build());
		} catch(Exception e) {
			log.debug("Build QueryResult one param return getting error");
		}
		return results;
	}
	
	public QueryResults<Entity> getDatastoreQueryResultsWithTwoParamsBoolean(String tableName,String orgId,String param1,String value1,String param2,boolean value2) {
		log.debug("enter getDatastoreQueryResultsWithTwoParams method");
		QueryResults<Entity> results = null;
		EntityQuery.Builder builder = Query.newEntityQueryBuilder();
		builder.setKind(tableName);
		builder.setNamespace(orgId);
		if(!StringUtils.isEmpty(param1) && !StringUtils.isEmpty(param2)) {
			builder.setFilter(CompositeFilter.and(PropertyFilter.eq(param1, value1), PropertyFilter.eq(param2, value2)));
		}
		try {
		 results = datastore.run(builder.build());
		} catch(Exception e) {
			log.debug("Build QueryResult two params return getting error");
		}
		return results;
	}
	
	public QueryResults<Entity> getDatastoreQueryResultsWithTwoParams(String tableName,String orgId,String param1,String value1,String param2,String value2) {
		log.debug("enter getDatastoreQueryResultsWithTwoParams method");
		QueryResults<Entity> results = null;
		EntityQuery.Builder builder = Query.newEntityQueryBuilder();
		builder.setKind(tableName);
		builder.setNamespace(orgId);
		if(!StringUtils.isEmpty(param1) && !StringUtils.isEmpty(param2)) {
			builder.setFilter(CompositeFilter.and(PropertyFilter.eq(param1, value1), PropertyFilter.eq(param2, value2)));
		}
		try {
		 results = datastore.run(builder.build());
		} catch(Exception e) {
			log.debug("Build QueryResult two params return getting error");
		}
		return results;
	}
	
	public QueryResults<Entity> getDatastoreQueryResultsWithThreeParams(String tableName,String orgId,String param1,String value1,String param2,String value2,String param3,String value3) {
		log.debug("enter getDatastoreQueryResultsWithThreeParams method");
		QueryResults<Entity> results = null;
		EntityQuery.Builder builder = Query.newEntityQueryBuilder();
		builder.setKind(tableName);
		builder.setNamespace(orgId);
		if(!StringUtils.isEmpty(param1) && !StringUtils.isEmpty(param2) && !StringUtils.isEmpty(param3)) {
			builder.setFilter(CompositeFilter.and(PropertyFilter.eq(param1, value1), PropertyFilter.eq(param2, value2),PropertyFilter.eq(param3, value3)));
		}
		try {
		 results = datastore.run(builder.build());
		} catch(Exception e) {
			log.debug("Build QueryResult three params return getting error");
		}
		return results;
	}
	
	public int getTableRowCount(String tableName,String orgId) {
		QueryResults<Entity> results = getDatastoreQueryResults(tableName,orgId,"");
		int count = Iterators.size(results);
		return count;
	}
	
	public Key getDatastoreKey(String orgId,String table) {
		final KeyFactory keyFactory = getKeyFactory(orgId,table);
		Key key = datastore.allocateId(keyFactory.newKey());
		return key;
	}
	
	public void deleteDataStoreTableData(Key key,String table) {
		log.debug("Delete "+table+" data record ");
		try {
			datastore.delete(key);
		} catch (Exception e) {
			log.debug("Delete "+table+"  data getting exception");
		}
	}
	
	public void putDataStoreTableData(Entity entity,String table) {
		log.debug("Save "+table+" table data");
		try {
			datastore.put(entity);
		} catch (Exception e) {
			log.debug("Save "+table+" table data getting exception");
		}
	}
	
	public Entity putDataStoreTableDataKey(Entity entity,String table) {
		return datastore.put(entity);
	}
	
	private KeyFactory getKeyFactory(String orgId,String table) {
		return datastore.newKeyFactory().setKind(table).setNamespace(orgId);
	}
	
	
	 public StringValue excludeFromIndexing(String input){
		 return StringValue.newBuilder(input).setExcludeFromIndexes(true).build();
	 }
	
}
