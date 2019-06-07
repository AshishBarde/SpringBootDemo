package com.example.demo;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

//import org.apache.commons.codec.binary.Base64;


abstract class A{
	public void show() {
		System.out.println("In show method");
	}
	
	abstract void display();
	
}

public class Java8Tester extends A {

   public static void main(String args[]) {
   
     /* List<String> names1 = new ArrayList<String>();
      List<Integer> arraylist = new ArrayList<> ();
      arraylist= Stream.of(1,2,4,5,6,2,2,3,4,1,6,7,8,7).collect(Collectors.toList());
      names1.add("Mahesh ");
      names1.add("Suresh ");
      names1.add("Ramesh ");
      names1.add("Naresh ");
      names1.add("Kalpesh ");
		
      List<String> names2 = new ArrayList<String>();
      names2.add("Mahesh ");
      names2.add("Suresh ");
      names2.add("Ramesh ");
      names2.add("Naresh ");
      names2.add("Kalpesh ");
		
      Java8Tester tester = new Java8Tester();
      System.out.println("Sort using Java 7 syntax: ");
		
      tester.sortUsingJava7(names1);
      System.out.println(names1 + "time:- " + java.time.LocalTime.now());
      System.out.println("Sort using Java 8 syntax: ");
		
      tester.sortUsingJava8(names2);
      System.out.println(names2 + "time:- " + java.time.LocalTime.now());
      
      tester.display();
      tester.show();
      
      
      for (int i = 0; i < arraylist.size(); i++) {

    	    for (int j = arraylist.size() - 1; j > i; j--) {
    	        if (arraylist.get(i) > arraylist.get(j)) {

    	            int tmp = arraylist.get(i);
    	            arraylist.set(i,arraylist.get(j));
    	            arraylist.set(j,tmp);

    	        }

    	    }

    	}
    	for (int i: arraylist) {
    	    System.out.println(i);
    	}
    	
    	Collections.sort(arraylist,Collections.reverseOrder());
    	System.out.println("Reverse order ");
    	for (int i: arraylist) {
    	    System.out.println(i);
    	}
    	
    	HashMap<Integer,String> hm=new HashMap<Integer,String>();    
        System.out.println("Initial list of elements: "+hm);  
          hm.put(100,"Amit");    
          hm.put(101,"Vijay");    
          hm.put(102,"Rahul");   
           
          System.out.println("After invoking put() method ");  
          for(Map.Entry m:hm.entrySet()){    
           System.out.println(m.getKey()+" "+m.getValue());    
          }  
            
          hm.putIfAbsent(102, "Gaurav");  
          System.out.println("After invoking putIfAbsent() method ");  
          for(Map.Entry m:hm.entrySet()){    
               System.out.println(m.getKey()+" "+m.getValue());    
              }  
          HashMap<Integer,String> map=new HashMap<Integer,String>();  
          map.put(104,"Ravi");  
          map.putAll(hm);  
          System.out.println("After invoking putAll() method ");  
          for(Map.Entry m:map.entrySet()){    
               System.out.println(m.getKey()+" "+m.getValue());    
              }  
          
         
          ZonedDateTime currentDate = ZonedDateTime.now();
  		ZonedDateTime firstMonthOfNextYear = currentDate
  				.plusYears(0);
  		System.out.println(Timestamp.valueOf(
  				currentDate
					.with(TemporalAdjusters
							.lastDayOfMonth())
					.toLocalDateTime()).toString()); 
  		
  		System.out.println(currentDate.toLocalDate().toString());
  		
  		ZonedDateTime firstMonthOfNextYear1 = currentDate
				.plusYears(0).minusMonths(
						currentDate.plusYears(0)
								.getMonthValue() - 10).minusDays(10);
  		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  		String timeZone = "Asia/Kolkata";
  		
  		ZonedDateTime hireDate=null;
		try {
			hireDate = ZonedDateTime
					.ofInstant(formatter.parse("2018-11-20 00:00:00").toInstant(),
							ZoneId.of(timeZone))
					.withHour(23).withMinute(59).withSecond(59);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ZonedDateTime hireDateLimitTo = ZonedDateTime.now().withYear(hireDate.getYear()).withMonth(10).withDayOfMonth(1)
				.withHour(00).withMinute(01).withSecond(00);
  		
  		ZonedDateTime hireDateLimitFrom = ZonedDateTime.now().withYear(hireDate.getYear()+1).withMonth(1).withDayOfMonth(1)
				.withHour(00).withMinute(01).withSecond(00);
  		
  		System.out.println(hireDateLimitTo.toLocalDateTime().toString());
  		System.out.println(hireDateLimitFrom.toLocalDateTime().toString());
  		System.out.println(hireDate.toLocalDateTime().toString());
      
      
      
      
   }
   
   //sort using java 7
   private void sortUsingJava7(List<String> names) {   
      Collections.sort(names, new Comparator<String>() {
         @Override
         public int compare(String s1, String s2) {
            return s1.compareTo(s2);
         }
      });
   }
   
   //sort using java 8
   private void sortUsingJava8(List<String> names) {
      Collections.sort(names, (s1, s2) -> s1.compareTo(s2));
   }

@Override
void display() {
	// TODO Auto-generated method stub
	System.out.println("Overried abstract method");*/
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	   String originalString = "password";
	   System.out.println("Original String to encrypt - " + originalString);
	   String encryptedString = encrypt(originalString);
	   System.out.println("Encrypted String - " + encryptedString);
	   
	   String decryptedString = decrypt(encryptedString);
		System.out.println("After decryption - " + decryptedString);
		
		String str = "New Hire";
		System.out.println(str.replaceAll("\\s+",""));
		
		try {
			ZonedDateTime hiredate = ZonedDateTime
					.ofInstant(formatter.parse("2019-01-01 00:00:00").toInstant(), ZoneId.of("Asia/Kolkata"))
					.withHour(00).withMinute(01).withSecond(00);
			
			ZonedDateTime hireDateLimit = ZonedDateTime.now().withMonth(10).withDayOfMonth(02)
					.withHour(00).withMinute(01).withSecond(00);
			
			if(hiredate.isBefore(hireDateLimit))
			{
				System.out.println(true);
			}else{
				System.out.println(false);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println(ZonedDateTime.now().withMonth(10).withDayOfMonth(02)
								.withHour(00).withMinute(01).withSecond(00));
		List<String> str1  = Stream.of("ABC","CBA","AAA","BBB","BCA","ACB","CCC").collect(Collectors.toList());
		List<String> s1=new ArrayList<String>();
		List<String> finalList = new ArrayList<String>();
		
		boolean flag=false;
		Collections.reverse(str1);
		System.out.println(str1);
		
		for(String s : str1)
		{
			String reverse="";
			for(int i = s.length() - 1; i >= 0; i--)
	        {
	            reverse = reverse + s.charAt(i);
	        } 
			
			s1.add(reverse);    
		}
		System.out.println("reverse String"+s1);
		for(int j = 0;j<str1.size();j++)
		{
			flag=false;
			for(int i = j+1; i<s1.size();i++)
			{
				if(str1.get(j).equals(s1.get(i)))
				{
			      flag=true;
			      break;
				}else{
					flag = false;
				}
			}
			
			if(flag)
			{
				finalList.add(str1.get(j));
			}
		}
		
		System.out.println("*************FinalList**************");
		DateTimeFormatter ageFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		System.out.println(ZonedDateTime.now().format(ageFormatter));
		 String formattedString = ZonedDateTime.now().format(ageFormatter);
		 System.out.println(formattedString);
		System.out.println(finalList);
		
		// Creating a Map with electoric items and prices 
		Map<String, Integer> ItemToPrice = new HashMap<>(); 
		ItemToPrice.put("Sony Braiva", 1000); 
		ItemToPrice.put("Apple iPhone 6S", 1200); 
		ItemToPrice.put("HP Laptop", 700);
		 ItemToPrice.put("Acer HD Monitor", 139); 
		 ItemToPrice.put("Samsung Galaxy", 800); 
		 System.out.println("unsorted Map: " + ItemToPrice); 
		 // sorting Map by values in ascending order, price here 
		 ItemToPrice.entrySet().stream() .sorted(Map.Entry.<String, Integer> comparingByValue()) .forEach(System.out::println); 
		 // now, let's collect the sorted entries in Map 
		 Map<String, Integer> sortedByPrice = ItemToPrice.entrySet().stream() .sorted(Map.Entry.<String, Integer> comparingByValue()) .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue())); 
		 System.out.println("Map incorrectly sorted by value in ascending order: " + sortedByPrice); 
		 // the Map returned by the previous statement was not sorted 
		 // because ordering was lost while collecting result in Map 
		 // you need to use the LinkedHashMap to preserve the order 
		 Map<String, Integer> sortedByValue = ItemToPrice .entrySet() .stream() .sorted(Map.Entry.<String, Integer> comparingByValue()) .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new)); 
		 System.out.println("Map sorted by value in increasing order: " + sortedByValue); 
		 // sorting a Map by values in descending order 
		 // just reverse the comparator sorting by using reversed() method 
		 Map<String, Integer> sortedByValueDesc = ItemToPrice .entrySet() .stream() .sorted(Map.Entry.<String, Integer> comparingByValue().reversed()) .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new)); 
		 System.out.println("Map sorted by value in descending order: " + sortedByValueDesc); 
		 

}

@Override
void display() {
	// TODO Auto-generated method stub
	
}



private static final String key = "aesEncryptionKey";
private static final String initVector = "encryptionIntVec";

public static String decrypt(String encrypted) {
	try {
		IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
		SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
		cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
		//byte[] original = cipher.doFinal(Base64.decodeBase64(encrypted));

		//return new String(original);
	} catch (Exception ex) {
		ex.printStackTrace();
	}

	return null;
}

public static String encrypt(String value)
{
	try {
		IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
		SecretKeySpec kyeSpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
		cipher.init(Cipher.ENCRYPT_MODE, kyeSpec, iv);

		byte[] encrypted = cipher.doFinal(value.getBytes());
		//return Base64.encodeBase64String(encrypted);
	} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (InvalidKeyException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (InvalidAlgorithmParameterException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IllegalBlockSizeException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (BadPaddingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (NoSuchAlgorithmException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (NoSuchPaddingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
	return value;
	
}


}
