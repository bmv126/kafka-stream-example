## Kafka Stream example code: [![Build Status](https://travis-ci.org/bmv126/kafka-stream-example.svg?branch=master)](https://travis-ci.org/bmv126/kafka-stream-example)

### Description:

This example is used to illustrate few basic kafka stream operations. 

### Pre-Requisite:

Kafka Cluster needs to be setup.
This example uses the message prouced by kfaker (https://github.com/Kappaware/kfaker). So kfaker also needs to be installed.

### How to clone and run
    Open a terminal window.
    
    Run  git clone https://github.com/bmv126/kafka-stream-example.git
	
    Run cd kafka-stream-example
	
    Run mvn install  //This creates an uber jar with all the dependency
	
	Start kfaker to produce messages:
		kfaker --brokers "localhost:9092" --topic mytest -messon
				
	Start the kafka-stream using below command from the target directory:
		java -cp kafka-sql-0.0.1-SNAPSHOT-shaded.jar  com.mykafka.work.KafkaStreamExample <topic_name> <broker_ip:port>
		java -cp kafka-sql-0.0.1-SNAPSHOT-shaded.jar  com.mykafka.work.KafkaStreamExample mytest localhost:9092
			
	Check the logs on the console for the results.
