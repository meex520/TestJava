package test.httpClient;

    import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;  
      
    /** 
     * 
     */  
    public class HttpDownload {
    	
    	public static void main(String[] args) throws IOException {
			
    		System.out.println("开始：" + new Date().toLocaleString());
    		downLoadFromUrl("http://192.168.2.71:8080/testForDownLoad/project2013.rar","project2013安装.rar","D:/");
    		System.out.println("结束：" + new Date().toLocaleString());
		}
    	
    	/**
         * 从文件服务器提供的Url中下载文件
         * @param urlStr
         * @param fileName
         * @param savePath
         * @throws IOException
         */
        public static void  downLoadFromUrl(String urlStr,String fileName,String savePath) throws IOException{
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            if(null!=conn){
            	//设置超时间为20秒
            	conn.setConnectTimeout(20*1000);
            	//得到输入流
            	InputStream inputStream = conn.getInputStream();  
            	//获取自己数组
            	byte[] getData = readInputStream(inputStream);    
            	
            	//文件保存位置
            	File saveDir = new File(savePath);
            	if(!saveDir.exists()){
            		saveDir.mkdir();
            	}
            	File file = new File(saveDir+File.separator+fileName);    
            	FileOutputStream fos = new FileOutputStream(file);     
            	fos.write(getData); 
            	if(fos!=null){
            		fos.close();  
            	}
            	if(inputStream!=null){
            		inputStream.close();
            	}
            }
            if(conn!=null){
            	conn.disconnect();
            }
        }
        /**
         * 从输入流中获取字节数组
         * @param inputStream
         * @return
         * @throws IOException
         */
        public static  byte[] readInputStream(InputStream inputStream) throws IOException {  
            byte[] buffer = new byte[1024];  
            int len = 0;  
            ByteArrayOutputStream bos = new ByteArrayOutputStream();  
            while((len = inputStream.read(buffer)) != -1) {  
                bos.write(buffer, 0, len);  
            }  
            bos.close();  
            return bos.toByteArray();  
        }  
    }  