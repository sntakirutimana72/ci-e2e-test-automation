package data.pojo.posts;

import java.util.List;

public record RetrievePojo(List<RetrievalOrDeletionPojo> nonExisting,
                           List<RetrievalOrDeletionPojo> existing) {}
