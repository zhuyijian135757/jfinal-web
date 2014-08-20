package com.demo.test;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;


class MyClassLoader extends URLClassLoader {
		
		
        MyClassLoader(URL[] urls, ClassLoader parent) {
		    super(urls, parent);
		}
		
		public static void main(String args[]) throws Exception{
			
			File file=new File("D:\\wp\\JFinalWp\\ClassLoader\\bin");
			URL url=file.toURL();
			//URL url=new URL("file:D:\\wp\\JFinalWp\\ClassLoader\\bin\\");
			URL urls[]=new URL[]{url};
			MyClassLoader classLoader=new MyClassLoader(urls,ClassLoader.getSystemClassLoader());
			
			Thread.currentThread().setContextClassLoader(classLoader);
			Class newClass=classLoader.findClass("com.my.classloader.MyClassLoaderTest");
			
			Object object=newClass.newInstance();
			Method method=newClass.getMethod("setName",String.class);
			method.invoke(object, "jake");
			
			Method method2=newClass.getMethod("sayName");
			method2.invoke(object);
			//classLoader.close();
			
			System.out.println(newClass.getClassLoader());
			System.out.println(Thread.currentThread().getContextClassLoader());
		}

}
