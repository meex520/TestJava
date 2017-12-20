package test.imageCompress;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.name.Rename;

public class TestForMagickImage {
    public static void main(String[] args) throws IOException {
    	System.out.println("开始：" + new Date().toLocaleString());
    	Thumbnails.of(new File("C:\\Inspection_Data\\2C142303479B4CB4A338C9A65D783B64\\VISIBLE_LIGHT\\3BE8AC86540440AA8DCD94FF481B2194")
    			.listFiles())         
    			.size(640, 480)         
    			.outputFormat("jpg")         
    			.toFiles(Rename.NO_CHANGE);
    	System.out.println("结束：" + new Date().toLocaleString());
    }
    
    
//    /**
//     * 对指定目录下的所有图片文件进行压缩操作
//     * @param file
//     */
//    public static void compress(File file) throws IOException {
//        if (file.isDirectory()) {
//            //该文件为文件夹则迭代遍历
//            File[] fl = file.listFiles();
//            if(null!=fl){
//            	for (int i = 0; i < fl.length; i++) {
//            		compress(fl[i]);
//            	}
//            }
//        }else{
//            // 获得文件后缀名
//            String tmpName = file.getPath().substring(file.getPath().lastIndexOf(".") + 1,
//                    file.getPath().length());
//            //该文件为图片则进行图片压缩操作
//            if("jpg".equals(tmpName)||"bmp".equals(tmpName)){
//                ImgCompress imgCom = new ImgCompress(file);
//                imgCom.resizeFix(1080, 720);
//            }
//        }
//    }
}