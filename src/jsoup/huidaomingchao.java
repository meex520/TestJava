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
 * 爬取小说txt文件
 *   
 * @author lixiaolin 
 * @createDate 2016-06-28 19:38 
 **/
public class huidaomingchao {
	public static void main(String[] args) throws SQLException {
		String baseUrl = "http://www.56shuku.org/files/article/html/134/134005/";
		String nextUrl = "27638205.html";
		String destFilePath = "D:/book/回到明朝考科举/";
		System.out.println("开始爬取数据...");
		long startTime = System.currentTimeMillis();
		getNovel(baseUrl, nextUrl, destFilePath);
		long endTime = System.currentTimeMillis();
		System.out.println("用时 " + (endTime - startTime) / 1000 + "秒...");
	}
/**
 * 
 * @param baseUrl	根Url
 * @param nextUrl	下一张Url
 * @param destFilePath	保存文章目录
 * @throws SQLException
 */
	public static void getNovel(String baseUrl, String nextUrl, String destFilePath) throws SQLException {
		
		try {
			
			String realUrl, resultContent;
			StringBuffer sb = new StringBuffer();
//			BufferedReader br = null;
			// 正文正则匹配表达式
			Pattern contentPat = Pattern.compile("<P>(.+?)</P>");
			// 标题正则表达式
			Pattern titlePat = Pattern.compile("<H1>(.+?)</H1>");
			// 书名正则表达式
			Pattern bookNamePat = Pattern.compile("<H2>(.+?)</H2>");
			// 下一章正则表达式
			Pattern nextPat = Pattern.compile("目 录</a><a href=\"(.+?)\">");
			Matcher matcher;
			// 下一张的url以 / 开头则是最新章节
			while (!nextUrl.startsWith("/")) {
				
				System.out.println(nextUrl);
				realUrl = baseUrl + nextUrl;
				System.out.println(realUrl);
				// 获取目标url的response
//				br = new BufferedReader(new InputStreamReader(new URL(realUrl).openStream()));
				
				String bookName = "";
				String title = "";
				String content = "";
				
				Response resultImageResponse = Jsoup.connect(realUrl).
						timeout(1000).ignoreContentType(true).execute();
				resultImageResponse.charset("gbk");
				sb.append(resultImageResponse.body());
				
				// 替换空格和换行符
				resultContent = sb.toString().replaceAll("&nbsp;", "");
				// 匹配标题
				matcher = titlePat.matcher(resultContent);
				if (matcher.find()) {
					title = matcher.group(1);
				}
				File destFile = new File(destFilePath+title+".txt");
				FileWriter fw = null;
				// 目标文件存在则删除
				if (destFile.exists()) {
					destFile.delete();
				}
				destFile.createNewFile();
				//将章节目录保存txt
				fw = new FileWriter(destFile);
				
				// 换行
				fw.write("\r\n\r\n");
				fw.write(title);
//				// 匹配标题
//				matcher = titlePat.matcher(resultContent);
//				if (matcher.find()) {
//					title = matcher.group(1);
//					fw.write(title);
//				}
				fw.write("\r\n");
				// 匹配正文
				matcher = contentPat.matcher(resultContent);
				if (matcher.find()) {
					content = matcher.group(1).replaceAll("<br /><br />", "\n\n");
					fw.write(content);
				}
				// 匹配下一页url
				matcher = nextPat.matcher(resultContent);
				if (matcher.find()) {
					nextUrl = matcher.group(1);
				}
				// 匹配书名
				matcher = bookNamePat.matcher(resultContent);
				if (matcher.find()) {
					bookName = matcher.group(1);
				}
				//保存书内容
//				saveBook(title,bookName,content);
				// 清空
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