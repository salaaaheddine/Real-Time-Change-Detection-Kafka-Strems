package org.youtube_kstream;

import java.util.Properties;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.youtube_kstream.Config.KafkaConfig;
import org.youtube_kstream.Model.VideoData;
import org.youtube_kstream.Processor.VideoChangeProcessor;
import org.youtube_kstream.Utility.JsonSerde;

public class YouTubeStreamApp {
    private static final String APPLICATION_ID = "youtube-change-detection";
    private static final String INPUT_TOPIC = "youtube-videos";

    public static void main(String[] args) {
        Properties props = KafkaConfig.getKafkaStreamsProperties(APPLICATION_ID);

        StreamsBuilder builder = new StreamsBuilder();
        KStream<String, VideoData> youtubeStream = builder.stream(INPUT_TOPIC, Consumed.with(Serdes.String(), new JsonSerde<>(VideoData.class)));

        VideoChangeProcessor.process(youtubeStream);

        KafkaStreams streams = new KafkaStreams(builder.build(), props);
        streams.start();

        Runtime.getRuntime().addShutdownHook(new Thread(streams::close));
    }
}