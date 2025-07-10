package data.pojo.posts;

import java.util.HashMap;

public record UpdatePojo(int id, HashMap<String, Object> payload, int expectedStatus) {}
