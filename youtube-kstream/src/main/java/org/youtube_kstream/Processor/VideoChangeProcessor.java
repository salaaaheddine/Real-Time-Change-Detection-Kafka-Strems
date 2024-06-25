package org.youtube_kstream.Processor;



import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Produced;
import org.youtube_kstream.Model.VideoData;
import org.youtube_kstream.Utility.JsonSerde;

import com.google.gson.Gson;

public class VideoChangeProcessor {
    private static final Gson gson = new Gson();

    public static VideoData process(KStream<String, VideoData> stream) {

        stream.groupByKey().aggregate(
            () -> null,
            (key, newValue, aggValue) -> {
                VideoData newVideo = newValue;
                VideoData aggVideo = aggValue == null ? null :aggValue;


                if (aggVideo == null || !newVideo.equals(aggVideo)) {
                    System.out.println("Change detected for video: " + key);
                    if (aggVideo != null) {
                        System.out.println("Old: " + gson.toJson(aggVideo));
                    }
                    System.out.println("New: " + gson.toJson(newVideo));
                }

                return newVideo;
            },
            Materialized.with(Serdes.String(), new JsonSerde<>(VideoData.class))
        ).toStream().to("youtube-video-changes", Produced.with(Serdes.String(), new JsonSerde<>(VideoData.class)));
		return null;
    }
}