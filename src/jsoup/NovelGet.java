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
public class NovelGet {
	public static void main(String[] args) throws SQLException {
		String baseUrl = "http://www.00ksw.net/html/1/1343/";
		String nextUrl = "597361.html";
		String destFilePath = "D://test.txt";
		System.out.println("��ʼ��ȡ����...");
		long startTime = System.currentTimeMillis();
		getNovel(baseUrl, nextUrl, destFilePath);
		long endTime = System.currentTimeMillis();
		System.out.println("��ʱ " + (endTime - startTime) / 1000 + "��...");
	}

	public static void getNovel(String baseUrl, String nextUrl, String destFilePath) throws SQLException {
		File destFile = new File(destFilePath);
		FileWriter fw = null;
		try {
			// Ŀ���ļ�������ɾ��
			if (destFile.exists()) {
				destFile.delete();
			}
			destFile.createNewFile();
			fw = new FileWriter(destFile);
			String realUrl, resultContent;
			StringBuffer sb = new StringBuffer();
//			BufferedReader br = null;
			// ��������ƥ����ʽ
			Pattern contentPat = Pattern.compile("<div id=\"content\">(.+?)</div>");
			// ����������ʽ
			Pattern titlePat = Pattern.compile("readtitle = \"(.+?)\"");
			// ����������ʽ
			Pattern bookNamePat = Pattern.compile("booktitle = \"(.+?)\"");
			// ��һ��������ʽ
			Pattern nextPat = Pattern.compile("&rarr; <a href=\"(.+?)\">");
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
				
//				String line;
//				while ((line = br.readLine()) != null) {
//					sb.append(line);
//				}
				// �滻�ո�ͻ��з�
				resultContent = sb.toString().replaceAll("&nbsp;", "");
				// ����
				fw.write("\r\n\r\n");
				// ƥ�����
				matcher = titlePat.matcher(resultContent);
				if (matcher.find()) {
					title = matcher.group(1);
					fw.write(title);
				}
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
				saveBook(title,bookName,content);
				// ���
				sb.delete(0, sb.length());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
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