package data;

import com.dto.RootDto;
import com.util.Envs;
import com.util.YamlLoader;

import java.util.Objects;

public abstract class BaseDataProvider {

  private static RootDto rootData;

  protected static RootDto load() {
    if (Objects.isNull(rootData))
      rootData = YamlLoader.extract(Envs.get("TEST_DATA_SOURCE"), RootDto.class);
    return rootData;
  }
}
