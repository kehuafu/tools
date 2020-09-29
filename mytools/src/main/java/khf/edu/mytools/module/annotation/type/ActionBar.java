package khf.edu.mytools.module.annotation.type;


import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public abstract class ActionBar {
    //告知编译器不要在.class文件中存储注解数据
    @Retention(RetentionPolicy.SOURCE)
    //定义可以接受的常量列表
    @IntDef({
            NAVIGATION_MODE_STANDARD,
            NAVIGATION_MODE_LIST,
            NAVIGATION_MODE_TABS
    })
    //定义NavigationMode注解
    public @interface NavigationMode {
    }

    //常量定义
    public static final int NAVIGATION_MODE_STANDARD = 0;
    public static final int NAVIGATION_MODE_LIST = 1;
    public static final int NAVIGATION_MODE_TABS = 2;

    @NavigationMode
    public abstract int getNavigationMode();

    public abstract void setNavigationMode(@NavigationMode int Mode);//传入的参数mode不是三个常量之一，则会给出警告

}
