package yyd.yun.utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class GenerateHtml {
	
	public void test(){
		//用于存储html字符串  
		StringBuilder stringHtml = new StringBuilder();  
		PrintStream printStream = null;
		try{  
		   //打开文件  
		    printStream = new PrintStream(new FileOutputStream("/Data/test.html"));  
		}catch(FileNotFoundException e){  
		   e.printStackTrace();  
		}  
		//输入HTML文件内容  
		stringHtml.append("<html><head>");  
		stringHtml.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=GBK\">");  
		stringHtml.append("<title>测试报告文档</title>");   
		stringHtml.append("</head>");  
		stringHtml.append("<body style='text-align:center;'>");  
		stringHtml.append("<div><img src='android.png' width='400' height='400' onClick='javascript:download_android()'><br/><br/>");  
		stringHtml.append("<a style='text-align:center;color:#000;font-size:40px;' href='http://resource.yydrobot.com/app/yyd/cn/app.apk'>Android版下载</a><br/>");
		stringHtml.append("<img src='iphone.png' width='400' height='400' onClick='javascript:download_iphone()'><br/>");
		stringHtml.append("<a style='text-align:center;color:#000;font-size:40px;' href='itms-services://?action=download-manifest&url=https://testhttps.yydrobot.com/app/yyd/cn/app.plist'>IOS版下载</a><br/><br/>");
		stringHtml.append("<h1>微信下载不了？</h1><h1>1.点击右上角下拉框</h1><h1>2.选择在浏览器中打开</h1>");
		stringHtml.append("</div></body></html>");  
		try{  
	       //将HTML文件内容写入文件中  
	       printStream.println(stringHtml.toString());  
		}catch (Exception e) {  
		      
		    e.printStackTrace();  
		}  
		
	}
}
