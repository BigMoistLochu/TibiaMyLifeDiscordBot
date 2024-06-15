package bot.webscrapper.webscrapperfordiscordbotmodule.configuration;

import bot.webscrapper.webscrapperfordiscordbotmodule.services.TrackedCharacterService;
import lombok.Getter;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class BeanManager implements ApplicationContextAware {

    @Getter
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        BeanManager.applicationContext = applicationContext;
    }

    public static <T> T getBean(Class<T> beanClass) {
        return applicationContext.getBean(beanClass);
    }

    public static TrackedCharacterService getTrackedCharacterService(){
        return applicationContext.getBean(TrackedCharacterService.class);
    }
}
