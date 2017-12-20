package test.httpClient;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

//import org.apache.tomcat.util.http.fileupload.IOUtils;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.*;

/**
 * Created by zhuyf on 2017/4/12.
 */
public class HtmlUnitUtil {
	 
    public static void main(String[] args) {
    	System.out.println("开始：" + new Date().toLocaleString());
    	getUrlxx();
    	System.out.println("结束：" + new Date().toLocaleString());
	}
    
/**
 * @throws IOException 
 * @throws MalformedURLException 
 * @throws FailingHttpStatusCodeException 
 * 
 */
    public static void getUrlxx(){
        // 得到浏览器对象，直接New一个就能得到，现在就好比说你得到了一个浏览器了  
    	WebClient webclient =null;
    	try {
    		webclient = new WebClient();  
    		// 做的第一件事，去拿到这个网页，只需要调用getPage这个方法即可  
//    		HtmlPage htmlpage = webclient.getPage("http://127.0.0.1:8080/inspectionData/iReport-5.6.0-windows-installer.exe");  
    		Page page = webclient.getPage("http://192.168.2.71:8080/testForDownLoad/project2013.rar");
    		saveFile(page, "d:/project2013.rar");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    public static void saveFile(Page page, String file) throws Exception {
        InputStream is = page.getWebResponse().getContentAsStream();
        FileOutputStream output = new FileOutputStream(file);
//        IOUtils.copy(is, output);
        output.close();
    }
    
}
