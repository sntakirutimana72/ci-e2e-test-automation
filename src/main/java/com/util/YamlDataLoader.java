package com.util;

import com.pojo.RootData;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.InputStream;

public class YamlDataLoader {

  private YamlDataLoader() {
    // Prevent instantiation
  }

  public static RootData loadTestData() {
    try (InputStream yis = YamlDataLoader.class.getClassLoader().getResourceAsStream("test_data.yaml")) {
      ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

      return mapper.readValue(yis, RootData.class);
    } catch (Exception e) {
      throw new RuntimeException("Failed to load YAML test data", e);
    }
  }
}

