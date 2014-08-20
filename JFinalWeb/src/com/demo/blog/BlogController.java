package com.demo.blog;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Blob;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.util.NodeIterator;
import org.htmlparser.util.NodeList;
import org.htmlparser.visitors.TextExtractingVisitor;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;

/**
 * BlogController
 * 所有 sql 写在 Model 或 Service 中，不要写在 Controller 中，养成好习惯，有利于大型项目的开发与维护
 */
@Before(BlogInterceptor.class)
public class BlogController extends Controller {
	
	public void index() {
		initHead();
		render("/common/index.html");
	}
	
	public void search(){
		String domain=getPara("domain");
		String sql="select * from blog_map where domain = '"+domain+"'";
		List<BlogMap> maps=BlogMap.dao.find(sql);
		BlogMap  map=new BlogMap();
		if(maps.size()>0){
			map=maps.get(0);
		}
		renderJson(map);
	}
	
	
	public void catchHtmlByConn() throws Exception{
		String url="http://baidu.com";
		HttpURLConnection conn=(HttpURLConnection) new URL(url).openConnection();
		conn.setRequestMethod("GET");
		conn.connect();
		StringBuffer buffer=new StringBuffer();
		if(conn.getResponseCode()==200){
			BufferedReader reader=new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String tmp="";
			while((tmp=reader.readLine())!=null){
				buffer.append(tmp);
			}
			reader.close();
		}
	}
	
	
	public void catchHtmlByParser() throws Exception{
		
		List<BlogMap> allMap=BlogMap.dao.findAll();
		
		for(BlogMap map : allMap){
			String domain=map.getStr("domain");
			String attr=map.getStr("attr");
			String values=map.getStr("value");
			List<Blog> blogs=Blog.dao.fingByUrl(domain);
			
			for(Blog blog : blogs){
				
				String url=blog.getStr("url");
				NodeList list=null;
				try{
					HttpURLConnection http=(HttpURLConnection)(new URL(url)).openConnection();
					http.addRequestProperty("User-Agent", "csdn");
					Parser parser = new Parser(http);
					NodeFilter filter = new HasAttributeFilter(attr,values);
					list=parser.parse(filter);
				}catch(Exception e){
					System.out.println(e.getMessage() +"  url"+url);
				}
				
				if(list==null || list.size()<1){
					System.out.println(url);
					continue;
				}
				
				String html=list.elementAt(0).toHtml();
				if(html==null || html.trim().equals("")){
					System.out.println(url);
					continue;
				}else{
					blog.set("content", list.elementAt(0).toHtml());
				}
				
				blog.update();
			}
		}
		renderNull();
	}
	
	
	public String catchHtmlByParser(String url,String attr,String values) throws Exception{
			
			NodeList list=null;
			try{
				HttpURLConnection http=(HttpURLConnection)(new URL(url)).openConnection();
				http.addRequestProperty("User-Agent", "flyingfat.net");
				Parser parser = new Parser(http);
				NodeFilter filter = new HasAttributeFilter(attr,values);
				list=parser.parse(filter);
			}catch(Exception e){
				System.out.println(e.getMessage() +" url "+url);
			}
			
			return list.elementAt(0).toHtml();
	}
	
	public void look() throws  Exception{
		
		String id=getPara(0);
		Blog blog=Blog.dao.findById(id);
		setAttr("blog",blog);
		initHead();
		render("/blog/content.html");
	}
	
	
	public void more() throws Exception {
		
		initHead();
		String type=URLDecoder.decode(getPara(0), "utf-8");
		setAttr("more",Blog.dao.paginate(1, 1000,type));
		render("/blog/more.html");
	}
	
	
	public void openMail() throws Exception {
		
		try{
			
			Desktop desktop = null;
            if (Desktop.isDesktopSupported()) {
                     desktop = Desktop.getDesktop();
            }
            desktop.mail(new URI("mailto:terry@flyingfat.net"));	
            
		}catch(Exception e){
			e.printStackTrace();
		}
		renderNull();
	}
	
	public void bookMarkParser() throws  Exception{
		
		String local="C:\\Users\\asus\\Desktop\\bookmarks_14-6-5.html";
		Parser parser = new Parser(local);
		NodeFilter filter = new TagNameFilter("A");
		
		
		String context="";
		int hrefCount=0;
		int textCount=0;
		for(NodeIterator it=parser.parse(filter).elements();it.hasMoreNodes();){
			Blog blog=new Blog();
			Node node=it.nextNode();
			String html=node.toHtml();
			
			Node pNode=node.getParent().getParent().getPreviousSibling().getPreviousSibling();
			int beginType=pNode.toHtml().indexOf(">");
			int lastType=pNode.toHtml().lastIndexOf("<");
			String type=pNode.toHtml().substring(beginType+1,lastType);
			System.out.println(type);
			blog.set("jtype", type);
			
			int beginHref=html.indexOf("http");
			int lastHref=html.indexOf(" ADD_DATE");
			if(beginHref!=-1 && lastHref!=-1){
				String href=html.substring(beginHref, lastHref-1);
				blog.set("url", href);
				hrefCount++;
			}
			
			int beginText=html.indexOf(">");
			int lastHText=html.indexOf("</A>");
			if(beginHref!=-1 && lastHref!=-1){
				String title=html.substring(beginText+1, lastHText);
				blog.set("title", title);
				blog.set("content", title);
				textCount++;
			}
			
			context+=node.getText()+"\n";;
			//context+=node.toHtml()+"\n";
			
			blog.save();
		}
		
		System.out.println(hrefCount);
		System.out.println(textCount);
		renderText(context);
	}
	
	
	public void initHead(){
		setAttr("java", Blog.dao.paginate(1, 10,"java"));
		setAttr("socket", Blog.dao.paginate(1, 10,"网络编程"));
		setAttr("web", Blog.dao.paginate(1,10, "web应用"));
		setAttr("linux", Blog.dao.paginate(1, 10,"linux"));
		setAttr("mine", Blog.dao.paginate(1, 10,"原创"));
	}
	
	
	
	public void save() {
		
		String domain=getPara("domain");
		String id=getPara("id");
		String value=getPara("value");
		
		String url=getPara("url");
		String title=getPara("title");
		String type=getPara("jtype");
		
		try{
			String sql="select * from blog_map where domain = '"+domain+"'";
			List<BlogMap> maps=BlogMap.dao.find(sql);
			if(maps.size()==0){
				BlogMap  map=new BlogMap();
				map.set("domain", domain);
				map.set("id", id);
				map.set("value", value);
				map.save();
			}
			
			Blog blog=new Blog();
			blog.set("url", url);
			blog.set("title", title);
			blog.set("jtype", type);
			String content=catchHtmlByParser(url,id,value);
			blog.set("content", content);
			blog.save();
			
			renderJson("ret","true");
		}catch(Exception e){
			e.printStackTrace();
		}
		    renderJson("ret","false");
	}
	
}


