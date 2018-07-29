package com.mykafka.work;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.Consumed;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;

public class KafkaStreamExample {
	public static void main(String args[]) {

		if (args.length != 2) {
			System.out.println("Provide 2 arguments to the class KafkaStreamSqlExample");
			System.out.println("Arguments to be passed are <topic_name> and <bootstrapServers>");
			System.exit(0);
		}
		String kafkaTopic = args[0];
		String bootstrapServers = args[1];
		Map<String, Object> serdeProps = new HashMap<>();

		final JsonSerializer<RandomComments> jsonSerializer = new JsonSerializer<>();
		serdeProps.put("keySerdeClass", RandomComments.class);
		jsonSerializer.configure(serdeProps, false);

		final JsonDeserializer<RandomComments> jsonDeserializer = new JsonDeserializer<>();
		serdeProps.put("valueSerdeClass", RandomComments.class);
		jsonDeserializer.configure(serdeProps, false);
		final Serde<RandomComments> jsonSerde = Serdes.serdeFrom(jsonSerializer, jsonDeserializer);

		Properties streamsConfiguration = new Properties();
		streamsConfiguration.put(StreamsConfig.APPLICATION_ID_CONFIG, "stream-test");
		streamsConfiguration.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);

		StreamsBuilder builder = new StreamsBuilder();
		KStream<String, RandomComments> kStream = builder.stream(kafkaTopic, Consumed.with(Serdes.String(), jsonSerde));

		// Print record consumed
		kStream.foreach((key, value) -> System.out.println("Records consumed is " + value));

		// Print RecipientCounter where recipient is not CLARA
		kStream.filterNot((key, value) -> value.getRecipient().equals("CLARA"))
				.map((key, val) -> new KeyValue<>(val.getRecipient(), val.getRecipientCounter()))
				.foreach((key, value) -> System.out.println("Message for " + key + " is " + value));

		// Print RecipientCounter where recipientName starts with N
		kStream.filter((key, value) -> value.getRecipient().startsWith("N"))
				.map((key, val) -> new KeyValue<>(val.getRecipient(), val.getBody()))
				.foreach((key, value) -> System.out.println("Message for recipentName starting with N ("+key+") is " + value));		
		
		KafkaStreams streams = new KafkaStreams(builder.build(), streamsConfiguration);
		streams.start();
		while (true) {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
