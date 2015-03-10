package com.yzr.caroucelview;

import java.util.List;

/**
 * 
* @ClassName: CaroucelOptions 
* @Description: �ֲ��ؼ����ò���
* @author yzr 609560302@qq.com
* @date 2015��3��10�� ����5:00:57 
*
 */
public class CaroucelOptions{
	List<String> uris;//��ʾͼƬuri��֧��uil���֧�ֵ�����uri��
	public int num;//�ֲ�ͼƬ����
	public int onLoadingImgId;//�ֲ���ʾ���ڼ���ͼƬ��ԴID
	public int onFailImgId;//�ֲ���ʾ����ʧ��ͼƬ��ԴID
	public int subscriptview_id;//�±�����״̬ͼ����ԴID
	public int subscriptview_selected_id;//�±�ѡ��״̬ͼ����ԴID
	public int subscriptview_size=20;//С��size Ĭ��20
	public int subscriptview_margin=10;//�±�margin Ĭ��10
	public long period=3000;//�ֲ���ʱ Ĭ��1000ms
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
}