package data.pojo.posts;

import java.util.HashMap;

public record CreatePojo(HashMap<String, Object> payload, int expectedStatus) {}
