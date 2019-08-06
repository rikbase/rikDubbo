package com.rik.dubbo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import redis.clients.jedis.JedisPool;

import com.alibaba.fastjson.JSONObject;
import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import com.rik.dubbo.api.DataSrcService;
import com.rik.dubbo.constant.Constants;
import com.rik.dubbo.data.DataManager;
import com.rik.dubbo.result.JsonResult;

@Service("dataSrcService")
public class DataSrcServiceImpl implements DataSrcService{
	private static final long serialVersionUID = 15222215412121L;
	private static Object ob = DataManager.dataSource;
	private static String SYS_SIGN="DSSI";
	private Logger logger = Logger.getLogger(getClass());
	
	public JsonResult getInfo(String collectionName){
		try {
			if (null == ob) return new JsonResult("0","dataSource:Mysql");
			if ("DB".equals(ob.getClass().getSimpleName())) return find((DB)ob,collectionName);
			if ("JedisPool".equals(ob.getClass().getSimpleName())) return getInfo((JedisPool)ob);
			return new JsonResult("0","dataSource:Mysql");
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(SYS_SIGN+":getInfo-"+e.getMessage(), e.getCause());
			return null;
		}
	}
	
	public JsonResult init(String collectionName) {
		// TODO Auto-generated method stub
		try {
			ob=DataManager.getDataSource();
			logger.info(SYS_SIGN+":init- NoSQL_Reinitialization");
			return getInfo(collectionName);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(SYS_SIGN+":init-"+e.getMessage(), e.getCause());
			return null;
		}
		
	}
	
	private JsonResult getInfo(JedisPool jedisPool){
		try {
			return new JsonResult("1","dataSource:Redis" , jedisPool.getResource().keys("*"));
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(SYS_SIGN+":getInfo-"+e.getMessage(), e.getCause());
			return null;
		}
		
	}
	
	private JsonResult find(DB db, String tableName){
		try {
			if (tableName == null) {
				Set<String> sets=db.getCollectionNames();
				return new JsonResult("1","dataSource:mongoDB" , sets.toString());
			}
			
			DBObject queryFind = (DBObject) JSON.parse("{}");
			DBCursor dbCursor = db.getCollection(tableName).find(queryFind);
			List<Object> cursorList= new ArrayList<Object>();
			while (dbCursor.hasNext()) {
				DBObject dbObt = (DBObject) dbCursor.next();
				cursorList.add(JSONObject.toJSON(dbObt));
			}
			return new JsonResult("1","dataSource:mongoDB" ,cursorList);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(SYS_SIGN+":find-"+e.getMessage(), e.getCause());
			return null;
		}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(new DataSrcServiceImpl().getInfo("inventory").getKey(Constants.MSG.get()).toString());
	}

}
