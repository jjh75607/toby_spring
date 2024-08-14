package soon.hellotobyspring.exrate;

import java.math.BigDecimal;
import soon.hellotobyspring.api.ApiTemplate;
import soon.hellotobyspring.api.ErApiExRateExtractor;
import soon.hellotobyspring.api.HttpClientApiExecutor;
import soon.hellotobyspring.payment.ExRateProvider;

public class WebApiExRateProvider implements ExRateProvider {

    ApiTemplate apiTemplate = new ApiTemplate();

    @Override
    public BigDecimal getExRate(String currency) {
        String url = "https://open.er-api.com/v6/latest/" + currency;

        return apiTemplate.getExRate(url, new HttpClientApiExecutor(), new ErApiExRateExtractor());
    }
}
