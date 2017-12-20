package test.imageCompress;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.name.Rename;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

/**
 * ͼƬѹ������
 * Created by zhuyf on 2017/3/28.
 */
public class ImgCompress {
    private Image img;
    private int width;
    private int height;
    private String imagePath;
    public static void main(String[] args) throws Exception {
        System.out.println("��ʼ��" + new Date().toLocaleString());

        String filePath = "C:\\Inspection_Data\\2C142303479B4CB4A338C9A65D783B64\\VISIBLE_LIGHT\\3BE8AC86540440AA8DCD94FF481B2194";
        File f = new File(filePath);
        //��ͼƬ����ѹ������
        compress(f);
        System.out.println("������" + new Date().toLocaleString());
    }

    /**
     * ��ָ��Ŀ¼�µ�����ͼƬ�ļ�����ѹ������
     * @param file
     */
    public static void compress(File file) throws IOException {
        if (file.isDirectory()) {
            //���ļ�Ϊ�ļ������������
            File[] fl = file.listFiles();
            if(null!=fl){
            	for (int i = 0; i < fl.length; i++) {
            		compress(fl[i]);
            	}
            }
        }else{
            // ����ļ���׺��
            String tmpName = file.getPath().substring(file.getPath().lastIndexOf(".") + 1,
                    file.getPath().length());
            //���ļ�ΪͼƬ�����ͼƬѹ������
            if("jpg".equals(tmpName)||"bmp".equals(tmpName)){
                ImgCompress imgCom = new ImgCompress(file);
                imgCom.resizeFix(640,480);
            }
        }
    }
    /**
     * ���캯��
     */
    public ImgCompress(File file) throws IOException {
        img = ImageIO.read(file);      // ����Image����
        width = img.getWidth(null);    // �õ�Դͼ��
        height = img.getHeight(null);  // �õ�Դͼ��
        imagePath = file.getPath();
    }
    /**
     * ���տ�Ȼ��Ǹ߶Ƚ���ѹ��
     * @param w int �����
     * @param h int ���߶�
     */
    public void resizeFix(int w, int h) throws IOException {
        if (width / height > w / h) {
            resizeByWidth(w);
        } else {
            resizeByHeight(h);
        }
    }
    /**
     * �Կ��Ϊ��׼���ȱ�������ͼƬ
     * @param w int �¿��
     */
    public void resizeByWidth(int w) throws IOException {
        int h = (int) (height * w / width);
        resize(w, h);
    }
    /**
     * �Ը߶�Ϊ��׼���ȱ�������ͼƬ
     * @param h int �¸߶�
     */
    public void resizeByHeight(int h) throws IOException {
        int w = (int) (width * h / height);
        resize(w, h);
    }
    /**
     * ǿ��ѹ��/�Ŵ�ͼƬ���̶��Ĵ�С
     * @param w int �¿��
     * @param h int �¸߶�
     */
    public void resize(int w, int h) throws IOException {
        // SCALE_SMOOTH �������㷨 ��������ͼƬ��ƽ���ȵ� ���ȼ����ٶȸ� ���ɵ�ͼƬ�����ȽϺ� ���ٶ���
        BufferedImage image = new BufferedImage(w, h,BufferedImage.TYPE_INT_RGB );
        image.getGraphics().drawImage(img, 0, 0, w, h, null); // ������С���ͼ
        File destFile = new File(imagePath);
        FileOutputStream out = new FileOutputStream(destFile); // ������ļ���
        // ��������ʵ��bmp��png��gifתjpg
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
        encoder.encode(image); // JPEG����
        out.close();
    }
    /**
     * ʹ��thumbnailatorѹ��ͼƬ���̶��Ĵ�С
     * @param dic File ͼƬĿ¼
     * @param width int �¿��
     * @param height int �¸߶�
     * @param prefix String ͼƬ��׺��	"jpg","png","bmp"��
     * @param rename Rename ͼƬ������ģʽ	Rename.NO_CHANGE ����ԭ�ļ�
     */
    public static void resize(File dic,int width,int height,String prefix,Rename rename) throws IOException{
    	
    	Thumbnails.of(dic.listFiles())         
    			.size(width, height)         
    			.outputFormat(prefix)         
    			.toFiles(rename);
    }
}
