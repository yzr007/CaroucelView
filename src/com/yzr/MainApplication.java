package com.yzr;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.StorageUtils;
import android.app.Application;


public class MainApplication extends Application {
@Override
public void onCreate() {
	initImageLoader();
	super.onCreate();
}
public void initImageLoader(){
	ImageLoaderConfiguration config = new ImageLoaderConfiguration  
		    .Builder(getApplicationContext())  
		    .memoryCacheExtraOptions(480, 800) // max width, max height，即保存的每个缓存文件的最大长宽  
		    .threadPoolSize(3)//线程池内加载的数量  
		    .threadPriority(Thread.NORM_PRIORITY - 2)  
		    .denyCacheImageMultipleSizesInMemory()  
		    .memoryCache(new LruMemoryCache(2 * 1024 * 1024)) // You can pass your own memory cache implementation/你可以通过自己的内存缓存实现  
		    .memoryCacheSize(2 * 1024 * 1024)    
		    .diskCacheSize(50 * 1024 * 1024)    
		    .diskCacheFileNameGenerator(new Md5FileNameGenerator())//将保存的时候的URI名称用MD5 加密  
		    .diskCacheFileCount(100) //缓存的文件数量  
		    .diskCache(new UnlimitedDiscCache(StorageUtils.getOwnCacheDirectory(getApplicationContext(), "MyAppName/Cache")))//自定义缓存路径  
		    .defaultDisplayImageOptions(DisplayImageOptions.createSimple())  
		    .imageDownloader(new BaseImageDownloader(getApplicationContext(), 5 * 1000, 30 * 1000)) // connectTimeout (5 s), readTimeout (30 s)超时时间  
		    .build();//开始构建  
	ImageLoader.getInstance().init(config);
}
}
