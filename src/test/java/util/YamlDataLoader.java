package util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import data.pojo.RootDataPojo;

import java.io.InputStream;

public class YamlDataLoader {

  private YamlDataLoader() {
    // Prevent instantiation
  }

  public static RootDataPojo loadTestData() {
    try (InputStream yis = YamlDataLoader.class.getClassLoader().getResourceAsStream("test_data.yaml")) {
      ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

      return mapper.readValue(yis, RootDataPojo.class);
    } catch (Exception e) {
      throw new RuntimeException("Failed to load YAML test data", e);
    }
  }
}

