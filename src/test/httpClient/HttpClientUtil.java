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
//			// ִ��get����
//			client.executeMethod(httpGet);
//			// ��ȡresponse�ķ���ͷ��Ϣ
//			Header contentHead = httpGet.getResponseHeader("Content-Disposition");
//			// ��ʼ�����ļ�ͷ��Ϣ������ʹ�õ���HeaderElement������Ϊ�ļ�ͷ�Ļ�����Ϣ
//			HeaderElement[] elements = contentHead.getElements();
//			String filerName = null;
//			for (HeaderElement el : elements) {
//				// ��������ȡfilename��filename��Ϣ��Ӧ�ľ��������ļ����ļ����ơ�
//				NameValuePair pair = el.getParameterByName("filename");
//				System.out.println(pair.getName() + ":" + pair.getValue());
//				filerName = pair.getValue();
//			}
//			// ���������ַ�����ص�ַ�����ص����ֽ�����Ϣ����������ֱ�ӽ����ص�bodyת�����ֽ���
//			in = httpGet.getResponseBodyAsStream();
//
//			out = new FileOutputStream(new File("D:\\" + filerName));
//
//			byte[] b = new byte[1024];
//			int len = 0;
//			byte[] content = new byte[0];
//			int length = 0;
//			// д�ļ������ｫ���е����ݶ����浽�ڴ��У����һ����д��
//			while ((len = in.read(b)) > 0) {
//				length = content.length;
//				content = Arrays.copyOf(content, length + len);// ����
//				System.arraycopy(b, 0, content, length, len);// ���ڶ����������һ������ϲ�
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
     * ���� get����  
     */  
    public void get() {
//    	DefaultHttpClient httpclient = new DefaultHttpClient();   
//    	getDefaultHostConfiguration.setProxy()    	
    	
    }  
}
