package com.it.Dao;
/*对数据表 增删改查
 * 创建QueryRunner 指定数据
 */

import java.sql.SQLException;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import com.it.domain.ZhangWu;
import com.it.tools.ConfigUtils;

public class ZhangWuDao {
	//连接池
	private QueryRunner qr = new QueryRunner(ConfigUtils.getDataSource());	
	//删除
	public void deleteZhangWu(int uid) {
		//删除语句		
		try {
			String sql = "DELETE FROM exe WHERE uid=?";
			int param=uid;
			qr.update(sql, param);
		} catch (SQLException e) {
			System.out.println(e);
			throw new RuntimeException("删除失败");
		}				
	}
	
	//编辑账务功能,由业务层调用,传递ZhangWu对象.将对象中的数据更i性能到数据表中
	public void editZhangWu(ZhangWu zw) {
		
		//更新SQL
		try {
			String sql="UPDATE exe SET uname=?,umoney=?,ubank=?,utime=?,udesc=? WHERE uid=?";
			Object[] params= {zw.getUname(),zw.getUmoney(),zw.getUbank(),zw.getUtime(),zw.getUdesc(),zw.getUid()};			
			qr.update(sql, params);
		} catch (SQLException e) {
			System.out.println(e);
			throw new RuntimeException("更改成功");
		}
	}
	
	//添加账务,服务层调用
	public void addZhangWu(ZhangWu zw) {
		try {
			String sql="INSERT INTO exe(uname,umoney,ubank,utime,udesc) values(?,?,?,?,?)";
			//创建对象数组,存储五个占位符的实际参数
			Object[] params= {zw.getUname(),zw.getUmoney(),zw.getUbank(),zw.getUtime(),zw.getUdesc()};
			qr.update(sql,params);
		} catch (SQLException e) {
			System.out.println(e);
			throw new RuntimeException("添加失败");			
		}
	}
	/*
	 * 服务层调用
	 */
	public List<ZhangWu> selectAll() {
		try {
			String sql="SELECT * FROM exe";
		    List<ZhangWu> list=	qr.query(sql, new BeanListHandler<>(ZhangWu.class));
		    return list;	
		}catch(SQLException e) {
			System.out.println(e);
			throw new RuntimeException("查询失败");
		}		
	}
	/*定义方法 查询数据库,带有条件查询
	 * 业务层调用,
	 */
	public List<ZhangWu> select(String startDate,String endDate) {	
		try {
			String sql="SELECT * FROM exe WHERE utime BETWEEN ? AND ?";
			//定义对象数组
			Object[] params = {startDate,endDate};
			return qr.query(sql, new BeanListHandler<>(ZhangWu.class),params);		
		} catch (SQLException e) {			
			e.printStackTrace();
			throw new RuntimeException("查询失败");
		}		
	}	
}
