package com.hsbc.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

public class MyTest {

    private static int i =0;

	public static void main(String[] args) {

	        String[] data = new String[]{
	        		"FWD,29052016:09:01,10.56|FWD,29052016:10:53,11.23|FWD,29052016:15:40,23.20", 
	        		"SPOT,29052016:09:04,11.56|FWD,29052016:11:45,11.23|SPOT,29052016:12:30,23.20",
	        		"FWD,29052016:08:01,10.56|SPOT,29052016:12:30,11.23|FWD,29052016:13:20,23.20|FWD,29052016:14:340,56.00",
	        		"FWD,29052016:08:01,10.56|SPOT,29052016:12:30,11.23|FWD,29052016:13:20,23.20"
	        };
	        //convert row to List<MyTrade>
	        List<MyTrade> myTradeLst = new ArrayList<MyTrade>();
	        List<String> temp2 = Arrays.stream(data).map( x-> {	
	        	    Arrays.stream(x.split("\\|")).forEach(y->{
	        	    	myTradeLst.add(new MyTrade(i++,y.trim()));
	        	    });
	        		return "" ;	           
	        }).collect(Collectors.toList());
	        
	        //1.What are the total number of events?
	        System.out.println(myTradeLst.size());	
	        
	        //2.What are the distinct product types?
	        System.out.println(myTradeLst.stream().map(x->x.getType()).distinct().count());
	        
    		//3.For each product type, what is the total amount?
	        Map<String, Double> sum = myTradeLst.stream().collect(
	        Collectors.groupingBy(MyTrade::getType, Collectors.summingDouble(MyTrade::getAmout)));
	        System.out.println(sum);	        
	        
    		//4.Can we see the events ordered by the time they occurred?
	        List<MyTrade> sorted = myTradeLst.stream().sorted(Comparator.comparing(MyTrade::getTime))
			.collect(Collectors.toList());
	        sorted.forEach(x->System.out.println(x.getTime()));	
	        
    		//5.For a given event, get the related events which make up the trade?
	        //int id = getTradeIdByEvent();
	        int id=1;
	        myTradeLst.stream().filter(x->x.getId()==id);
    		//6.Data needs to be saved into Hive for other consumers?
	        //perfer to create spring boot/cloud microservice
	        //convert MyTrade to Json format and use jdbc connect to hive 
	        //to execute insert 
	        
    		//7.How do we get the minimum amount for each product type?
	        Comparator<MyTrade> comparator = Comparator.comparing(MyTrade::getAmout); 
	        Map<String, Optional<MyTrade>> min = myTradeLst.stream().collect(
	    	Collectors.groupingBy(MyTrade::getType, Collectors.reducing(BinaryOperator.minBy(comparator))));
	        System.out.println(min);
	        
    		//8.How do we get the maximum amount for each product type? 
	        Map<String, Optional<MyTrade>> max = myTradeLst.stream().collect(
	    	Collectors.groupingBy(MyTrade::getType, Collectors.reducing(BinaryOperator.maxBy(comparator))));
	        System.out.println(max);
	        
    		//9.How do we get the total amount for each trade, and the max/min across trades?
	        // similar group by product type, just change groupingBy(MyTrade::getId) 


	}

}
