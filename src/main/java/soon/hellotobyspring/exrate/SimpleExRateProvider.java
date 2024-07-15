package soon.hellotobyspring.exrate;

import java.io.IOException;
import java.math.BigDecimal;
import soon.hellotobyspring.payment.ExRateProvider;

public class SimpleExRateProvider implements ExRateProvider {

    @Override
    public BigDecimal getExRate(String currency) throws IOException {
        if (currency.equals("USD")) {
            return BigDecimal.valueOf(1000);
        }

        throw new IllegalArgumentException("지원되지 않는 통화입니다");
    }
}
