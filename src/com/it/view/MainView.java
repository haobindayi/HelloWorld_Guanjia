package com.it.view;
/*��ͼ��
 * ���ݴ��ݸ�controller
 * ��Աλ��,����controller����
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
			System.out.println("---------------�ܼ��ż�ͥ�������---------------");
			System.out.println("1.�������2.�༭����3.ɾ������4.��ѯ����5.�˳�ϵͳ");
			System.out.println("������Ҫ�����Ĺ������[1-5]:");
			int a = sc.nextInt();
			switch (a) {
			case 1:
				System.out.println("�밴����ʾ�������");
				addZhangWu();
				break;
			case 2:
				System.out.println("�޸�����");
				editZhangWu();
				break;
			case 3:
				System.out.println("ɾ������");
				deleteZhangWu();
				break;
			case 4:
				System.out.println("��ѯ����");
				selectZhangWu();
				break;
			case 5:
				System.out.println("�˳�����");
				return;
			default:
				System.out.println("����ȷ����,�������ڹر�...");
				return;
			}		
		}
	}
	//���巽����������ɾ��
	public void deleteZhangWu() {	
		System.out.println("ѡ�����ɾ������,�밴��ʾѡ��ID");
		Scanner sc =new Scanner(System.in);
		System.out.println("������ID");
		int uid=sc.nextInt();	
		System.out.println("�Ƿ�ɾ����"+uid+"������ true/false");
		Boolean i = sc.nextBoolean();
		if(i) {
			controller.deleteZhangWu(uid);
			System.out.println("ɾ���ɹ�");
		}else {
			return;
		}
	}
	//���巽���������ݱ༭
	//�����û�����  ������Ϣ��װ��ZhangWu����      ���ÿ��Ʋ㷽��,
	public void editZhangWu() {
		selectAll();//�������ݽ����޸�
		System.out.println("ѡ����Ǳ༭����,��༭����");
		Scanner sc =new Scanner(System.in);
		System.out.println("������ID");
		int uid=sc.nextInt();
		System.out.println("�����������");
		String uname=sc.next();
		System.out.println("������");
		double umoney =sc.nextDouble();
		System.out.println("�����˻�");
		String ubank = sc.next();
		System.out.println("����ʱ��");
		String utime=sc.next();
		System.out.println("�������");
		String udesc =sc.next();
		//���û���������ݷ���ZhangWu������
		ZhangWu zw=new ZhangWu(uid, uname, umoney, ubank, utime, udesc);
		//����controller��
		controller.editZhangWu(zw);		
		System.out.println("�༭�ɹ�");
	}
	//�������ķ���addZhangWu
	//�����û�������
	public void addZhangWu() {
		Scanner sc = new Scanner(System.in);
		System.out.println("�����������");
		String uname=sc.next();
		System.out.println("������");
		double umoney =sc.nextDouble();
		System.out.println("�����˻�");
		String ubank = sc.next();
		System.out.println("����ʱ��");
		String utime=sc.next();
		System.out.println("�������");
		String udesc =sc.next();
		//���û���������в��� ��װ��ZhangWu����
		ZhangWu zw =new ZhangWu(0, uname, umoney, ubank, utime, udesc);
		controller.addZhangWu(zw);
		System.out.println("��ӳɹ�");
	}
	/*selectZhangwu()fangfa
	 * 1��ѯ���� 2������ѯ
	 */
	public void selectZhangWu() {
		System.out.println("1��ѯ���� 2������ѯ");
		Scanner scanner =new Scanner(System.in);
		int b = scanner.nextInt();
		switch (b) {		
		case 1://��ѯ����
			selectAll();
			break;
		case 2://������ѯ
			select();
			break;
			
		default:
			System.out.println("�������");
			return;
		}
	}
	/*
	 * ���巽�� ʵ�ֲ�ѯ
	 */
	//���� 
	public void selectAll() {
		List<ZhangWu> list = controller.selectAll();		
		printZhangWuList(list);	
	}
	/*
	 * �ṩ�û����������,��ʼ���ںͽ�������
	 * �����ڴ���con
	 */
	public void select() {
		System.out.println("ѡ��������ѯ,�������ڸ�ʽXXXX-XX-XX");
		Scanner scanner =new Scanner(System.in);
		System.out.println("�����뿪ʼ����");
		String startDate=scanner.nextLine();
		System.out.println("�������������");		
		String endDate=scanner.nextLine();
		//����con
		List<ZhangWu>list =  controller.select(startDate, endDate);
		if(list.size()!= 0) 
			printZhangWuList(list);
		else
			System.out.println("û�в�ѯ������");
	}
	//��ӡ����ķ���
	public void printZhangWuList(List<ZhangWu> list) {
		System.out.println("id\t��������\t���ѽ��\t֧���˻�\t����ʱ��\t\t��ע");
		for(ZhangWu zw:list) {
			System.out.println(zw.getUid()+"\t"+zw.getUname()+"\t"+zw.getUmoney()+"\t"+zw.getUbank()+"\t"+zw.getUtime()+"\t"+zw.getUdesc());
		}
	}
}
