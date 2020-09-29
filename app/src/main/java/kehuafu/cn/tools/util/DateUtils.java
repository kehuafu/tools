package kehuafu.cn.tools.util;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;

/**
 * 获取日期工具类
 */
public class DateUtils {


    /**
     * 利用j8的新特性，得到当前日期下一天
     *
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static LocalDate GetNextDayOnNew(int i) {
        // 取当前日期
        LocalDate localDate = LocalDate.now();
        //当前对象减去指定的天数(一天)
        localDate = localDate.minusDays(-i);
        return localDate;
    }

    /**
     * 获取当前日期对应的星期
     *
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String GetWeekOnNow(int i) {
        String week = null;
        // 取当前日期
        LocalDate localDate = LocalDate.now();
        //当前对象减去指定的天数(一天)
        localDate = localDate.minusDays(-i);
        switch (localDate.getDayOfWeek().toString()) {
            case "MONDAY":
                week = "周一";
                break;
            case "TUESDAY":
                week = "周二";
                break;
            case "WEDNESDAY":
                week = "周三";
                break;
            case "THURSDAY":
                week = "周四";
                break;
            case "FRIDAY":
                week = "周五";
                break;
            case "SATURDAY":
                week = "周六";
                break;
            case "SUNDAY":
                week = "周日";
                break;
        }
        return week;
    }
}
