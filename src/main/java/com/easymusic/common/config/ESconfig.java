package com.easymusic.common.config;

import java.net.InetAddress;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//@ConfigurationProperties(prefix="spring.es")
public class ESconfig {
	
	@Value("${spring.es.nodes:null}")
	private String nodes;
	@Value("${spring.es.clusterName:null}")
	private String clusterName;
	
	@Bean
	public TransportClient initial() {
		
		try {
			//配置对象settings
			Settings settings = Settings.builder().put("cluster.name", clusterName).build();
			//利用配置对象构造一个连接为空的client
			TransportClient client=new PreBuiltTransportClient(settings);
			//传递当前client可以连接的集群节点，负载均衡器
			//为了高可用，客户端可以连接多个负载均衡器
			String[] node = nodes.split(",");
			for (String hostAndPort : node) {
				String host = hostAndPort.split(":")[0];
				int port = Integer.parseInt(hostAndPort.split(":")[1]);
				client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(host),port));
			}
			
			return client;
			
		} catch (Exception e) {
			System.out.println("缺少es配置文件，或者配置错误");
			return null;
		}
	}
}
