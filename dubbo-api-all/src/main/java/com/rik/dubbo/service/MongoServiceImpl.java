package com.rik.dubbo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.mongodb.AggregationOptions;
import com.mongodb.Cursor;
import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;
import com.mongodb.util.JSON;
import com.rik.dubbo.api.MongoService;
import com.rik.dubbo.constant.Constants;
import com.rik.dubbo.data.DataManager;
import com.rik.dubbo.result.JsonResult;

@Service("mongoService")
public class MongoServiceImpl implements MongoService{
	private static final long serialVersionUID = -4400667434120027392L;
	
	private static String SYS_SIGN="FISI";
	private Logger logger = Logger.getLogger(getClass());
	private DB mongo_DB = (DB) new DataManager().getDataSource(Constants.DB_MONGO.get());
	
	public boolean insert(String insertJson, String collectionName) {
		// TODO Auto-generated method stub
		try {
			DBObject documents = (DBObject) JSON.parse(insertJson);
			WriteResult result = mongo_DB.getCollection(collectionName).insert(documents);
			return result.wasAcknowledged();
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(SYS_SIGN+":insert-"+e.getMessage(), e.getCause());
			return false;
		}
	}

	public boolean insertMulti(String insertJson, String collectionName) {
		// TODO Auto-generated method stub
		try {
			DBObject batchToSave = (DBObject) JSON.parse(insertJson);
			WriteResult result = mongo_DB.getCollection(collectionName).insert(Arrays.asList(batchToSave));
			return result.wasAcknowledged();
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(SYS_SIGN+":insertMulti-"+e.getMessage(), e.getCause());
			return false;
		}
	}

	public boolean delete(String delJson, String collectionName) {
		// TODO Auto-generated method stub
		try {
			DBObject documents = (DBObject) JSON.parse(delJson);
			WriteResult result = mongo_DB.getCollection(collectionName).remove(documents);
			return result.wasAcknowledged();
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(SYS_SIGN+":delete-"+e.getMessage(), e.getCause());
			return false;
		}
	}

	public boolean drop(String collectionName) {
		// TODO Auto-generated method stub
		try {
			mongo_DB.getCollection(collectionName).drop();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(SYS_SIGN+":drop-"+e.getMessage(), e.getCause());
			return false;
		}
	}
	
	public boolean update(String query, String update, Boolean upsert, Boolean multi, String collectionName) {
		// TODO Auto-generated method stub
		try {
			DBObject que = (DBObject) JSON.parse(query),queset=(DBObject) JSON.parse(update);
			WriteResult result = mongo_DB.getCollection(collectionName).update(que, queset, upsert, multi);
			return result.wasAcknowledged();
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(SYS_SIGN+":update-"+e.getMessage(), e.getCause());
			return false;
		}
	}

	public boolean updateMulti(String query, String update, String collectionName) {
		// TODO Auto-generated method stub
		try {
			DBObject que = (DBObject) JSON.parse(query),queset=(DBObject) JSON.parse(update);
			WriteResult result = mongo_DB.getCollection(collectionName).updateMulti(que, queset);
			return result.wasAcknowledged();
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(SYS_SIGN+":updateMulti-"+e.getMessage(), e.getCause());
			return false;
		}
	}

	public JsonResult find(String findJson, String collectionName) {
		// TODO Auto-generated method stub
		try {
			DBObject queryFind = (DBObject) JSON.parse(findJson);
			DBCursor dbCursor = mongo_DB.getCollection(collectionName).find(queryFind);
			List<Object> cursorList= new ArrayList<Object>();
			while (dbCursor.hasNext()) {
				cursorList.add(JSONObject.toJSON((DBObject) dbCursor.next()));
			}
			return new JsonResult("1",collectionName,cursorList);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(SYS_SIGN+":find-"+e.getMessage(), e.getCause());
			return new JsonResult("0",e.getMessage());
		}
		
	}

	public JsonResult aggregate(String pipelineJson, String collectionName) {
		// TODO Auto-generated method stub
		try {
			List<DBObject> pipeline = new ArrayList<DBObject>();
			DBObject group = (DBObject) JSON.parse(pipelineJson);
			pipeline.add(group);
			Cursor cursor = mongo_DB.getCollection(collectionName).aggregate(pipeline,AggregationOptions.builder().outputMode(AggregationOptions.OutputMode.CURSOR).build());
			List<Object> cursorList= new ArrayList<Object>();
			while (cursor.hasNext()) {
				cursorList.add(JSONObject.toJSON((DBObject) cursor.next()));
			}
			return new JsonResult("1",collectionName,cursorList);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(SYS_SIGN+":aggregate-"+e.getMessage(), e.getCause());
			return new JsonResult("0",e.getMessage());
		}
	}

}
