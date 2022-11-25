package com.tech.config;

import java.util.Map;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

public class KafkaSenderInterceptor implements ProducerInterceptor{

	@Override
	public void configure(Map<String, ?> configs) {
		
	}

	@Override
	public ProducerRecord onSend(ProducerRecord record) {
		System.out.println("Topic "+record.topic() +" : "+"Partition "+record.partition());
		System.out.println();
		return record;
	}

	@Override
	public void onAcknowledgement(RecordMetadata metadata, Exception exception) {
		System.out.println("Topic "+metadata.topic() +" : "+"Partition "+metadata.partition() +" : Offset "+metadata.offset());
		System.out.println(metadata.serializedValueSize());
	}

	@Override
	public void close() {
		
	}

}
