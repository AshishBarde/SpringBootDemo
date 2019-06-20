package com.example.demo.enumuration;

public enum Active {
	 YES("Y"),
	 NO("N");
	
	Active(String val) {
       this.val = val;
   }
   
   private final String val;
   
   public String value(){
       return val;
   }
}
