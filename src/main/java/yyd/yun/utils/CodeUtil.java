package yyd.yun.utils;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class CodeUtil {
	/** 
	 *  生成web版本二维码 
	 * @param url 要生成二维码的路径 
	 * @param response response对象 
	 * @param width 二维码宽度 
	 * @param height 二维码高度 
	 * @throws IOException 
	 */  
	public static void getTwoDimension(String url,HttpServletResponse response,int width,int height) throws IOException{  
	 if (url != null && !"".equals(url)) {  
	         ServletOutputStream stream = null;  
	         try {  
	             stream = response.getOutputStream();  
	             QRCodeWriter writer = new QRCodeWriter();  
	             BitMatrix m = writer.encode(url, BarcodeFormat.QR_CODE, height, width);  
	             MatrixToImageWriter.writeToStream(m, "png", stream);  
	             System.out.println(url);
	         } catch (WriterException e) {  
	             e.printStackTrace();  
	            /* logger.error("生成二维码失败!");  */
	         } finally {  
	             if (stream != null) {  
	                 stream.flush();  
	                 stream.close();  
	             }  
	         }  
	     }  
	}  
}
