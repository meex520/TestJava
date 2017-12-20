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
 * 图片压缩处理
 * Created by zhuyf on 2017/3/28.
 */
public class ImgCompress {
    private Image img;
    private int width;
    private int height;
    private String imagePath;
    public static void main(String[] args) throws Exception {
        System.out.println("开始：" + new Date().toLocaleString());

        String filePath = "C:\\Inspection_Data\\2C142303479B4CB4A338C9A65D783B64\\VISIBLE_LIGHT\\3BE8AC86540440AA8DCD94FF481B2194";
        File f = new File(filePath);
        //对图片进行压缩操作
        compress(f);
        System.out.println("结束：" + new Date().toLocaleString());
    }

    /**
     * 对指定目录下的所有图片文件进行压缩操作
     * @param file
     */
    public static void compress(File file) throws IOException {
        if (file.isDirectory()) {
            //该文件为文件夹则迭代遍历
            File[] fl = file.listFiles();
            if(null!=fl){
            	for (int i = 0; i < fl.length; i++) {
            		compress(fl[i]);
            	}
            }
        }else{
            // 获得文件后缀名
            String tmpName = file.getPath().substring(file.getPath().lastIndexOf(".") + 1,
                    file.getPath().length());
            //该文件为图片则进行图片压缩操作
            if("jpg".equals(tmpName)||"bmp".equals(tmpName)){
                ImgCompress imgCom = new ImgCompress(file);
                imgCom.resizeFix(640,480);
            }
        }
    }
    /**
     * 构造函数
     */
    public ImgCompress(File file) throws IOException {
        img = ImageIO.read(file);      // 构造Image对象
        width = img.getWidth(null);    // 得到源图宽
        height = img.getHeight(null);  // 得到源图长
        imagePath = file.getPath();
    }
    /**
     * 按照宽度还是高度进行压缩
     * @param w int 最大宽度
     * @param h int 最大高度
     */
    public void resizeFix(int w, int h) throws IOException {
        if (width / height > w / h) {
            resizeByWidth(w);
        } else {
            resizeByHeight(h);
        }
    }
    /**
     * 以宽度为基准，等比例放缩图片
     * @param w int 新宽度
     */
    public void resizeByWidth(int w) throws IOException {
        int h = (int) (height * w / width);
        resize(w, h);
    }
    /**
     * 以高度为基准，等比例缩放图片
     * @param h int 新高度
     */
    public void resizeByHeight(int h) throws IOException {
        int w = (int) (width * h / height);
        resize(w, h);
    }
    /**
     * 强制压缩/放大图片到固定的大小
     * @param w int 新宽度
     * @param h int 新高度
     */
    public void resize(int w, int h) throws IOException {
        // SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 优先级比速度高 生成的图片质量比较好 但速度慢
        BufferedImage image = new BufferedImage(w, h,BufferedImage.TYPE_INT_RGB );
        image.getGraphics().drawImage(img, 0, 0, w, h, null); // 绘制缩小后的图
        File destFile = new File(imagePath);
        FileOutputStream out = new FileOutputStream(destFile); // 输出到文件流
        // 可以正常实现bmp、png、gif转jpg
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
        encoder.encode(image); // JPEG编码
        out.close();
    }
    /**
     * 使用thumbnailator压缩图片到固定的大小
     * @param dic File 图片目录
     * @param width int 新宽度
     * @param height int 新高度
     * @param prefix String 图片后缀名	"jpg","png","bmp"等
     * @param rename Rename 图片重命名模式	Rename.NO_CHANGE 覆盖原文件
     */
    public static void resize(File dic,int width,int height,String prefix,Rename rename) throws IOException{
    	
    	Thumbnails.of(dic.listFiles())         
    			.size(width, height)         
    			.outputFormat(prefix)         
    			.toFiles(rename);
    }
}
