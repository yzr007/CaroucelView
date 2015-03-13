package com.yzr.caroucelview;

import java.util.List;

import android.widget.ImageView.ScaleType;

/**
 * 
* @ClassName: CaroucelOptions 
* @Description: 轮播控件设置参数
* @author yzr 609560302@qq.com
* @date 2015年3月10日 下午5:00:57 
*
 */
public class CaroucelOptions{
	protected List<String> uris;//显示图片uri（支持uil组件支持的所有uri）
	protected int num;//轮播图片数量
	protected int onLoadingImgId;//轮播显示正在加载图片资源ID
	protected int onFailImgId;//轮播显示加载失败图片资源ID
	protected int subscriptview_id;//下标正常状态图标资源ID
	protected int subscriptview_selected_id;//下标选中状态图标资源ID
	protected int subscriptview_size=20;//小标size 默认20
	protected int subscriptview_margin=10;//下标margin 默认10
	protected long period=2000;//轮播延时 默认2000ms
	protected ScaleType scaletype=ScaleType.CENTER_CROP;//轮播显示View的Scaletype参数 默认CENTER_CROP
	public CaroucelOptions(int num, List<String> uris2,
			int onLoadingImgId, int onFailImgId, int subscriptview_id,
			int subscriptview_selected_id) {
		this.uris=uris2;
		this.num=num;
		this.onLoadingImgId=onLoadingImgId;
		this.onFailImgId=onFailImgId;
		this.subscriptview_id=subscriptview_id;
		this.subscriptview_selected_id=subscriptview_selected_id;
		
	}
	public void setSubScriptViewSize(int size){
		subscriptview_size=size;
	}
	public void setSubScriptViewMargin(int margin){
		subscriptview_margin=margin;
	}
	public void setPeriod(long period){
		this.period=period;
	}
	public void setScaleType(ScaleType scaletype){
		this.scaletype=scaletype;
	}
}