package com.demo.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;


class MyClassLoader2 extends ClassLoader {
		
        MyClassLoader2(ClassLoader parent) {
        	super(parent);
		}
        
        public Class<?> findClass(String name)  {
        	
        	FileInputStream input=null;
        	ByteOutputStream out=null;
        	String oName=name;
        	
			try {
				name=name.replace(".", File.separator).concat(".class");
	        	File file=new File("D:\\wp\\JFinalWp\\ClassLoader\\bin\\"+name);
	        	
	        	input=new FileInputStream(file);
	        	out=new ByteOutputStream();
	        	
				byte by[]=new byte[1024];
				int len=0;
				while((len=input.read(by))!=-1){
					out.write(by, 0, len);
				}
				
				byte bt[]=out.getBytes();
				return defineClass(oName,bt, 0, bt.length);
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				if(input!=null){
					try {
						input.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if(out!=null){
					out.close();
				}
			}
			
        	return null;
        }
        
		public static void main(String args[]) throws Exception{
			
			MyClassLoader2 classLoader=new MyClassLoader2(ClassLoader.getSystemClassLoader());
			Class newClass=classLoader.findClass("com.my.classloader.MyClassLoaderTest");
			
			Object object=newClass.newInstance();
			Method method=newClass.getMethod("setName",String.class);
			method.invoke(object, "jake");
			
			Method method2=newClass.getMethod("sayName");
			method2.invoke(object);
			
		}
}
