package data;

import com.pojo.RootDataPojo;
import com.util.YamlDataLoader;

import java.util.Objects;

public abstract class BaseDataProvider {

  private static RootDataPojo rootData;

  protected static RootDataPojo load() {
    if (Objects.isNull(rootData))
      rootData = YamlDataLoader.loadTestData();
    return rootData;
  }
}
