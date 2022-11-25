package com.tech.service;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import com.tech.model.Product;

@Service
public class KafkaSender {

	@Autowired
	private KafkaTemplate<String, Product> kafkaTemplate;
	
	private String topicName = "java-topic";
	
	//AsyncProducer
	public void sendMessage(Product product) {
		kafkaTemplate.send(topicName, product);
		//kafkaTemplate.send(topicName, "0" ,product);
		System.out.println("Message sent to Kafka Server  "+product);
	}
	
	//SyncProducer
	public void sendMessage2(List<Product> products) {
		
		products.forEach(product -> {
			SendResult result =null;
			try {
				//result = (SendResult) kafkaTemplate.send(topicName,"0", product).get();
				result = (SendResult) kafkaTemplate.send(topicName, product).get();
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
			
			System.out.println("Message sent to Kafka Server... "+ product + 
					" | partition number "+ result.getRecordMetadata().partition()
					+" | Offset number "+ result.getRecordMetadata().offset());
		});
		
	}
	
	public void sendMessage3(List<Product> products) {
		products.forEach(product -> {
			
			kafkaTemplate.send(topicName, product);
			
			System.out.println("Message sent to Kafka Server... : " + product);
		});
		

	}
}
