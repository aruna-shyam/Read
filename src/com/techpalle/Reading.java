package com.techpalle;

import java.sql.*;
import java.util.ArrayList;
public class Reading 
{
	//no one can modify data so use privat a.m & final vari 
	
	private static final String  url="jdbc:mysql://localhost:3306/jdbc";
	private static final String  username="root";
	private static final String  password="admin";
	//static variable reduce memory wastage
	public static Connection con=null;
	public static Statement  s=null;
	public static PreparedStatement  ps=null;
	public static ResultSet rs=null;
	
	//reterive mutiple rows(here 3 rows) with collection only posible bcoz we want to create 3 obj here
	//sql qry=(select * from dept)no user i/p here so no para
	//A.L(class),<methodname>generic
	public static ArrayList<Department> getAllTableData()
	{
		//need  only one A.L obj so create outside whileloop
		ArrayList<Department> alDept=new ArrayList<Department>();
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			con=DriverManager.getConnection(url,username,password);
			
			s=con.createStatement();
			
			rs=s.executeQuery("select * from dept");
			
			
			while(rs.next()==true)
            {
            	int id=rs.getInt("dno");
            	String name=rs.getString("dname");
            	String location=rs.getString("dlocation");
            	int strength=rs.getInt("dstrength");
            	//how many rows that many department obj so inside loop
            	Department d=new Department(id,name,location,strength);
            	//dept obj created thn it ll added into A.L
            	 alDept.add(d);
            }
         } 
		catch (ClassNotFoundException e) 
		{
		 e.printStackTrace();
		} 
		catch (SQLException e) 
		{
		e.printStackTrace();
		}
		finally
		{
		   try 
		     {
			    if(rs!=null)
			    {
			     rs.close();
			    }
			    if(s!=null)
			    {
			     s.close();
			    }
			    if(con!=null)
			    {
			     con.close();
			    }
		      } 
		   catch (SQLException e) 
		      {
			   e.printStackTrace();
		      }
		 }
		return  alDept;
  }
	
//req:1	//1st aproach for reterive data(dname) from workbench
	public static void f1()
	{
		
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,username,password);
			String qry="select dname from dept";
			
			s=con.createStatement();
			
			
			ResultSet rs=s.executeQuery(qry);
			while(rs.next()==true)
			{
				//for reterving use "get" method always
				System.out.println(rs.getString("dname"));
			}
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	   //for clearing memory we can use close (optional)
		finally
		{
		   try 
		     {
			    if(rs!=null)
			    {
			     rs.close();
			    }
			    if(s!=null)
			    {
			     s.close();
			    }
			    if(con!=null)
			    {
			     con.close();
			    }
		      } 
		   catch (SQLException e) 
		      {
			e.printStackTrace();
		      }
		  }
	}
//req:2
	/*create a method to retrieve all  rows present in dept based on dno*/
	//returntype Department
	//sql query=select * from dept where dno=8(here user i/p given so taken one i/p)
	public static Department getAllTableData(int deptno)
	{
		Department d=null;
		//jdbc code
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			con=DriverManager.getConnection(url,username,password);
			
			String qry="select * from dept where dno=?";
			//dynamic qry so call preparestatement
			ps=con.prepareStatement(qry);
			ps.setInt(1, deptno);
			
			rs=ps.executeQuery();
			
			rs.next();
			
				//table column in sql name should be match
				//read data and storing data
				int i=rs.getInt("dno");
				String n=rs.getString("dname");
				String l=rs.getString("dlocation");
				int s=rs.getInt("dstrength");
				
				//obj for constructor 
				d=new Department(i,n,l,s);
				
			
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
		      if(rs!=null)
		      {
		        rs.close();  
		      }
		      if(ps!=null)
		      {
			    ps.close();
		      }
		      if(con!=null)
		      {
			   con.close();
		      }
			}
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		//return type Department so return d here
		return d;
		
	}
	public static void f3(int no,String name)
	{
		  try 
		  {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			con=DriverManager.getConnection(url,username,password);
				
			ps=con.prepareStatement("update dept set dname=? where dno=?");
				ps.setString(1,name);
				ps.setInt(2, no);
				
				ps.executeUpdate();
				 
			
		} 
		catch (ClassNotFoundException e) 
		 {
			e.printStackTrace();
		 } 
		catch (SQLException e) 
		 {
			e.printStackTrace();
		 }
		  finally 
		  {
			  try 
			  {
				  if(s!=null)
				  {
				  s.close();
				  }
				  if(con!=null)
				  {
				  con.close();
				  }
			  } 
			  catch (SQLException e) 
			  {
		       e.printStackTrace();
			  }
		  }
 		   
	}
}
