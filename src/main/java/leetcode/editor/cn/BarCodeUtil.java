package leetcode.editor.cn;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.lang.StringUtils;
import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;

/**
 * @author ：xinze
 * @date ：Created by 2020/5/6 19:55
 * @description：
 * @modified By：
 * @version:
 */

public class BarCodeUtil {

    /**
     *具体实现
     * @param msg
     * @param path
     */
    public static void getBarCode(String msg,String path){
        BitmapCanvasProvider canvas = null;
        try {
            File file=new File(path);
            OutputStream ous=new FileOutputStream(file);
            if(StringUtils.isEmpty(msg) || ous==null) {
                return;
            }
            //选择条形码类型(好多类型可供选择)
            Code128Bean bean=new Code128Bean();
            //设置长宽
            final double moduleWidth=0.20;
            final int resolution=150;
            bean.setModuleWidth(moduleWidth);
            bean.doQuietZone(false);
            String format = "image/png";
            // 输出流
            canvas = new BitmapCanvasProvider(ous, format,
                resolution, BufferedImage.TYPE_BYTE_BINARY, false, 0);
            //生成条码
            bean.generateBarcode(canvas,msg);
            canvas.finish();
        }catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
        }

    }

    public static void main(String[] args) {
        String msg = "1234567";
        String path = "/Users/fang/Downloads/pilar666.jpg";
        BarCodeUtil.getBarCode(msg,path);
    }

}
