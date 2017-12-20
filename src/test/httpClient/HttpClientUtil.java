package test.httpClient;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

//import org.apache.commons.httpclient.Header;
//import org.apache.commons.httpclient.HeaderElement;
//import org.apache.commons.httpclient.HttpClient;
//import org.apache.commons.httpclient.HttpException;
//import org.apache.commons.httpclient.NameValuePair;
//import org.apache.commons.httpclient.methods.GetMethod;
//import org.apache.http.impl.client.DefaultHttpClient;

public class HttpClientUtil {
//	public static void main(String[] args) {
//		HttpClient client = new HttpClient();   
////		HttpClient client = new HttpClient();
//		GetMethod httpGet = new GetMethod("http://127.0.0.1:8080/inspectionData/iReport-5.6.0-windows-installer.exe");
//		InputStream in = null;
//		FileOutputStream out = null;
//		try {
//			// 执行get方法
//			client.executeMethod(httpGet);
//			// 获取response的返回头信息
//			Header contentHead = httpGet.getResponseHeader("Content-Disposition");
//			// 开始解析文件头信息，这里使用的是HeaderElement对象作为文件头的基础信息
//			HeaderElement[] elements = contentHead.getElements();
//			String filerName = null;
//			for (HeaderElement el : elements) {
//				// 遍历，获取filename。filename信息对应的就是下载文件的文件名称。
//				NameValuePair pair = el.getParameterByName("filename");
//				System.out.println(pair.getName() + ":" + pair.getValue());
//				filerName = pair.getValue();
//			}
//			// 由于这个地址是下载地址，返回的是字节流信息。所有这里直接将返回的body转换成字节流
//			in = httpGet.getResponseBodyAsStream();
//
//			out = new FileOutputStream(new File("D:\\" + filerName));
//
//			byte[] b = new byte[1024];
//			int len = 0;
//			byte[] content = new byte[0];
//			int length = 0;
//			// 写文件，这里将所有的内容都缓存到内存中，最后一次性写入
//			while ((len = in.read(b)) > 0) {
//				length = content.length;
//				content = Arrays.copyOf(content, length + len);// 扩容
//				System.arraycopy(b, 0, content, length, len);// 将第二个数组与第一个数组合并
//			}
//			out.write(content, 0, content.length);
//			in.close();
//			out.close();
//
//		} catch (HttpException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			if (in != null) {
//				try {
//					in.close();
//				} catch (IOException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//			}
//			if (out != null)
//				try {
//					out.close();
//				} catch (IOException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//			e.printStackTrace();
//		} finally {
//			httpGet.releaseConnection();
//		}
//		System.out.println("download, success!!");
//	}
	
	
	
	
	
	/**  
     * 发送 get请求  
     */  
    public void get() {
//    	DefaultHttpClient httpclient = new DefaultHttpClient();   
//    	getDefaultHostConfiguration.setProxy()    	
    	
    }  
}
