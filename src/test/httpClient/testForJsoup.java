package test.httpClient;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;

public class testForJsoup {
	
	 public static void main(String[] args) {
	        try {
	            httpGetFile("http://read.qidian.com/chapter/tIz22GXnlzY1/UaCNB0GCoLQex0RJOkJclQ2","");
	        } catch (IOException e) {
	                e.printStackTrace();
	        }
	    }
	
	
	 /**
     * 使用Jsoup发送请求
     * @param url   请求地址
     * @param cookie 没有则置空
     * @return
     * @throws IOException
     */
    public static void httpGetFile(String url,String cookie) throws IOException{
//    	Document document= Jsoup.connect(url).ignoreContentType(true).get(); 
    	Response resultImageResponse = Jsoup.connect(url).timeout(10000).ignoreContentType(true).execute();
    	// output here
    	FileOutputStream out = (new FileOutputStream(new File("D:/indx.html")));
    	out.write(resultImageResponse.bodyAsBytes());           
    	// resultImageResponse.body() is where the image's contents are.
    	out.close();
    	
//        byte[] bytes = Jsoup.connect(url).ignoreContentType(true).execute().bodyAsBytes();
//        //文件保存位置
//        File saveDir = new File("D:/");
//        if(!saveDir.exists()){
//            saveDir.mkdir();
//        }
//        File file = new File(saveDir+File.separator+"staller.exe");
//        FileOutputStream fos = new FileOutputStream(file);
//        fos.write(bytes);
//        if(fos!=null){
//            fos.close();
//        }
    }
}
