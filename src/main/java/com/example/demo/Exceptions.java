package com.example.demo;

import java.io.IOException;
import java.sql.Blob;

public class Exceptions {
	
	private Blob [] employeeInfo;
	
	public static void main(String[] args) {
		 Exceptions obj=new Exceptions();  
		   obj.p();  
		   System.out.println("normal flow...");  
	}

	void m()throws IOException{  
	    throw new IOException("device error");//checked exception  
	  }  
	  void n()throws IOException{ 
		  try{
			  m();
		  }catch(Exception e){
			  System.out.println("In Exception");
		  }
	  }  
	  void p(){  
	   try{  
	    n();  
	   }catch(Exception e){System.out.println("exception handled");}  
	  }  
}
