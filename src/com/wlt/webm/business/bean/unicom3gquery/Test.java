package com.wlt.webm.business.bean.unicom3gquery;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {
	
    static HttpFillOP op=new HttpFillOP();
	
	class A extends Thread{
		@Override
		public void run() {
			String orderno=new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date())+(int) (Math.random() * (1000 - 100) + 100);
			System.out.println(op.queryResult(orderno));
		}
	}
	
	class B extends Thread{
		@Override
		public void run() {
			String orderno=new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date())+(int) (Math.random() * (1000 - 100) + 100);
			System.out.println(op.queryResult(orderno));
		}
	}
	
	class C extends Thread{
		@Override
		public void run() {
			String orderno=new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date())+(int) (Math.random() * (1000 - 100) + 100);
			System.out.println(op.queryResult(orderno));
		}
	}
	
	class D extends Thread{
		@Override
		public void run() {
			String orderno=new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date())+(int) (Math.random() * (1000 - 100) + 100);
			System.out.println(op.queryResult(orderno));
		}
	}
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		Test test=new Test();
//		int n=0;
//		for(int k=0;k<10000;k++){
//			n++;
//			System.out.println("当前线程数:"+n*4+" 最大内存数:"+Runtime.getRuntime().maxMemory()
//					+"  当前空闲内存:");
//			A a= test.new A();
//			a.start();
//			B b= test.new B();
//			b.start();
//			C c= test.new C();
//			c.start();
//			D d= test.new D();
//			d.start();
//		}
//		System.out.println(System.getProperty("file.encoding"));
//		System.out.println("缺少必须字段".getBytes("ISO-8859-1"));
//		System.out.println(new String("缺少必须字段".getBytes(),"GBK")); 
		System.out.println(Long.parseLong("123.88"));
	}

}
