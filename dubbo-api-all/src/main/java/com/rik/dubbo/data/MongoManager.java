package com.rik.dubbo.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.stereotype.Component;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.rik.dubbo.utils.PropertiesUtil;

@Configuration
@Component
public class MongoManager {
	
	@SuppressWarnings("unchecked")
	@Description("mongoClient µœ÷")
	public MongoDbFactory mongoDbFactory(){
		Map<String, String> environment = PropertiesUtil.read("mongodb");
		String username = environment.get("mongo.username");
		String password = environment.get("mongo.password");
		String database = environment.get("mongo.database");
		String host = environment.get("mongo.host");
		Integer port = Integer.valueOf(environment.get("mongo.port"));
		ServerAddress serverAddress = new ServerAddress(host,port);
		MongoCredential mongoCredential = MongoCredential.createCredential(username, database, password.toCharArray());
		List<MongoCredential> mongoCredentialList = new ArrayList<MongoCredential>();
		mongoCredentialList.add(mongoCredential);
		return new SimpleMongoDbFactory(new MongoClient(serverAddress,mongoCredentialList),database);
	}
	
	public MongoTemplate mongoTemplate(){
		return mongoTemplate();
	}
}
