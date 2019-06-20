package com.example.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;


class Product implements Comparator<Product>{  
    int id;  
    String name;  
    float price;  
    public Product(int id, String name, float price) {  
        this.id = id;  
        this.name = name;  
        this.price = price;  
    }
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price
				+ "]";
	}
	@Override
	public int compare(Product e1, Product e2) {
		// TODO Auto-generated method stub
		return e1.getName().compareToIgnoreCase( e2.getName() );
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	
}  
public class Test {

	public static void main(String[] args) {
		List<String> names = Arrays.asList("Reflection","Collection","Stream");
		List<Integer> number = Arrays.asList(2,3,4,5);
		List<String> result = names.stream().filter(s->s.endsWith("n")).collect(Collectors.toList());
		 number = number.stream().map(x->x%2==0?x:0).collect(Collectors.toList());		
		System.out.println(number);
		System.out.println(result);
		
		List<Product> productsList = new ArrayList<Product>();  
        //Adding Products  
        productsList.add(new Product(1,"HP Laptop",25000f));  
        productsList.add(new Product(2,"Dell Laptop",30000f));  
        productsList.add(new Product(3,"Lenevo Laptop",28000f));  
        productsList.add(new Product(4,"Sony Laptop",28000f));  
        productsList.add(new Product(5,"Apple Laptop",90000f));   
        List<Product> proPrice = productsList.stream().filter(s->s.price<30000).map(s->s).collect(Collectors.toList());	
        System.out.println(proPrice.toString());
        
        Stream.iterate(1, element->element+1).filter(element->element%18==0).limit(10).forEach(System.out::println);
        Float totalPrice = productsList.stream().map(e->e.price).reduce(0.0f, (Sum,price)->Sum+price);
        Float totalPrice2 = productsList.stream().map(e->e.price).reduce(0.0f,(Float::sum));
        Double totalPrice3 = productsList.stream().collect(Collectors.summingDouble(e->e.price));
        Product product = productsList.stream().max((product1,product2)->product1.price>product2.price?1:-1).get();
        long count = productsList.stream().filter(p->p.price<30000).count();
        Set<Float> pro = productsList.stream().filter(x->x.price<30000).map(x->x.price).collect(Collectors.toSet());
        Map<Integer,String> map = productsList.stream().collect(Collectors.toMap(p->p.id, p->p.name));
        System.out.println(map);
        System.out.println(product.toString());
        System.out.println(pro);
        System.out.println(totalPrice);
        System.out.println(totalPrice2);
        System.out.println(totalPrice3);
        System.out.println(count);
        
        int num = 121;
        int n=0,r,sum=0,temp;      
        temp=num;
        for(n=num;n>0;n=n)
        {
        	r=n%10;    
        	sum=(sum*10)+r;    
        	n=n/10; 
        }
        if(temp==sum)
        {
        	System.out.println(temp+" is pelidrome");
        }else
        {
        	System.out.println(temp+" not pelidrome");
        }
        
        //fibbonessis series***********
        int a=0,b=1,c,flag=0;
        for(int no=0;no<20;no++)
        {
        	c=a+b;
        	System.out.println(c+" ");
        	a=b;
        	b=c;
        }
        //prime number
        c=11;
        a=c/2;
        for(int i=2;i<a;i++)
        {
        	if(c%i==0)
        	{
        		flag=1;
        		break;
        	}
        }
        if(flag==0)
        {
        	System.out.println(c + " is prime number");
        }else
        {
        	System.out.println(c + " is not prime number");
        }
        
        // amstrom number
        n=153;
        temp=n;
        sum=0;
        while(n>0)    
        {    
        r=n%10;    
        sum=sum+(r*r*r);    
        n=n/10;    
        }    
        if(temp==sum)    
        System.out.println("armstrong  number ");    
        else    
        System.out.println("not armstrong number"); 
        
        HashMap<String,String> map1=new HashMap<String,String>();  
        map1.put("name","Rahul");  
        map1.put("address","Vijay");  
        map1.put("age","Amit");  
        //Elements can traverse in any order 
        for(Entry<String, String> m : map1.entrySet())
        {
        	System.out.println(m.getKey()+":"+m.getValue());
        }
        map1.entrySet()  
        //Returns a sequential Stream with this collection as its source  
        .stream()  
        //Sorted according to the provided Comparator  
        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))  
        //Performs an action for each element of this stream  
        .forEach(System.out::println); 
        map1.entrySet()  
        //Returns a sequential Stream with this collection as its source  
        .stream()  
        //Sorted according to the provided Comparator  
        .sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))  
        //Performs an action for each element of this stream  
        .forEach(System.out::println);  
        
        ConcurrentHashMap<Integer, String> conHash = new ConcurrentHashMap<Integer, String>();
        conHash.put(1, "A");
        conHash.put(2, "B");
        conHash.put(3, "C");
        
        for(Entry<Integer, String> k : conHash.entrySet())
        {
        	
        	if(k.getKey()==1)
        	{
        		conHash.put(4, "E");
        		k.setValue("D");
        	}
        	
        	System.out.println(k.getKey()+" : "+k.getValue());
        }
        
        
         
        Comparator<Product> groupByComparator = Comparator.comparing(Product::getName)
                                                .thenComparing(Product::getPrice)
                                                .thenComparing(Product::getId);
         
        Collections.sort(productsList, groupByComparator);
        
        //System.out.println(productsList);
        
        List<String> memberNames = new ArrayList<>();
        memberNames.add("Amitabh");
        memberNames.add("Shekhar");
        memberNames.add("Aman");
        memberNames.add("Rahul");
        memberNames.add("Shahrukh");
        memberNames.add("Salman");
        memberNames.add("Yana");
        memberNames.add("Lokesh");
        
         memberNames.stream().filter((e)->e.startsWith("S")).map(s->s.toUpperCase()).forEach(System.out::println);
        System.out.println(memberNames);
        
      String  str  = "AABBCSAABFGHAAB";
  	  String pat = "AAB";
  	  
  	  for (int i = -1; (i = str.indexOf(pat, i + 1)) != -1; i++) 
  	  {
  		    System.out.println(i);
  	  }
  	  
  	  final Set<Integer> mapList = new HashSet<Integer>();
  	   final Map mapll=new HashMap();
  	 mapll.put(1,"A");
  	mapll.put(2, "");
  	System.out.println(mapll);
  	  mapList.add(1);
  	  mapList.add(2);
  	  System.out.println(mapList);
  	  
  	  Map<String,List<Product>> mapObject = new HashMap<String,List<Product>>();
  	  mapObject.put("productList",productsList);
  	  System.out.println(mapObject);
  	  List<List<Product>> p = mapObject.values().stream().map(e->e).collect(Collectors.toList());
  	  System.out.println(p.toString());
	}
}
