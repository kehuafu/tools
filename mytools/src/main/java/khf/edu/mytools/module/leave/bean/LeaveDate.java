package khf.edu.mytools.module.leave.bean;

import java.io.Serializable;

public class LeaveDate implements Serializable {

    private int dateBgColor;//请假日期背景
    private int dateColor;//请假日期颜色值
    private int weekColor;//星期颜色值

    public LeaveDate(int dateBg,int date,int week){
        this.dateBgColor = dateBg;
        this.dateColor =date;
        this.weekColor = week;
    }
    public int getDateBgColor() {
        return dateBgColor;
    }

    public void setDateBgColor(int dateBgColor) {
        this.dateBgColor = dateBgColor;
    }

    public int getDateColor() {
        return dateColor;
    }

    public void setDateColor(int dateColor) {
        this.dateColor = dateColor;
    }

    public int getWeekColor() {
        return weekColor;
    }

    public void setWeekColor(int weekColor) {
        this.weekColor = weekColor;
    }
}
