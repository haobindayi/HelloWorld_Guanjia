账务管家

今天我们制作一款账务管家

*查询账务*

*多条件组合查询账务*

*添加账务*

*编辑账务*

*删除账务*

该程序实现了分层处理,分为app,视图层,控制层,服务层,数据处理层,以及数据库

由于数据库中需要导入apache的三方库,具体库的内容可以自行百度或者同事下载.jar包.

MainApp

```java
package com.it.app;
import com.it.view.MainView;
//开启程序
public class MainApp {
	public static void main(String[] args) {
		new MainView().run();
	}
}
```

MainView

```java
package com.it.view;
/*视图层
 * 数据传递给controller
 * 成员位置,创建controller对象
 */

import java.util.List;
import java.util.Scanner;
import com.it.controller.ZhangWuController;
import com.it.domain.ZhangWu;

public class MainView {
	private ZhangWuController controller=new ZhangWuController();
	public void run() {
		Scanner sc =new Scanner(System.in);
		while(true) {
			System.out.println("---------------管家婆家庭记账软件---------------");
			System.out.println("1.添加账务　2.编辑账务　3.删除账务　4.查询账务　5.退出系统");
			System.out.println("请输入要操作的功能序号[1-5]:");
			int a = sc.nextInt();
			switch (a) {
			case 1:
				System.out.println("请按照提示添加账务");
				addZhangWu();
				break;
			case 2:
				System.out.println("修改账务");
				editZhangWu();
				break;
			case 3:
				System.out.println("删除账务");
				deleteZhangWu();
				break;
			case 4:
				System.out.println("查询账务");
				selectZhangWu();
				break;
			case 5:
				System.out.println("退出账务");
				return;
			default:
				System.out.println("请正确输入,本次正在关闭...");
				return;
			}		
		}
	}
	//定义方法进行数据删除
	public void deleteZhangWu() {	
		System.out.println("选择的是删除功能,请按提示选择ID");
		Scanner sc =new Scanner(System.in);
		System.out.println("请输入ID");
		int uid=sc.nextInt();	
		System.out.println("是否删除第"+uid+"行数据 true/false");
		Boolean i = sc.nextBoolean();
		if(i) {
			controller.deleteZhangWu(uid);
			System.out.println("删除成功");
		}else {
			return;
		}
	}
	//定义方法进行数据编辑
	//接收用户输入  数据信息封装成ZhangWu对象      调用控制层方法,
	public void editZhangWu() {
		selectAll();//看到数据进行修改
		System.out.println("选择的是编辑功能,请编辑数据");
		Scanner sc =new Scanner(System.in);
		System.out.println("请输入ID");
		int uid=sc.nextInt();
		System.out.println("输入分类名称");
		String uname=sc.next();
		System.out.println("输入金额");
		double umoney =sc.nextDouble();
		System.out.println("输入账户");
		String ubank = sc.next();
		System.out.println("输入时间");
		String utime=sc.next();
		System.out.println("输入概括");
		String udesc =sc.next();
		//将用户输入的数据放入ZhangWu对象中
		ZhangWu zw=new ZhangWu(uid, uname, umoney, ubank, utime, udesc);
		//调用controller层
		controller.editZhangWu(zw);		
		System.out.println("编辑成功");
	}
	//添加账务的方法addZhangWu
	//接收用户的输入
	public void addZhangWu() {
		Scanner sc = new Scanner(System.in);
		System.out.println("输入分类名称");
		String uname=sc.next();
		System.out.println("输入金额");
		double umoney =sc.nextDouble();
		System.out.println("输入账户");
		String ubank = sc.next();
		System.out.println("输入时间");
		String utime=sc.next();
		System.out.println("输入概括");
		String udesc =sc.next();
		//将用户输入的所有参数 封装成ZhangWu对象
		ZhangWu zw =new ZhangWu(0, uname, umoney, ubank, utime, udesc);
		controller.addZhangWu(zw);
		System.out.println("添加成功");
	}
	/*selectZhangwu()fangfa
	 * 1查询所有 2条件查询
	 */
	public void selectZhangWu() {
		System.out.println("1查询所有 2条件查询");
		Scanner scanner =new Scanner(System.in);
		int b = scanner.nextInt();
		switch (b) {		
		case 1://查询所有
			selectAll();
			break;
		case 2://条件查询
			select();
			break;
			
		default:
			System.out.println("输入错误");
			return;
		}
	}
	/*
	 * 定义方法 实现查询
	 */
	//调用 
	public void selectAll() {
		List<ZhangWu> list = controller.selectAll();		
		printZhangWuList(list);	
	}
	/*
	 * 提供用户输入的日期,开始日期和结束日期
	 * 将日期传给con
	 */
	public void select() {
		System.out.println("选择条件查询,输入日期格式XXXX-XX-XX");
		Scanner scanner =new Scanner(System.in);
		System.out.println("请输入开始日期");
		String startDate=scanner.nextLine();
		System.out.println("请输入结束日期");		
		String endDate=scanner.nextLine();
		//调用con
		List<ZhangWu>list =  controller.select(startDate, endDate);
		if(list.size()!= 0) 
			printZhangWuList(list);
		else
			System.out.println("没有查询到数据");
	}
	//打印账务的方法
	public void printZhangWuList(List<ZhangWu> list) {
		System.out.println("id\t花销概括\t花费金额\t支付账户\t交易时间\t\t备注");
		for(ZhangWu zw:list) {
			System.out.println(zw.getUid()+"\t"+zw.getUname()+"\t"+zw.getUmoney()+"\t"+zw.getUbank()+"\t"+zw.getUtime()+"\t"+zw.getUdesc());
		}
	}
}
```

ZhangWuController

```
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
```

ZhangWuService

```java
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
```

ZhangWuDao

```java
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
```

ConfigUtils

```java
package com.it.tools;

import java.io.InputStream;
import java.util.Properties;
import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSource;

public class ConfigUtils {
	private ConfigUtils() {}
	private static BasicDataSource ds=new BasicDataSource();
	static {
		Properties properties = new Properties();
		try {
			InputStream in = ConfigUtils.class.getClassLoader().getResourceAsStream("config.properties");
			properties.load(in);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println(e+"配置文件读取错误");
		}		
		try {			
			ds.setDriverClassName(properties.getProperty("driver"));
			ds.setUrl(properties.getProperty("url"));
			ds.setUsername(properties.getProperty("user"));
			ds.setPassword(properties.getProperty("password"));
			ds.setInitialSize(10);
			ds.setMaxActive(10);
			ds.setMaxIdle(5);
			ds.setMinIdle(2);
			} catch (Exception e) {				
				e.printStackTrace();				
			}
	}	
	public static DataSource getDataSource() {
		return ds;
	}
}
```

properties

```
driver=com.mysql.jdbc.Driver
url=jdbc:mysql://localhost:3306/exe
user=root
password=XXX
```

