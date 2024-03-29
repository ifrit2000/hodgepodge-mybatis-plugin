package io.github.cd871127.hodgepodge.mybatis.config;

import lombok.extern.slf4j.Slf4j;
import org.yaml.snakeyaml.Yaml;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author anthonychen
 */
@Slf4j
public class YamlConfigParser implements ConfigParser {
    @SuppressWarnings("unchecked")
    @Override
    public List<Map<String, Object>> parse(String configPath) {
        Yaml yaml = new Yaml();
        Iterable<Object> configs = yaml.loadAll(this.getClass().getClassLoader()
                .getResourceAsStream(configPath));
        log.info("Get datasource config from {}", configPath);
        for (Object o : configs) {
            if (o instanceof Map && ((Map) o).containsKey("hodgepodge")) {
                Object config = ((Map) o).get("hodgepodge");
                config = ((Map) config).get("mybatis");
                config = ((Map) config).get("dataSource");
                return (List<Map<String, Object>>) config;
            }
        }
        return new ArrayList<>();
    }
}
