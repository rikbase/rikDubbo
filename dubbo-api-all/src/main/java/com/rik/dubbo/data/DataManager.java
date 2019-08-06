package com.rik.dubbo.data;

import org.springframework.stereotype.Component;

import redis.clients.jedis.JedisPool;

@Component
public class DataManager {
	
	public static Object dataSource = null;
	static{
		if (dataSource == null) getDataSource();
	}
	
	public static Object getDataSource(){
		JedisPool jedisPool = new RedisManager().getJedisPool();
		String sqlData = jedisPool.getResource().get("sql");
		if ("mongo".equals(sqlData)) dataSource = new MongoManager().mongoDbFactory().getDb();
		if ("redis".equals(sqlData)) dataSource = jedisPool;
		
		return dataSource;
	}
	
	public Object getDataSource(String dbType){
		if (dataSource != null && dataSource.getClass().getSimpleName().equals(dbType)) return dataSource;
		
		if ("mongo".equals(dbType)) dataSource = new MongoManager().mongoDbFactory().getDb();
		if ("redis".equals(dbType)) dataSource =  new RedisManager().getJedisPool();
		
		return dataSource;
	}
	
	public static void main(String[] args) {
//		System.out.println(new DataManager().getDataSource());
	}
 }
