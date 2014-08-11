/*  
 * @(#) KafkaTest.java Create on 2014-8-6 下午4:02:13   
 *   
 * Copyright 2014 by yhx.   
 */

package net.joshdevins;

import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

/**
 * @KafkaTest.java
 * @created at 2014-8-6 下午4:02:13 by zhanghl
 * 
 * @desc
 * 
 * @author zhanghl({@link 253587517@qq.com})
 * @version $Revision$
 * @update: $Date$
 */
public class KafkaTest {
	public static void main(String[] args) {

		Producer<String, String> producer;
		Properties props = new Properties();
		// agent_log.sinks.kafka.type = com.vipshop.flume.sink.kafka.KafkaSink
		// agent_log.sinks.kafka.channel = all_channel
		// agent_log.sinks.kafka.zk.connect = 127.0.0.1:2181
		// agent_log.sinks.kafka.topic = all
		// agent_log.sinks.kafka.batchsize = 200
		// agent_log.sinks.kafka.producer.type = async
		// agent_log.sinks.kafka.serializer.class = kafka.serializer.StringEncoder
		props.put("metadata.broker.list", "192.168.174.132:9092");
		// props.put("topic", "test2");
		props.put("producer.type", "sync");
		// props.put("request.required.acks","1");
		props.put("serializer.class", "kafka.serializer.StringEncoder");
		producer = new Producer<String, String>(new ProducerConfig(props));
		
		KeyedMessage<String, String> message = new KeyedMessage<String, String>("test2", "1232131111adfd");
		try {
			producer.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
		producer.close();

	}
}
