package jsoup;

import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.DocumentType;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

public class JsoupTest {
    static String url="http://www.cnblogs.com/zyw-205520/archive/2012/12/20/2826402.html";
    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        
        BolgBody();
        //Blog();
        /*
         * Document doc = Jsoup.connect("http://www.oschina.net/")
         * .data("query", "Java") // ������� .userAgent("I �� m jsoup") // ����
         * User-Agent .cookie("auth", "token") // ���� cookie .timeout(3000) //
         * �������ӳ�ʱʱ�� .post();
         */// ʹ�� POST �������� URL

        /*
         * // ���ļ��м��� HTML �ĵ� File input = new File("D:/test.html"); Document doc
         * = Jsoup.parse(input,"UTF-8","http://www.oschina.net/");
         */
    }

    /**
     * ��ȡָ��HTML �ĵ�ָ����body
     * @throws IOException
     */
    private static void BolgBody() throws IOException {
        // ֱ�Ӵ��ַ��������� HTML �ĵ�
        String html = "<html><head><title> ��Դ�й����� </title></head>"
                + "<body><p> ������ jsoup ��Ŀ��������� </p></body></html>";
        Document doc = Jsoup.parse(html);
        System.out.println(doc.body());
        
        
        // �� URL ֱ�Ӽ��� HTML �ĵ�
//        Document doc2 = Jsoup.connect(url).get();
        
    	Document doc2 = Jsoup.connect("http://read.qidian.com/chapter/tIz22GXnlzY1/UaCNB0GCoLQex0RJOkJclQ2").get();
        String title = doc2.body().toString();
        Node node = doc2.childNodes().get(1);
        System.out.println(title);
    }

    /**
     * ��ȡ�����ϵ����±��������
     */
    public static void article() {
        Document doc;
        try {
            doc = Jsoup.connect("http://www.cnblogs.com/zyw-205520/").get();
            Elements ListDiv = doc.getElementsByAttributeValue("class","postTitle");
            for (Element element :ListDiv) {
                Elements links = element.getElementsByTag("a");
                for (Element link : links) {
                    String linkHref = link.attr("href");
                    String linkText = link.text().trim();
                    System.out.println(linkHref);
                    System.out.println(linkText);
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    /**
     * ��ȡָ���������µ�����
     */
    public static void Blog() {
        Document doc;
        try {
            doc = Jsoup.connect("http://www.cnblogs.com/zyw-205520/archive/2012/12/20/2826402.html").get();
            Elements ListDiv = doc.getElementsByAttributeValue("class","postBody");
            for (Element element :ListDiv) {
                System.out.println(element.html());
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

}