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
