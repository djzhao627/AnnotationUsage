package cn.djzhao.annotationusage.anno;

import android.app.Activity;
import android.view.View;

import java.lang.reflect.Field;

public class ViewInjectUtil {
    public static void injectView(Activity activity) {
        try {
            Class<? extends Activity> aClass = activity.getClass();
            Field[] declaredFields = aClass.getDeclaredFields();
            for (Field declaredField : declaredFields) {
                if (declaredField.isAnnotationPresent(ViewInject.class)) {
                    ViewInject annotation = declaredField.getAnnotation(ViewInject.class);
                    declaredField.setAccessible(true);
                    View viewById = activity.findViewById(annotation.value());
                    declaredField.set(activity, viewById);
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
