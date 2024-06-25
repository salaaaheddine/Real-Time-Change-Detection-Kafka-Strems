package org.youtube_kstream.Utility;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

import com.google.gson.Gson;

public class JsonSerde<T> implements Serde<T> {
    private final Gson gson = new Gson();
    private final Class<T> type;

    public JsonSerde(Class<T> type) {
        this.type = type;
    }

    @Override
    public Serializer<T> serializer() {
        return (topic, data) -> gson.toJson(data).getBytes();
    }

    @Override
    public Deserializer<T> deserializer() {
        return (topic, data) -> gson.fromJson(new String(data), type);
    }
}