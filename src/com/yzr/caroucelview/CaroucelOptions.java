package com.yzr.caroucelview;

import java.util.List;

import android.widget.ImageView.ScaleType;

/**
 * 
* @ClassName: CaroucelOptions 
* @Description: �ֲ��ؼ����ò���
* @author yzr 609560302@qq.com
* @date 2015��3��10�� ����5:00:57 
*
 */
public class CaroucelOptions{
	protected List<String> uris;//��ʾͼƬuri��֧��uil���֧�ֵ�����uri��
	protected int num;//�ֲ�ͼƬ����
	protected int onLoadingImgId;//�ֲ���ʾ���ڼ���ͼƬ��ԴID
	protected int onFailImgId;//�ֲ���ʾ����ʧ��ͼƬ��ԴID
	protected int subscriptview_id;//�±�����״̬ͼ����ԴID
	protected int subscriptview_selected_id;//�±�ѡ��״̬ͼ����ԴID
	protected int subscriptview_size=20;//С��size Ĭ��20
	protected int subscriptview_margin=10;//�±�margin Ĭ��10
	protected long period=2000;//�ֲ���ʱ Ĭ��2000ms
	protected ScaleType scaletype=ScaleType.CENTER_CROP;//�ֲ���ʾView��Scaletype���� Ĭ��CENTER_CROP
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