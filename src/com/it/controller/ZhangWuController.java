package com.it.controller;
/*
 * ���Ʋ�
 * ������ͼ��service
 * ��Աλ�ô���service����
 */

import java.util.List;
import com.it.Service.ZhangWuService;
import com.it.domain.ZhangWu;

public class ZhangWuController {
	private ZhangWuService service = new ZhangWuService();
	//ɾ������
	public void deleteZhangWu(int uid) {
		service.deleteZhangWu(uid);
	}
	//���巽��,ʵ������༭  ��ͼ����� ����ZhangWu����
	public void editZhangWu(ZhangWu zw) {
		service.editZhangWu(zw);
	}
	//���巽��,ʵ��������ӹ���;���ݵ���ZhangWu�Ķ���
	public void addZhangWu(ZhangWu zw) {
		service.addZhangWu(zw);
	}	
	/*
	 * ��ͼ�����
	 */
	public List<ZhangWu> selectAll() {
		return service.selectAll();
	}
	/*
	 * ���巽��ʵ�ֲ�ѯ����,��������ͼ�����,������������
	 * ����service
	 */
	public List<ZhangWu> select(String startDate,String endDate) {
		return service.select(startDate, endDate);	
	}
}
