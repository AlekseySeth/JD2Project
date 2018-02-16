package util;

import com.nutrition.config.ServiceConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author a.shestovsky
 */
public final class AppContextContainer {

    private static final AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(ServiceConfig.class);

    public static AnnotationConfigApplicationContext getContext() {
        return context;
    }
}
