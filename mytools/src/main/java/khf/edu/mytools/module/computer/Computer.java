package khf.edu.mytools.module.computer;

import androidx.annotation.NonNull;

/**
 * 计算机抽象类，即Product角色
 */
public abstract class Computer {
    protected String mBoard;
    protected String mDisplay;
    protected String mOs;

    protected Computer() {
    }

    //设置主板
    public void setmBoard(String mBoard) {
        this.mBoard = mBoard;
    }

    //设置显示器
    public void setmDisplay(String mDisplay) {
        this.mDisplay = mDisplay;
    }

    //设置操作系统
    public void setmOs(){
    }

    @NonNull
    @Override
    public String toString() {
        return "Computer [ mBoard=" + mBoard + ",mDisplay=" + mDisplay
                + ",mOs=" + mOs + "]";
    }
}
