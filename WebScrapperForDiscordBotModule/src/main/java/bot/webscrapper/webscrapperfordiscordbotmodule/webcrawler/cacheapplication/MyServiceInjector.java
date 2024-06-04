package bot.webscrapper.webscrapperfordiscordbotmodule.webcrawler.cacheapplication;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class MyServiceInjector implements ApplicationContextAware {

    private static ApplicationContext context;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }
    protected static <T> T getBean(Class<T> beanClass) {
        return context.getBean(beanClass);
    }
}
