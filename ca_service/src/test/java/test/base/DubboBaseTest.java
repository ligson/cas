package test.base;

import org.ca.cas.offlineca.api.OfflineCaApi;
import org.ligson.fw.core.facade.base.result.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by ligson on 2016/3/29.
 */
public class DubboBaseTest {
    protected static Logger logger;
    protected static ApplicationContext applicationContext = new ClassPathXmlApplicationContext
            ("spring-conf.xml");
    protected static OfflineCaApi offlineCaApi = (OfflineCaApi) applicationContext.getBean("offlineCaApi");

    public DubboBaseTest() {
        logger = LoggerFactory.getLogger(this.getClass());
    }

    public void println(Object object) {
        logger.info("{}", object);
    }

    public void testResult(Result result) {
        println(result);
        assert result.isSuccess();
    }
}
