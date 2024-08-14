package soon.hellotobyspring.payment;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;

/*
 * 오브젝트
 *   - oop, 객체, 클래스, Array
 *   - 프로그램을 실행 하면 그때 동작하는 것 / 프로그램을 실행하면 움직이는 것
 * 클래스
 *   - 오브젝트를 위한 것
 *   - ex) 청사진, 설계도, 설계도면
 *   클래스를 기반으로 만들어지는 오브젝트의 상세
 * 인스턴스
 *   - 클래스의 인스턴스 == 오브젝트
 *   - 추상적인 것에 대한 실체 / 건설도면(클래스) -> 건축물(인스턴스)
 * 자바에서의 오브젝트는 클래스의 인스턴스 또는 배열
 * 의존관계
 *  - A-->B, A가 B에 의존한다
 *  - 클래스 사이의 의존관계 -> A는 B의 코드가 있어야 동작한다
 *  - B가 변경되면 A의 코드가 영향을 받는다
 *
 * 관심사의 분리(SoC)
 *  - 관심사 -> 변경의 관점으로 설명 가능
 *  - 변경의 이유, 시점이 다른 코드를 같이 두는것은 좋지 않다
 *
 * ocp 개방-폐쇠 원칙
 *  - 클래스나 모듈은 확장에 열려있어야 하고 변경에는 닫혀야 한다
 *  - 어떤 클래스는 해당 클래스의 기능을 확장할떄 클래스의 코드는 변경이 되면 안 된다.
 *  - 전략 패턴에 잘 적용되어 있다.
 *
 * 높은 응집도와 낮은 결합도
 *  - 응집도가 높다 -> 하나의 모듈이 하나의 책임 또는 관심사에 집중되어있다
 *          -> 변화가 일어나면 해당 묘둘에서 변하는 부분이 크다
 *  - 응집도가 낮다 -> 변화가 작은 영역에서 일어난다
 * - 결합도 -> 결합도가 높을수록 수정해야 하는 코드가 많다
 *          -> 결합도를 낮게 유지해야한다, 느슨해야 한다
 *
 * 전략 패턴
 * - 자신의 기능 맥락(context)에서 필요에 따라 인터페이스를 통해 통째로 외부로 분리시키고,
 *   이를 구현한 구체적인 클래스를 필요에 따라 바꿀 수 있도록 하는 디자인 패턴
 *
 * 제어의 역전 ioc
 *  - 제어권의 이전을 통한 제어관계 역전
 *  - 제어권이 누구에게 있고, 어디에 이전 되었는가 / 내가 가진 권한을 이전했다 정도
 *  - PaymentService -> Client 이전
 *
 * 스프링 컨테이너
 *  - 스프링 중 가장 핵심, 중요한 기술
 *  - ioc, di를 지원한다.
 *
 * Bean
 *  - 오브젝트 중 애플리케이션의 핵심 클래스의 오브젝트
 *
 * 의존관계 주입 DI
 *  - 의존관계를 외부에서 주입해준다.
 *
 * 싱글톤 레지스트리
 *  - 애플리케이션이 구동되는 동안 단 하나의 오브젝트를 공유해서 사용하는 것
 *
 * 의존성 역전 원칙 dip
 *  - 상위 수준 모듈은 하위 수준 모듈에 의존하면 안 된다, 추상화에 의존해야한다
 *  - 추상화는 구체적인 사항에 의존하면 안 된다, 구체적인 사항은 추상화에 의존해야한다
 *  - 모듈 : 전체 소프트웨어를 응집도가 높고 결합도가 낮은 단위로 모아둔것 -> 대표 JAR 파일, 패키지
 *      -> Policy Layer : 정책과 비즈니스 모델을 결정하는 레이어 (상위 모듈)
 *      -> Mechanism Layer : 기술적인 부분을 담당하는 모듈 (하위 모듈)
 * */

public class PaymentService {

    private final ExRateProvider exRateProvider;
    private final Clock clock;

    public PaymentService(ExRateProvider exRateProvider, Clock clock) {
        this.exRateProvider = exRateProvider;
        this.clock = clock;
    }

    public Payment prepare(Long orderId, String currency, BigDecimal foreignCurrencyAmount) {
        BigDecimal exRate = exRateProvider.getExRate(currency);

        return Payment.createPrepared(orderId, currency, foreignCurrencyAmount, exRate,
            LocalDateTime.now(clock));
    }
}
