package khf.edu.mytools.biz.leave.bean;

import java.io.Serializable;
import java.util.List;

public class LeaveBeanShell implements Serializable {

    /**
     * id : 1
     * course : [{"id":1001,"name":"java EE框架应用开发"},..]
     */

    private int id;

    private List<CourseBean> course;

    private String date;//对应的日期

    private boolean allcheck;//是否全选

    public LeaveBeanShell(List<CourseBean> course,boolean allcheck){
        this.course = course;
        this.allcheck = allcheck;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<CourseBean> getCourse() {
        return course;
    }

    public void setCourse(List<CourseBean> course) {
        this.course = course;
    }

    public boolean isAllcheck() {
        return allcheck;
    }

    public void setAllcheck(boolean allcheck) {
        this.allcheck = allcheck;
    }

    public static class CourseBean implements Serializable{
        /**
         * id : 1001
         * name :java EE框架应用开发
         * color:
         * checked:false
         */
        private int id;//课程id
        private int section;//节数id
        private String sectionTime;//节数文本
        private String name;//课程名称
        private int color;//文本颜色
        private boolean checked;//是否选择

        public CourseBean(String name, int color, boolean checked){
           this.name = name;
           this.color =color;
           this.checked =checked;
        }
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getColor() {
            return color;
        }

        public void setColor(int color) {
            this.color = color;
        }

        public boolean isChecked() {
            return checked;
        }

        public void setChecked(boolean checked) {
            this.checked = checked;
        }

        public int getSection() {
            return section;
        }

        public void setSection(int section) {
            this.section = section;
            setSectionTime(section);
        }

        public String getSectionTime() {
            return sectionTime;
        }

        public void setSectionTime(int section) {
            switch (section) {
                case 1:
                    sectionTime = "  1   2节";
                    break;
                case 2:
                    sectionTime = "  3   4节";
                    break;
                case 3:
                    sectionTime = "  5   6节";
                    break;
                case 4:
                    sectionTime = "  7   8节";
                    break;
                case 5:
                    sectionTime = " 9  10节";
                    break;
                case 6:
                    sectionTime = "11 12节";
                    break;
            }
        }

    }
}
