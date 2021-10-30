package cn.djzhao.annotationusage.anno;

import android.app.Activity;
import android.os.Bundle;
import android.os.Parcelable;

import java.lang.reflect.Field;
import java.util.Arrays;

public class AutowiredUtil {
    public static void injectValue(Activity activity) {
        Bundle extras = activity.getIntent().getExtras();
        Class<? extends Activity> aClass = activity.getClass();
        for (Field declaredField : aClass.getDeclaredFields()) {
            declaredField.setAccessible(true);
            if (declaredField.isAnnotationPresent(Autowired.class)) {
                Autowired autowired = declaredField.getAnnotation(Autowired.class);
                // 获取注解参数名
                String key = autowired.value().equals("") ? declaredField.getName() : autowired.value();
                // 当前key在extras中存在
                if (extras.containsKey(key)) {
                    Object value = extras.get(key);
                    // 如果当前属性是数组，则返回数组元素类型，否则返回null
                    Class<?> componentType = declaredField.getType().getComponentType();
                    // 如果是Parcelable类型的数组，则需要特殊处理
                    if (declaredField.getType().isArray() && Parcelable.class.isAssignableFrom(componentType)) {
                        Object[] objects = (Object[]) value;
                        // 转为Parcelable[]数组
                        value = Arrays.copyOf(objects, objects.length, (Class<? extends Object[]>) declaredField.getType());
                    }
                    declaredField.setAccessible(true);
                    try {
                        declaredField.set(activity, value);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
