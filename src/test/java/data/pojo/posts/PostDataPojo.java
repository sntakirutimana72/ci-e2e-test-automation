package data.pojo.posts;

import java.util.List;

public record PostDataPojo(List<CreatePojo> create,
                           List<UpdatePojo> update,
                           List<RetrievalOrDeletionPojo> delete,
                           RetrievePojo retrieve) {}
