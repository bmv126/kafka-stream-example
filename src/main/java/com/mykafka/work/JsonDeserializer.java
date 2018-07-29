package com.mykafka.work;

import java.util.Map;

import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonDeserializer<T> implements Deserializer<T> {
	private ObjectMapper objectMapper = new ObjectMapper();
    private Class<T> tClass;

	@Override
	public void close() {
		// TODO Auto-generated method stub
	}

	@SuppressWarnings("unchecked")
	@Override
	public void configure(Map<String, ?> props, boolean arg1) {
		tClass = (Class<T>) props.get("valueSerdeClass");
	}

	@Override
	public T deserialize(String arg0, byte[] bytes) {
		T data;
		try {
            data = objectMapper.readValue(bytes, tClass);
        } catch (Exception e) {
            throw new SerializationException(e);
        }
		return data;
	}

}
