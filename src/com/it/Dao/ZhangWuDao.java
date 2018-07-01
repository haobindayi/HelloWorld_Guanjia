package com.it.Dao;
/*�����ݱ� ��ɾ�Ĳ�
 * ����QueryRunner ָ������
 */

import java.sql.SQLException;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import com.it.domain.ZhangWu;
import com.it.tools.ConfigUtils;

public class ZhangWuDao {
	//���ӳ�
	private QueryRunner qr = new QueryRunner(ConfigUtils.getDataSource());	
	//ɾ��
	public void deleteZhangWu(int uid) {
		//ɾ�����		
		try {
			String sql = "DELETE FROM exe WHERE uid=?";
			int param=uid;
			qr.update(sql, param);
		} catch (SQLException e) {
			System.out.println(e);
			throw new RuntimeException("ɾ��ʧ��");
		}				
	}
	
	//�༭������,��ҵ������,����ZhangWu����.�������е����ݸ�i���ܵ����ݱ���
	public void editZhangWu(ZhangWu zw) {
		
		//����SQL
		try {
			String sql="UPDATE exe SET uname=?,umoney=?,ubank=?,utime=?,udesc=? WHERE uid=?";
			Object[] params= {zw.getUname(),zw.getUmoney(),zw.getUbank(),zw.getUtime(),zw.getUdesc(),zw.getUid()};			
			qr.update(sql, params);
		} catch (SQLException e) {
			System.out.println(e);
			throw new RuntimeException("���ĳɹ�");
		}
	}
	
	//�������,��������
	public void addZhangWu(ZhangWu zw) {
		try {
			String sql="INSERT INTO exe(uname,umoney,ubank,utime,udesc) values(?,?,?,?,?)";
			//������������,�洢���ռλ����ʵ�ʲ���
			Object[] params= {zw.getUname(),zw.getUmoney(),zw.getUbank(),zw.getUtime(),zw.getUdesc()};
			qr.update(sql,params);
		} catch (SQLException e) {
			System.out.println(e);
			throw new RuntimeException("���ʧ��");			
		}
	}
	/*
	 * ��������
	 */
	public List<ZhangWu> selectAll() {
		try {
			String sql="SELECT * FROM exe";
		    List<ZhangWu> list=	qr.query(sql, new BeanListHandler<>(ZhangWu.class));
		    return list;	
		}catch(SQLException e) {
			System.out.println(e);
			throw new RuntimeException("��ѯʧ��");
		}		
	}
	/*���巽�� ��ѯ���ݿ�,����������ѯ
	 * ҵ������,
	 */
	public List<ZhangWu> select(String startDate,String endDate) {	
		try {
			String sql="SELECT * FROM exe WHERE utime BETWEEN ? AND ?";
			//�����������
			Object[] params = {startDate,endDate};
			return qr.query(sql, new BeanListHandler<>(ZhangWu.class),params);		
		} catch (SQLException e) {			
			e.printStackTrace();
			throw new RuntimeException("��ѯʧ��");
		}		
	}	
}
