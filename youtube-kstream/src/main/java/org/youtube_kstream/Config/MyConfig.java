package org.youtube_kstream.Config;

import java.io.FileReader;
import java.io.IOException;

import com.google.gson.Gson;

public class MyConfig {

    private String bootstrapServers;
    private String email;



    public static MyConfig loadConfig(String filePath) throws IOException {
      Gson gson = new Gson();
      try (FileReader reader = new FileReader(filePath)) {
          return gson.fromJson(reader, MyConfig.class);
      }
  }



    public void setBootstrapServers(String bootstrapServers) {
        this.bootstrapServers = bootstrapServers;
    }



    public void setEmail(String email) {
        this.email = email;
    }
    
}
