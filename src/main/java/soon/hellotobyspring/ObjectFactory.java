package soon.hellotobyspring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import soon.hellotobyspring.exrate.CachedExRateProvider;
import soon.hellotobyspring.payment.ExRateProvider;
import soon.hellotobyspring.exrate.WebApiExRateProvider;
import soon.hellotobyspring.payment.PaymentService;

@Configuration
public class ObjectFactory {

    @Bean
    public PaymentService paymentService() {
        return new PaymentService(cachedExRateProvider());
    }

    @Bean
    public ExRateProvider cachedExRateProvider() {
        return new CachedExRateProvider(exRateProvider());
    }

    @Bean
    public ExRateProvider exRateProvider() {
        return new WebApiExRateProvider();
    }
}