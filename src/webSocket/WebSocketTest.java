package webSocket;  
  
import java.io.IOException;  
  
import javax.websocket.OnClose;  
import javax.websocket.OnMessage;  
import javax.websocket.OnOpen;  
import javax.websocket.Session;  
import javax.websocket.server.ServerEndpoint;  
/** 
 * ��tomcat7�д���WebSocketServlet�ࣨ���Ѿ���ʱ������tomcat8�г���ɾ�� 
 * �˴�ʹ��@ServerEndpointע�⣬��Ҫ�ǽ�Ŀǰ���ඨ���һ��websocket�������� 
 * ע���ֵ�������ڼ����û����ӵ��ն˷���URL��ַ 
 */  
@ServerEndpoint("/websocket")  
public class WebSocketTest {  
    /** 
     * �����������յ��ͻ��˷��͵���Ϣʱ�����õķ��� 
     * �÷������ܰ���һ��javax.websocket.Session��ѡ���� 
     * ����������������������ѵ�ǰ������Ϣ�ͻ��˵�����Sessionע���ȥ 
     */  
    @OnMessage  
    public void onMessage(String message,Session session) throws IOException, InterruptedException {  
        // ��ӡ�ӿͻ��˻�ȡ������Ϣ  
        System.out.println("�ӿͻ��˽��յ�����Ϣ: " + message);  
        //��ͻ��˵�һ�η�����Ϣ  
        session.getBasicRemote().sendText("=======��ͻ��˵�һ�η�����Ϣ=======");  
        //ÿ����ͻ��˷���һ����Ϣ����������3��  
        int sentMessages = 0;  
        while (sentMessages < 3) {  
            Thread.sleep(1000);  
            session.getBasicRemote().sendText("��ʱ������Ϣ����ǰ�ǵ� " + sentMessages+"��...");  
            sentMessages++;  
        }  
        // ��ͻ��˷������һ����Ϣ  
        session.getBasicRemote().sendText("=======��ͻ��˷������һ����Ϣ=======");  
    }  
    /** 
     * ��һ�����û�����ʱ�����õķ��� 
     * �÷������ܰ���һ��javax.websocket.Session��ѡ���� 
     * ����������������������ѵ�ǰ������Ϣ�ͻ��˵�����Sessionע���ȥ 
     */  
    @OnOpen  
    public void onOpen(Session session) {  
        System.out.println("�ͻ������ӳɹ�");  
    }  
    /** ��һ���û��Ͽ�����ʱ�����õķ���*/  
    @OnClose  
    public void onClose() {  
        System.out.println("�ͻ��˹ر�");  
    }  
}  