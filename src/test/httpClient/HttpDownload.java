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
			
    		System.out.println("��ʼ��" + new Date().toLocaleString());
    		downLoadFromUrl("http://192.168.2.71:8080/testForDownLoad/project2013.rar","project2013��װ.rar","D:/");
    		System.out.println("������" + new Date().toLocaleString());
		}
    	
    	/**
         * ���ļ��������ṩ��Url�������ļ�
         * @param urlStr
         * @param fileName
         * @param savePath
         * @throws IOException
         */
        public static void  downLoadFromUrl(String urlStr,String fileName,String savePath) throws IOException{
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            if(null!=conn){
            	//���ó�ʱ��Ϊ20��
            	conn.setConnectTimeout(20*1000);
            	//�õ�������
            	InputStream inputStream = conn.getInputStream();  
            	//��ȡ�Լ�����
            	byte[] getData = readInputStream(inputStream);    
            	
            	//�ļ�����λ��
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
         * ���������л�ȡ�ֽ�����
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