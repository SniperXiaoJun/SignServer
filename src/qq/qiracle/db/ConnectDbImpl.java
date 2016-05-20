//======================================================================
 //
 //        Copyright (C) 2016   
 //        All rights reserved
 //
 //        filename :ConnectDbImpl
 //        
 //
 //        created by Qiangqiang Jinag in  2016.04
 //        https://github.com/qiracle
 //		   qiracle@foxmail.com
 //
 //======================================================================
package qq.qiracle.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectDbImpl implements ConnectDb {
	  ResultSet rs;
	  PreparedStatement ps;
	  public static String QRcode = null;
	@Override
	public boolean LoginConnectDb(String LoginName, String LoginPassword,int Type)
			throws Exception {
		//1. ע������
		   Class.forName("com.mysql.jdbc.Driver") ;
		//2. ����һ�����Ӷ���
		   Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sign","root","qiracle") ; 
		//3. ����һ��sql���ķ����������
		   Statement stmt = conn.createStatement() ;
		//4. ִ��SQL,�õ���ѯ�Ľṹ������
		   switch(Type){
		   
		   case 1: rs = stmt.executeQuery("select * from stu") ;break;
		   case 2: rs = stmt.executeQuery("select * from teacher") ;break;
		   }
		 
		//5. ��������������
		   while(rs.next()){
			   String user = rs.getString("user");
			   String password = rs.getString("password");
			   System.out.println(rs.getString("user") + ":" + rs.getString("password") );
			   if(user.equals(LoginName)&&password.equals(LoginPassword)){
				   
				
				   return true;
				   
				   
			   }
			   
		   }
		//6. �ر�����,������󣬽����
		  
		return false;
	}

	@Override
	public boolean ModPwdConnectDb(String LoginName, String OldPassword,
			String NewPassword,int Type) throws Exception {
		//1. ע������
		   Class.forName("com.mysql.jdbc.Driver") ;
		//2. ����һ�����Ӷ���
		   Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sign","root","qiracle") ; 
		//3. ����һ��sql���ķ����������
		   Statement stmt = conn.createStatement() ;
		//4. ִ��SQL,�õ���ѯ�Ľṹ������
		 
		   
		   switch(Type){
			case 1:
				rs = stmt.executeQuery("select * from stu") ;
				break;
			case 2:
				rs = stmt.executeQuery("select * from teacher") ;
				break;
			
			}
		   
		   
			//5. ��������������
		   while(rs.next()){
			   String user = rs.getString("user");
			   String password = rs.getString("password");
			   System.out.println(rs.getString("user") + ":" + rs.getString("password") );
			   if(user.equals(LoginName)&&password.equals(OldPassword)){
			switch(Type){
			case 1:
				 ps=conn.prepareStatement("update  stu set password=? where user=?");
				break;
			case 2:
				ps=conn.prepareStatement("update teacher set password=? where user=?");
				break;
			
			}
				
				   ps.setString(1, NewPassword);
				   ps.setString(2, LoginName);
				   ps.executeUpdate();
				 
				
				   
				   return true;
				   
				   
			   }
			   
		   }
		   
		   
		 
		   
		return false;
	}

	@Override
	public boolean SignConnectDb(String scanResult,String username) throws Exception {
		
		
		//1. ע������
		   Class.forName("com.mysql.jdbc.Driver") ;
		//2. ����һ�����Ӷ���
		   Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sign","root","qiracle") ; 
		
		  
		   
		   System.out.println("----"+QRcode);
		   
		   if(scanResult.equals(QRcode)){
			   
			   PreparedStatement ps=conn.prepareStatement("update  stu set flag=? where user=?");  
			   ps.setString(1, "1");
			   ps.setString(2, username);
			   ps.executeUpdate();
		
			   return true;
		   }
		   
		   else{
			  
			
			   return false;
		   }
		 
		
		
	}

	@Override
	public boolean QrcodeReceive(String Qrcode) throws Exception {
		
		QRcode = Qrcode;
		if(QRcode !=null)
		{
		return true;
		}else{
			
			return false;
		}
	}

}
