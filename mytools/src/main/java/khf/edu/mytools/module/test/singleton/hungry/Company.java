package khf.edu.mytools.module.test.singleton.hungry;

import java.util.ArrayList;
import java.util.List;

//公司
public class Company {
    private List<Staff> staffList = new ArrayList<>();

    public void addStaff(Staff staff) {
        staffList.add(staff);
    }

    public void showALlStaffs() {
        for (Staff staff : staffList) {
            System.out.println("Obj:" + staff.toString());
        }
    }

    public static void main(String[] args) {
        Company cp = new Company();
        //CEO
        Staff ceo1 = CEO.getmCEO();
        Staff ceo2 = CEO.getmCEO();
        cp.addStaff(ceo1);
        cp.addStaff(ceo2);
        //VP
        Staff vp1 = new VP();
        Staff vp2 = new VP();
        //Staff
        Staff staff1 = new Staff();
        Staff staff2 = new Staff();
        Staff staff3 = new Staff();

        cp.addStaff(vp1);
        cp.addStaff(vp2);

        cp.addStaff(staff1);
        cp.addStaff(staff2);
        cp.addStaff(staff3);

        cp.showALlStaffs();

    }
}
