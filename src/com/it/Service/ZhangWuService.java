package com.it.Service;
/*
 * ҵ���
 * ������һ��,���Ʋ�contr������
 * ����󴫵ݸ�dao
 * ����dao�����,���Աλ��  ����dao��Ķ���
 * 
 */

import java.util.List;
import com.it.Dao.ZhangWuDao;
import com.it.domain.ZhangWu;

public class ZhangWuService {
	private ZhangWuDao dao=new ZhangWuDao();
	//ɾ������
	public void deleteZhangWu(int uid) {
		dao.deleteZhangWu(uid);
	}
	//�༭����,���Ʋ����
	public void editZhangWu(ZhangWu zw) {
		dao.editZhangWu(zw);		
	}		
	//���Ʋ����,����ZhangWu����
	public void addZhangWu(ZhangWu zw) {
		dao.addZhangWu(zw);
	}
	/*
	 * service�㶨�巽��,ʵ�ֲ�ѯ��������,
	 * ���Ƶ���, 
	 */
	public List<ZhangWu> selectAll() {
		return dao.selectAll();
	}
	public List<ZhangWu> select(String startDate,String endDate) {
		return dao.select(startDate, endDate);		
	}
	
}
