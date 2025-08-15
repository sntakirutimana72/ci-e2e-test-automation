package providers;

import com.dto.RootDto;
import com.util.Envs;
import com.util.YamlLoader;

import java.util.Objects;

public abstract class BaseDataProvider {

  private static RootDto rootSource;

  public static RootDto load() {
    if (Objects.isNull(rootSource))
      rootSource = YamlLoader.extract(Envs.TEST_DATA_SOURCE, RootDto.class);
    return rootSource;
  }
}
