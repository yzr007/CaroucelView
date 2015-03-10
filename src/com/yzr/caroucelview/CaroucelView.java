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
	 * �ֲ��±겼��
	 */
	private LinearLayout subscriptlayout;
	
	/**
	 * �ֲ��ؼ�����
	 */
	private ViewPager showgroup;
	
	/**
	 * �ֲ���ʱ��
	 */
	private Timer showtimer;
	/**
	 * �ֲ�UI���¹�����
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
	* @Description: �����ֲ��ؼ���������ʼ��
	* @param @param caroucelOptions    �趨�ļ� 
	* @return void    �������� 
	* @throws
	 */
	public void setOptions(CaroucelOptions caroucelOptions ) {
		this.caroucelOptions=caroucelOptions;
		initDisplayImageOptions();
		init();
		showtimer=new Timer(true);
		showtimer.schedule(task,1000,caroucelOptions.period); //��ʱ1000ms��ִ�У�1000msִ��һ��

	}

	private void initDisplayImageOptions() {

		options = new DisplayImageOptions.Builder()
				.showImageOnLoading(caroucelOptions.onLoadingImgId) // ����ͼƬ�������ڼ���ʾ��ͼƬ
				.showImageForEmptyUri(caroucelOptions.onFailImgId)// ����ͼƬUriΪ�ջ��Ǵ����ʱ����ʾ��ͼƬ
				.showImageOnFail(caroucelOptions.onFailImgId) // ����ͼƬ����/��������д���ʱ����ʾ��ͼƬ
				.cacheInMemory(true)// �������ص�ͼƬ�Ƿ񻺴����ڴ���
				.cacheOnDisk(true)// �������ص�ͼƬ�Ƿ񻺴���SD����
				.considerExifParams(true) // �Ƿ���JPEGͼ��EXIF��������ת����ת��
				.imageScaleType(ImageScaleType.EXACTLY_STRETCHED)// ����ͼƬ����εı��뷽ʽ��ʾ
				.bitmapConfig(Config.RGB_565)// ����ͼƬ�Ľ�������
				.resetViewBeforeLoading(true)// ����ͼƬ������ǰ�Ƿ����ã���λ
				.displayer(new FadeInBitmapDisplayer(100))// �Ƿ�ͼƬ���غú���Ķ���ʱ��
				.build();// �������

	}

	
	/**
	 * 
	 * @Title: init
	 * @Description: ��ʼ���ֲ��ؼ�
	 * @param @param num �趨�ļ�
	 * @param @param urls �趨�ļ�
	 * @return void ��������
	 * @throws
	 */
	public void init() {
		//��ʼ���ֲ�viewpager
		showgroup = new ViewPager(getContext());
		FrameLayout.LayoutParams showgroupP = new FrameLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		showgroup.setLayoutParams(showgroupP);
		//��ʼ���ֲ�ͼƬ��ʾview
		
		
		List<View> listviews = new ArrayList<View>();

		subscriptlayout = new LinearLayout(getContext());

		// ��subscriptlayout��Ϊwrap_content��λ�ڸ��������·�
		FrameLayout.LayoutParams subscriptlayoutP = new FrameLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		subscriptlayoutP.gravity = Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL;
		subscriptlayout.setLayoutParams(subscriptlayoutP);
		// ��Ϊ����
		subscriptlayout.setOrientation(LinearLayout.HORIZONTAL);

		//��ʼ���ֲ��±�
		initsubscript();
		//����ֲ�viewpager����Դ
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
				//�±����
				initsubscript();
				subscriptlayout.getChildAt(position).setBackgroundResource(caroucelOptions.subscriptview_selected_id);
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		//add�ֲ�ͼƬ��ʾ�ؼ�
		addView(showgroup);
		//add�±�ؼ�
		addView(subscriptlayout);

	}
	/**
	 * 
	* @Title: initshowview 
	* @Description: ��ʼ���ֲ�ͼƬ��ʾview
	* @param @return    �趨�ļ� 
	* @return ImageView    �������� 
	* @throws
	 */
	private ImageView initshowview(){
		/**
		 * �ֲ���ʾͼƬ�ؼ�
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
	* @Description: ��ʼ���ֲ��±�
	* @param @param num
	* @param @param subscriptview_id    �趨�ļ� 
	* @return void    �������� 
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
	* @Description: ��ʼ���±�ͼ��
	* @param     �趨�ļ� 
	* @return ImageView    �������� 
	* @throws
	 */
	private ImageView initsubscriptview(){
		/**
		 * �ֲ��±�ͼƬ
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
	 * @Description: �ֲ��ؼ�����viewpager ������
	 * @author yzr 609560302@qq.com
	 * @date 2015��3��10�� ����3:19:50
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

		// ʹ��imageloader����viewpager��ͼƬ
		@Override
		public Object instantiateItem(View view, int position) {

			ImageView imageView = (ImageView) mListViews.get(position);

			imageloader.displayImage(caroucelOptions.uris.get(position), imageView, options);

			((ViewPager) view).addView(mListViews.get(position), 0); // ��ͼƬ���ӵ�ViewPager
			
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
