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
		    .memoryCacheExtraOptions(480, 800) // max width, max height���������ÿ�������ļ�����󳤿�  
		    .threadPoolSize(3)//�̳߳��ڼ��ص�����  
		    .threadPriority(Thread.NORM_PRIORITY - 2)  
		    .denyCacheImageMultipleSizesInMemory()  
		    .memoryCache(new LruMemoryCache(2 * 1024 * 1024)) // You can pass your own memory cache implementation/�����ͨ���Լ����ڴ滺��ʵ��  
		    .memoryCacheSize(2 * 1024 * 1024)    
		    .diskCacheSize(50 * 1024 * 1024)    
		    .diskCacheFileNameGenerator(new Md5FileNameGenerator())//�������ʱ���URI������MD5 ����  
		    .diskCacheFileCount(100) //������ļ�����  
		    .diskCache(new UnlimitedDiscCache(StorageUtils.getOwnCacheDirectory(getApplicationContext(), "MyAppName/Cache")))//�Զ��建��·��  
		    .defaultDisplayImageOptions(DisplayImageOptions.createSimple())  
		    .imageDownloader(new BaseImageDownloader(getApplicationContext(), 5 * 1000, 30 * 1000)) // connectTimeout (5 s), readTimeout (30 s)��ʱʱ��  
		    .build();//��ʼ����  
	ImageLoader.getInstance().init(config);
}
}
