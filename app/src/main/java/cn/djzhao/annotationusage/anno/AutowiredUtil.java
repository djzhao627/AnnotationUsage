package cn.djzhao.annotationusage.anno;

import android.app.Activity;
import android.content.Intent;

import java.lang.reflect.Field;

public class AutowiredUtil {
    public static void injectValue(Activity activity) {
        try {
            Intent intent = activity.getIntent();
            Class<? extends Activity> aClass = activity.getClass();
            for (Field declaredField : aClass.getDeclaredFields()) {
                declaredField.setAccessible(true);
                if (declaredField.isAnnotationPresent(Autowired.class)) {
                    Autowired annotation = declaredField.getAnnotation(Autowired.class);
                    String value = annotation.value();
                    Class<?> type = declaredField.getType();
                    switch (type.toString()) {
                        case "class java.lang.String":
                            declaredField.set(activity, intent.getStringExtra(value));
                            break;
                        case "int":
                        case "class java.lang.Integer":
                            declaredField.set(activity, intent.getIntExtra(value, 0));
                            break;
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
