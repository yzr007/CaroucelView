# CaroucelView
自定义的轮播控件
=========================
用于循环展示图片的自定义控件，支持默认加载图片、加载失败图片以及用于显示图片数量与位置的下标的自定义设置（包括图片内容、大小与间距），通过list<String>保存图片uri并传入，支持uil支持的所有uri。

##将会更新
图片点击事件、切换动画等。

##依赖
[Android-Universal-Image-Loader](https://github.com/nostra13/Android-Universal-Image-Loader)
android-support-v4.jar

##权限
<uses-permission android:name="android.permission.INTERNET" />  
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />  

##示例
``` java
    CaroucelView caroucelView=(CaroucelView) findViewById(R.id.caroucelView);
    CaroucelOptions carouceloptions=new CaroucelOptions(uris.size(), uris, R.drawable.default_img, R.drawable.default_img, R.drawable.index_normal, R.drawable.index_selected);
    caroucelView.setOptions(carouceloptions);
```
