package com.fm.fixconnector.database;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.unwind;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.mapreduce.GroupBy;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import org.springframework.util.StringUtils;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;


public  class  GenericDAO {

	@Autowired
    private MongoTemplate mongoTemplate;
	
	private String configCollectionName="trade";
	
	List<Class> classes;
	
	public String getConfigCollectionName() {
		return configCollectionName;
	}
	public void setConfigCollectionName(String configCollectionName) {
		this.configCollectionName = configCollectionName;
	}
	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}
	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
		
	}
	
	public void setClasses(List<Class> classes) {
		this.classes = classes;
	}
	public List<Class> getClasses() {
		return classes;
	}
	static final Logger logger = Logger.getLogger(GenericDAO.class);
	public Object getAllObject(){
		List<Object> trades=new ArrayList<Object>();
		for(Class className:classes){
			trades.addAll(mongoTemplate.findAll(className));
		}
		return	trades;
	}
	
	public Object getAllObjectWithTradeType(String tradeType){
		List<Object> trades=new ArrayList<Object>();
		Query mongoQuery=new Query();
		mongoQuery.addCriteria(Criteria.where("tradeType").is(tradeType));
		for(Class className:classes){
			trades.addAll(mongoTemplate.find(mongoQuery,className));
		}
		return	trades;
	}
	
//	public void addTradeObject(){
//		
//	}
//	
//	public List<Object> getTradeByDate(Date date){
//		Query query = new Query();
//		query.addCriteria(Criteria.where("date").is(date));
//		return mongoTemplate.find(query, TradeObject.class);
//	}
	
	public void saveObject(Object object){
		mongoTemplate.save(object);
	}
		
}
