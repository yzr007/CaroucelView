package com.yzr.caroucelview;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import android.content.Context;
import android.graphics.Bitmap.Config;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class CaroucelView extends FrameLayout {
	/**
	 * 轮播下标布局
	 */
	private LinearLayout subscriptlayout;
	
	/**
	 * 轮播控件主体
	 */
	private ViewPager showgroup;
	
	/**
	 * 轮播定时器
	 */
	private Timer showtimer;
	/**
	 * 轮播UI更新管理器
	 */
	private Handler caroucelHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			caroucel();
			super.handleMessage(msg);
		}
		private void caroucel() {
			showgroup.setCurrentItem(nextpage%caroucelOptions.num);
			nextpage++;
		}
	};

	ImageLoader imageloader = ImageLoader.getInstance();
	DisplayImageOptions options;
	CaroucelOptions caroucelOptions;
	int nextpage=0;

	TimerTask task=new TimerTask() {
		
		@Override
		public void run() {
			caroucelHandler.sendEmptyMessage(0);
		}
	};
	
	public CaroucelView(Context context) {
		super(context);
	}
	public CaroucelView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	/**
	 * 
	* @Title: setOptions 
	* @Description: 设置轮播控件参数，初始化
	* @param @param caroucelOptions    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void setOptions(CaroucelOptions caroucelOptions ) {
		this.caroucelOptions=caroucelOptions;
		initDisplayImageOptions();
		init();
		showtimer=new Timer(true);
		showtimer.schedule(task,1000,caroucelOptions.period); //延时1000ms后执行，1000ms执行一次

	}

	private void initDisplayImageOptions() {

		options = new DisplayImageOptions.Builder()
				.showImageOnLoading(caroucelOptions.onLoadingImgId) // 设置图片在下载期间显示的图片
				.showImageForEmptyUri(caroucelOptions.onFailImgId)// 设置图片Uri为空或是错误的时候显示的图片
				.showImageOnFail(caroucelOptions.onFailImgId) // 设置图片加载/解码过程中错误时候显示的图片
				.cacheInMemory(true)// 设置下载的图片是否缓存在内存中
				.cacheOnDisk(true)// 设置下载的图片是否缓存在SD卡中
				.considerExifParams(true) // 是否考虑JPEG图像EXIF参数（旋转，翻转）
				.imageScaleType(ImageScaleType.EXACTLY_STRETCHED)// 设置图片以如何的编码方式显示
				.bitmapConfig(Config.RGB_565)// 设置图片的解码类型
				.resetViewBeforeLoading(true)// 设置图片在下载前是否重置，复位
				.displayer(new FadeInBitmapDisplayer(100))// 是否图片加载好后渐入的动画时间
				.build();// 构建完成

	}

	
	/**
	 * 
	 * @Title: init
	 * @Description: 初始化轮播控件
	 * @param @param num 设定文件
	 * @param @param urls 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	public void init() {
		//初始化轮播viewpager
		showgroup = new ViewPager(getContext());
		FrameLayout.LayoutParams showgroupP = new FrameLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		showgroup.setLayoutParams(showgroupP);
		//初始化轮播图片显示view
		
		
		List<View> listviews = new ArrayList<View>();

		subscriptlayout = new LinearLayout(getContext());

		// 将subscriptlayout设为wrap_content并位于父布局正下方
		FrameLayout.LayoutParams subscriptlayoutP = new FrameLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		subscriptlayoutP.gravity = Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL;
		subscriptlayout.setLayoutParams(subscriptlayoutP);
		// 设为横向
		subscriptlayout.setOrientation(LinearLayout.HORIZONTAL);

		//初始化轮播下标
		initsubscript();
		//填充轮播viewpager数据源
		for (int index = 0; index < caroucelOptions.num; index++) {
			listviews.add(index, initshowview());
		}

		showgroup.setAdapter(new ShowGroupAdapter(listviews));
		showgroup.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPageScrolled(int position, float arg1, int arg2) {
				//下标更新
				initsubscript();
				subscriptlayout.getChildAt(position).setBackgroundResource(caroucelOptions.subscriptview_selected_id);
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		//add轮播图片显示控件
		addView(showgroup);
		//add下表控件
		addView(subscriptlayout);

	}
	/**
	 * 
	* @Title: initshowview 
	* @Description: 初始化轮播图片显示view
	* @param @return    设定文件 
	* @return ImageView    返回类型 
	* @throws
	 */
	private ImageView initshowview(){
		/**
		 * 轮播显示图片控件
		 */
		ImageView showview;
		showview = new ImageView(getContext());
		LayoutParams showviewP = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		showview.setLayoutParams(showviewP);
		return showview;
	}
	/**
	 * 
	* @Title: initsubscropt 
	* @Description: 初始化轮播下标
	* @param @param num
	* @param @param subscriptview_id    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	private void initsubscript(){
		subscriptlayout.removeAllViews();
		
		for (int index = 0; index < caroucelOptions.num; index++) {
			initsubscriptview();
			subscriptlayout.addView(initsubscriptview(), index);

		}
	}
	/**
	 * 
	* @Title: initsubscriptview 
	* @Description: 初始化下标图标
	* @param     设定文件 
	* @return ImageView    返回类型 
	* @throws
	 */
	private ImageView initsubscriptview(){
		/**
		 * 轮播下标图片
		 */
		ImageView subscriptview;
		subscriptview = new ImageView(getContext());
		LinearLayout.LayoutParams subscriptviewP = new LinearLayout.LayoutParams(
				caroucelOptions.subscriptview_size, caroucelOptions.subscriptview_size);
		subscriptviewP.gravity = Gravity.CENTER;
		subscriptviewP.leftMargin = caroucelOptions.subscriptview_margin;
		subscriptview.setLayoutParams(subscriptviewP);
		subscriptview.setBackgroundResource(caroucelOptions.subscriptview_id);
		return subscriptview;
	}

	/**
	 * 
	 * @ClassName: ShowGroupAdapter
	 * @Description: 轮播控件主体viewpager 适配器
	 * @author yzr 609560302@qq.com
	 * @date 2015年3月10日 下午3:19:50
	 * 
	 */
	public class ShowGroupAdapter extends PagerAdapter {
		public List<View> mListViews;

		public ShowGroupAdapter(List<View> mListViews) {
			this.mListViews = mListViews;
		}

		@Override
		public void destroyItem(View arg0, int arg1, Object arg2) {
			((ViewPager) arg0).removeView(mListViews.get(arg1));
		}

		@Override
		public void finishUpdate(View arg0) {
		}

		@Override
		public int getCount() {
			return mListViews.size();
		}

		// 使用imageloader加载viewpager的图片
		@Override
		public Object instantiateItem(View view, int position) {

			ImageView imageView = (ImageView) mListViews.get(position);

			imageloader.displayImage(caroucelOptions.uris.get(position), imageView, options);

			((ViewPager) view).addView(mListViews.get(position), 0); // 将图片增加到ViewPager
			
			return imageView;
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view.equals(object);
		}

		@Override
		public void restoreState(Parcelable arg0, ClassLoader arg1) {
		}

		@Override
		public Parcelable saveState() {
			return null;
		}

		@Override
		public void startUpdate(View arg0) {
		}
	}
	
}
