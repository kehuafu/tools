package khf.edu.mytools.module.computer;

/**
 * 测试代码
 */
public class Test {
    public static void main(String[]args){
        //构造器
        Builder builder  =new MacbookBuilder();
        //Director
        Director pcDirector =new Director(builder);
        //封装构建过程，4核、内存2GB、Mac系统
        pcDirector.construct("英特尔主板","三星显示器");
        //构建计算机，输出相关信息
        System.out.println("Computer Info:"+builder.create().toString());
    }
}
