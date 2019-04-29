package com.easymusic.common.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
//@ConfigurationProperties(prefix="spring.redis.cluster")
public class RedisClusterConfig {
	//用@Value配置，可以指定默认值为null，避免没有redis配置文件的子工程报错
	@Value("${spring.redis.cluster.nodes:null}")
	private String nodes;
	@Value("${spring.redis.cluster.maxTotal:0}")
	private int maxTotal;
	@Value("${spring.redis.cluster.maxIdle:0}")
	private int maxIdle;
	@Value("${spring.redis.cluster.minIdle:0}")
	private int minIdle;
	
	@Bean
	public JedisCluster getCluster() {

		try {
			//配置
			JedisPoolConfig config = new JedisPoolConfig();
			config.setMaxTotal(maxTotal);
			config.setMaxIdle(maxIdle);
			config.setMinIdle(minIdle);
			
			//节点信息
			Set<HostAndPort> set = new HashSet<>();
			String[] nodeArray = nodes.split(",");
			for (String node : nodeArray) {
				String host = node.split(":")[0];
				int port = Integer.parseInt(node.split(":")[1]);
				set.add(new HostAndPort(host, port));
			}
			
			return new JedisCluster(set, config);
			
		} catch (Exception e) {
			//说明构造过程出现了一些问题，一般是因为没有redis相关配置
			System.out.println("缺少redis配置文件，或者配置错误");
			return null;
		}
	}
}
