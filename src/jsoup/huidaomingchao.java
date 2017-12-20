package jsoup;

import java.io.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.Connection.Response;

import common.DBUtils;

/** 
 * ��ȡС˵txt�ļ�
 *   
 * @author lixiaolin 
 * @createDate 2016-06-28 19:38 
 **/
public class huidaomingchao {
	public static void main(String[] args) throws SQLException {
		String baseUrl = "http://www.56shuku.org/files/article/html/134/134005/";
		String nextUrl = "27638205.html";
		String destFilePath = "D:/book/�ص��������ƾ�/";
		System.out.println("��ʼ��ȡ����...");
		long startTime = System.currentTimeMillis();
		getNovel(baseUrl, nextUrl, destFilePath);
		long endTime = System.currentTimeMillis();
		System.out.println("��ʱ " + (endTime - startTime) / 1000 + "��...");
	}
/**
 * 
 * @param baseUrl	��Url
 * @param nextUrl	��һ��Url
 * @param destFilePath	��������Ŀ¼
 * @throws SQLException
 */
	public static void getNovel(String baseUrl, String nextUrl, String destFilePath) throws SQLException {
		
		try {
			
			String realUrl, resultContent;
			StringBuffer sb = new StringBuffer();
//			BufferedReader br = null;
			// ��������ƥ����ʽ
			Pattern contentPat = Pattern.compile("<P>(.+?)</P>");
			// ����������ʽ
			Pattern titlePat = Pattern.compile("<H1>(.+?)</H1>");
			// ����������ʽ
			Pattern bookNamePat = Pattern.compile("<H2>(.+?)</H2>");
			// ��һ��������ʽ
			Pattern nextPat = Pattern.compile("Ŀ ¼</a><a href=\"(.+?)\">");
			Matcher matcher;
			// ��һ�ŵ�url�� / ��ͷ���������½�
			while (!nextUrl.startsWith("/")) {
				
				System.out.println(nextUrl);
				realUrl = baseUrl + nextUrl;
				System.out.println(realUrl);
				// ��ȡĿ��url��response
//				br = new BufferedReader(new InputStreamReader(new URL(realUrl).openStream()));
				
				String bookName = "";
				String title = "";
				String content = "";
				
				Response resultImageResponse = Jsoup.connect(realUrl).
						timeout(1000).ignoreContentType(true).execute();
				resultImageResponse.charset("gbk");
				sb.append(resultImageResponse.body());
				
				// �滻�ո�ͻ��з�
				resultContent = sb.toString().replaceAll("&nbsp;", "");
				// ƥ�����
				matcher = titlePat.matcher(resultContent);
				if (matcher.find()) {
					title = matcher.group(1);
				}
				File destFile = new File(destFilePath+title+".txt");
				FileWriter fw = null;
				// Ŀ���ļ�������ɾ��
				if (destFile.exists()) {
					destFile.delete();
				}
				destFile.createNewFile();
				//���½�Ŀ¼����txt
				fw = new FileWriter(destFile);
				
				// ����
				fw.write("\r\n\r\n");
				fw.write(title);
//				// ƥ�����
//				matcher = titlePat.matcher(resultContent);
//				if (matcher.find()) {
//					title = matcher.group(1);
//					fw.write(title);
//				}
				fw.write("\r\n");
				// ƥ������
				matcher = contentPat.matcher(resultContent);
				if (matcher.find()) {
					content = matcher.group(1).replaceAll("<br /><br />", "\n\n");
					fw.write(content);
				}
				// ƥ����һҳurl
				matcher = nextPat.matcher(resultContent);
				if (matcher.find()) {
					nextUrl = matcher.group(1);
				}
				// ƥ������
				matcher = bookNamePat.matcher(resultContent);
				if (matcher.find()) {
					bookName = matcher.group(1);
				}
				//����������
//				saveBook(title,bookName,content);
				// ���
				sb.delete(0, sb.length());
				
				try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			
		}
	}
	
	public static void saveBook(String title,String bookName,String content) throws SQLException{
		
		DBUtils db = DBUtils.getInstance();
        db.getConnection();
        String sql = "insert into mybook(bookName,title,content)value(?,?,?)";
        List<Object> params = new ArrayList<Object>();
        params.add(0,bookName);
        params.add(1,title);
        params.add(2,content);
        db.executeUpdate(sql,params);
        
	}
}