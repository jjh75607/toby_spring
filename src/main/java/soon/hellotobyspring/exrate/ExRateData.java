package soon.hellotobyspring.exrate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.math.BigDecimal;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true) // 존재하지 않는 값에 대해서는 무시
public record ExRateData(String result, Map<String, BigDecimal> rates) {

}
