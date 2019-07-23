package khf.edu.mytools.module.cloneable;

import java.util.ArrayList;

/**
 * 文档类型，扮演的是ConcretePrototype角色，而Cloneable扮演的是Prototype角色
 */
public class WordDocument implements Cloneable {
    //文本
    private String mText;
    //图片名列表
    private ArrayList<String> mImages = new ArrayList<>();

    public WordDocument() {
        System.out.println("---------WordDocument----------");
    }

    /**
     * 克隆对象
     * @return
     */
    protected WordDocument clone() {
        try {
            WordDocument doc = (WordDocument) super.clone();
            doc.mText = this.mText;
            //对mImages对象也调用clone（）函数，进行深拷贝
            doc.mImages = (ArrayList<String>)this.mImages.clone();
            return doc;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getText() {
        return mText;
    }

    public void setText(String mText) {
        this.mText = mText;
    }

    public ArrayList<String> getImages() {
        return mImages;
    }

    public void addImages(String img) {
        this.mImages.add(img);
    }

    /**
     * 打印文档内容
     */
    public void showDocument() {
        System.out.println("---------Word Content Start----------");
        System.out.println("Text:" + mText);
        System.out.println("Images List:");
        for (String imgName : mImages) {
            System.out.println("image name:" + imgName);
        }
        System.out.println("---------Word Content End----------");
    }
}
