package com.yangyang.util;


public class test2 {
	public static void main(String[] args) {
		Student student = new Student();
		System.out.println(student.age + " " + student.name);
	}
}

	class Person { 
		protected String name; 
		protected int age; 
		//你已经定义了自动的构造函数，此时编译器不会为你创建默认的构造函数 
		public Person(String name,int age) { 
			this.name=name; 
			this.age=age; 
		} 
		public void print() { 
			System.out.println("Name:"+name+"/nAge:"+age); 
		}
	} 
	
	class Student extends Person { 
		public Student(){      //子类构造函数 
		//super();   不行，因为你的父类没有无参的构造函数 
		
		super("a",1); 
	      //显示调用父类的构造函数，而且必须是第一行调用 
		} 
	} 
		