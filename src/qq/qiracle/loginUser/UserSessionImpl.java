package qq.qiracle.loginUser;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserSessionImpl implements UserSession{
	  ResultSet rs;
	 
	@Override
	public boolean insertLoginUser(String loginName, String sessionID) throws Exception {
	
		//1. ע������
		   Class.forName("com.mysql.jdbc.Driver") ;
		//2. ����һ�����Ӷ���
		   Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sign","root","qiracle") ; 
		//3. ����һ��sql���ķ����������
		   Statement stmt = conn.createStatement() ;
		//4. ִ��SQL,�õ���ѯ�Ľṹ������
		   PreparedStatement ps1,ps2;
		   rs = stmt.executeQuery("select sessionId from onlineuser where user ="+loginName);
		  
		   String sql2 ="update  onlineuser set sessionId=? where user=?";
		   ps2=conn.prepareStatement(sql2);
		   ps2.setString(1, sessionID);
		   ps2.setString(2, loginName);
		   
		   String sql1 ="insert into onlineuser(user,sessionId) values(?,?)"; 
		   ps1=conn.prepareStatement(sql1);
		   ps1.setString(1, loginName);
		   ps1.setString(2, sessionID);
		   
		   boolean result = rs.first();
		   if(result){//�����ǰ�����û����д������ڵ�����û�
			   String origin_sessionId = rs.getString("sessionId");
			   System.out.println(origin_sessionId);
			   
			   ps2.executeUpdate();//����sessionid
			  
			   
		   }else{//�����ǰ�����û����в��������ڵ�����û�
			   
			   ps1.executeUpdate();//�������û������������ڵ�����û�
		   }
		   
		
		   
		  
			  
		  
			   
		   
		
		
		//System.out.println(loginName+"--"+sessionID);
		return true;
	}

}
