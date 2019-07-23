package khf.edu.mytools.module.cloneable;

public class Client {
    public static void main(String []args){
        //1、构建文档对象
        WordDocument doc = new WordDocument();
        //2、编辑文档，添加图片
        doc.setText("这是一篇文档");
        doc.addImages("图片1");
        doc.addImages("图片2");
        doc.addImages("图片3");
        doc.showDocument();

        //以原始文档为原型，拷贝一份副本
        WordDocument document = doc.clone();
        document.showDocument();

        //修改文字、图片
        document.setText("这是修改后的Doc文档");
        document.addImages("哈哈.jpg");
        document.showDocument();

        //再打印原始文档，看看是否发生改变
        doc.showDocument();
    }
}
