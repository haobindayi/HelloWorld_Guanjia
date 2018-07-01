package com.it.Service;
/*
 * 业务层
 * 接收上一层,控制层contr的数据
 * 计算后传递给dao
 * 调用dao层的类,类成员位置  创建dao类的对象
 * 
 */

import java.util.List;
import com.it.Dao.ZhangWuDao;
import com.it.domain.ZhangWu;

public class ZhangWuService {
	private ZhangWuDao dao=new ZhangWuDao();
	//删除账务
	public void deleteZhangWu(int uid) {
		dao.deleteZhangWu(uid);
	}
	//编辑账务,控制层调用
	public void editZhangWu(ZhangWu zw) {
		dao.editZhangWu(zw);		
	}		
	//控制层调用,传递ZhangWu对象
	public void addZhangWu(ZhangWu zw) {
		dao.addZhangWu(zw);
	}
	/*
	 * service层定义方法,实现查询所有账务,
	 * 控制调用, 
	 */
	public List<ZhangWu> selectAll() {
		return dao.selectAll();
	}
	public List<ZhangWu> select(String startDate,String endDate) {
		return dao.select(startDate, endDate);		
	}
	
}
