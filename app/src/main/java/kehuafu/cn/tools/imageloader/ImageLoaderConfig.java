package kehuafu.cn.tools.imageloader;

public class ImageLoaderConfig {
    //图片缓存配置对象
    ImageCache bitmapCache = new MemoryCache();
    //加载图片时的loading和加载失败的图片配置对象
    DisplayConfig displayConfig = new DisplayConfig();
    //加载策略
    LoadPolicy loadPolicy = new SerialPolicy();
    //线程数量，默认为cpu数量+1
    int threadCount =Runtime.getRuntime().availableProcessors()+1;
    private ImageLoaderConfig(){

    }
    //配置类的Builder
    public static class Builder{
        //图片缓存配置对象
        ImageCache imageCache = new MemoryCache();
        //加载图片时的loading和加载失败的图片配置对象
        DisplayConfig displayConfig = new DisplayConfig();
        //加载策略
        LoadPolicy loadPolicy = new SerialPolicy();
        //线程数量
        int threadCount =Runtime.getRuntime().availableProcessors()+1;
        //设置线程数量
        public Builder setThreadCount(int threadCount) {
            this.threadCount = Math.max(1,threadCount);
            return this;
        }
        //设置缓存
        public Builder setImageCache(ImageCache imageCache) {
            this.imageCache = imageCache;
            return this;
        }
        //设置加载策略
        public Builder setLoadPolicy(LoadPolicy loadPolicy) {
            this.loadPolicy = loadPolicy;
            return this;
        }
    }
}
