package jsoup;

import java.io.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;

import bean.CharacterPattern;

import org.jsoup.Connection.Response;
import org.jsoup.nodes.Document;

import common.DBUtils;

/** 
 * ��ȡС˵txt�ļ�
 *   
 * @author lixiaolin 
 * @createDate 2016-06-28 19:38 
 **/
public class JsoupForNovelUtil {
	public static void main(String[] args) throws SQLException {
		//�ص��������ƾ�
//		String baseUrl = "http://www.56shuku.org/files/article/html/134/134005/";
//		String nextUrl = "27638205.html";
//		String destFilePath = "D:/book/�ص��������ƾ�/";
//		System.out.println("��ʼ��ȡ����...");
//		long startTime = System.currentTimeMillis();
//		
//		CharacterPattern parrern = new CharacterPattern();
//		parrern.setTitlePat(Pattern.compile("<H1>(.+?)</H1>"));
//		parrern.setNextPat(Pattern.compile("Ŀ ¼</a><a href=\"(.+?)\">"));
//		parrern.setContentPat(Pattern.compile("<P>(.+?)</P>"));
//		parrern.setBookNamePat(Pattern.compile("<H2>(.+?)</H2>"));
//		getNovel(parrern,baseUrl, nextUrl, destFilePath);
//		long endTime = System.currentTimeMillis();
//		System.out.println("��ʱ " + (endTime - startTime) / 1000 + "��...");
		
		//��������
		String baseUrl = "http://www.ybdu.com/";
		String nextUrl = "xiaoshuo/14/14971/4832879.html";
		String destFilePath = "D:/book/��������/";
		System.out.println("��ʼ��ȡ����...");
		long startTime = System.currentTimeMillis();
		
		CharacterPattern parrern = new CharacterPattern();
		//��������
		parrern.setTitlePat(Pattern.compile("<h1>(.+?)</h1>"));
		//��һҳ����
		parrern.setNextPat(Pattern.compile("next_page=\"/(.+?)\""));
		//��������			<key>\naaa\nbbb\n<key>\nccc\nddd\n<key>\neee\nfff
		parrern.setContentPat(Pattern.compile("class=\"contentbox\">\n(.+?)\n        <div"));
		//��������
		parrern.setBookNamePat(Pattern.compile("��(.+?)��"));
		getNovel(parrern,baseUrl, nextUrl, destFilePath);
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
	public static void getNovel(CharacterPattern parrern ,String baseUrl, String nextUrl, String destFilePath) throws SQLException {
		
		try {
			
			String realUrl, resultContent;
			StringBuffer sb = new StringBuffer();
//			// ��������ƥ����ʽ
//			Pattern contentPat = Pattern.compile("<P>(.+?)</P>");
//			// ����������ʽ
//			Pattern titlePat = Pattern.compile("<H1>(.+?)</H1>");
//			// ����������ʽ
//			Pattern bookNamePat = Pattern.compile("<H2>(.+?)</H2>");
//			// ��һ��������ʽ
//			Pattern nextPat = Pattern.compile("Ŀ ¼</a><a href=\"(.+?)\">");
			Matcher matcher;
			// ��һ�ŵ�url�� / ��ͷ���������½�
			while (!nextUrl.startsWith("/")) {
				
				System.out.println(nextUrl);
				realUrl = baseUrl + nextUrl;
				System.out.println(realUrl);
				
				String bookName = "";
				String title = "";
				String content = "";
				
				Response resultImageResponse = Jsoup.connect(realUrl).
						timeout(1000).ignoreContentType(true).execute();
				resultImageResponse.charset("gbk");
				
				Document doc2 = Jsoup.connect(realUrl).get();
				
				sb.append(resultImageResponse.body());
				
				// �滻�ո�ͻ��з�
				resultContent = sb.toString();
				// ƥ�����
				matcher = parrern.getTitlePat().matcher(resultContent);
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
				fw.write("\r\n");
				// ƥ������
				matcher = parrern.getContentPat().matcher(resultContent);
				if (matcher.find()) {
					content = matcher.group(1).replaceAll("<br /><br />", "\n\n").replaceAll("&nbsp;", "");
					fw.write(content);
				}
				// ƥ����һҳurl
				matcher = parrern.getNextPat().matcher(resultContent);
				if (matcher.find()) {
					nextUrl = matcher.group(1);
				}
				// ƥ������
				matcher = parrern.getBookNamePat().matcher(resultContent);
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