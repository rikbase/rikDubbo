package com.rik.dubbo.data;

import java.util.Map;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.rik.dubbo.utils.PropertiesUtil;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@SuppressWarnings("unchecked")
@Configuration
@Component
public class RedisManager {
	private static JedisPool jedisPool = null;
	
	public RedisManager() {
		jedisPool = null;
		Map<String,Object> map = PropertiesUtil.read("redis");
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxIdle(Integer.valueOf(map.get("redis.maxIdle").toString()));
		config.setMaxTotal(Integer.valueOf(map.get("redis.maxTotal").toString()));
		config.setMaxWaitMillis(Integer.valueOf(map.get("redis.maxWaitMillis").toString()));
		config.setMinEvictableIdleTimeMillis(Integer.valueOf(map.get("redis.minEvictableIdleTimeMillis").toString()));
		config.setNumTestsPerEvictionRun(Integer.valueOf(map.get("redis.numTestsPerEvictionRun").toString()));
		config.setTimeBetweenEvictionRunsMillis(Integer.valueOf(map.get("redis.timeBetweenEvictionRunsMillis").toString()));
		config.setTestOnBorrow(Boolean.getBoolean(map.get("redis.testOnBorrow").toString()));
		config.setTestWhileIdle(Boolean.getBoolean(map.get("redis.testWhileIdle").toString()));
		
		jedisPool = new JedisPool(config, map.get("redis.host").toString(), Integer.valueOf(map.get("redis.port").toString()), Integer.valueOf(map.get("redis.timeout").toString()), map.get("redis.password").toString());
	}
	
	public JedisPool getJedisPool() {
		return jedisPool;
	}
}
