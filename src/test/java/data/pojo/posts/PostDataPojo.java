package data.pojo.posts;

import java.util.List;

public record PostDataPojo(List<CreatePojo> create,
                           List<CreatePojo> create422,
                           List<UpdatePojo> update,
                           List<UpdatePojo> update404,
                           List<RetrievalOrDeletionPojo> delete,
                           RetrievePojo retrieve) {}
