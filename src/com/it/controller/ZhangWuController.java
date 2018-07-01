package com.it.controller;
/*
 * 控制层
 * 接收视图到service
 * 成员位置创建service对象
 */

import java.util.List;
import com.it.Service.ZhangWuService;
import com.it.domain.ZhangWu;

public class ZhangWuController {
	private ZhangWuService service = new ZhangWuService();
	//删除账务
	public void deleteZhangWu(int uid) {
		service.deleteZhangWu(uid);
	}
	//定义方法,实现账务编辑  视图层调用 传递ZhangWu对象
	public void editZhangWu(ZhangWu zw) {
		service.editZhangWu(zw);
	}
	//定义方法,实现账务添加功能;传递的是ZhangWu的对象
	public void addZhangWu(ZhangWu zw) {
		service.addZhangWu(zw);
	}	
	/*
	 * 视图层调用
	 */
	public List<ZhangWu> selectAll() {
		return service.selectAll();
	}
	/*
	 * 定义方法实现查询账务,方法由视图层调用,传递两个日期
	 * 调用service
	 */
	public List<ZhangWu> select(String startDate,String endDate) {
		return service.select(startDate, endDate);	
	}
}
