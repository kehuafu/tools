package khf.edu.mytools.module.builder.user;

/**
 * 变种的Builder模式
 */
public class User {
    private final String mFisrtName;//必选
    private final String mLastName;//必选
    private final String mGender;//可选
    private final int mAge;//可选
    private final String mPhone;//可选

    private User(UserBuilder builder) {
        mFisrtName = builder.firstName;
        mLastName = builder.lastName;
        mGender  =builder.gender;
        mAge = builder.age;
        mPhone = builder.phone;
        System.out.println(mFisrtName+mLastName+mGender+mAge+mPhone);
    }

    public static class UserBuilder {
        private final String firstName;//必选
        private final String lastName;//必选
        private String gender;//可选
        private int age;//可选
        private String phone;//可选

        public UserBuilder(String firstName, String lastName){
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public UserBuilder gender(String gender) {
            this.gender = gender;
            return this;
        }

        public UserBuilder age(int age) {
            this.age = age;
            return this;
        }

        public UserBuilder phone(String phone) {
            this.phone = phone;
            return this;
        }
        public User build(){
            return new User(this);
        }
    }
    //User实例的使用
    public static void main(String[] args){
        new User.UserBuilder("柯","华富").gender("男").age(23).phone("132262624444").build();
    }
}
