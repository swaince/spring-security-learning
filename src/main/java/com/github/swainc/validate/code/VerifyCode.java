package com.github.swainc.validate.code;

import lombok.Data;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

/**
 * @author swaince
 * @date 2019/12/18 8:47 下午
 */
@Data
public class VerifyCode {

    /**
     * 图片长
     */
    private int width;

    /**
     * 图片宽
     */
    private int height;

    /**
     * 验证码字符个数
     */
    private int charLength ;

    /**
     * Random类 生成随机数
     */
    private Random random = new Random();

    /**
     * 列举验证图片中验证码的字体类型： 宋体", "华文楷体", "黑体", "华文新魏", "华文隶书", "微软雅黑", "楷体_GB2312"
     */
    private String[] fontNames  = {"宋体", "华文楷体", "黑体", "微软雅黑", "楷体_GB2312"};
    /**
     * 验证码可选字符
     */
    private String codes="23456789abcdefghjkmnopqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ";
    /**
     * 背景色
     */
    private Color bgColor  = new Color(255, 255, 255);
    /**
     * 验证码上的文本
     */
    private String text ;



    public VerifyCode() {

    }

    public VerifyCode(int width, int height, int charLength) {
        super();
        this.width = width;
        this.height = height;
        this.charLength = charLength;
    }


    /**
     * 生成随机的颜色
     * @return
     */
    private Color randomColor () {
        int red = random.nextInt(150);
        int green = random.nextInt(150);
        int blue = random.nextInt(150);
        return new Color(red, green, blue);
    }

    /**
     * 生成随机的字体
     * @return
     */
    private Font randomFont () {
        int index = random.nextInt(fontNames.length);
        String fontName = fontNames[index];//生成随机的字体名称
        int style = random.nextInt(4);//生成随机的样式, 0(无样式), 1(粗体), 2(斜体), 3(粗体+斜体)
        int size = random.nextInt(5) + 24; //生成随机字号, 24 ~ 28
        return new Font(fontName, style, size);
    }

    /**
     * 画干扰线
     * @param image
     */
    private void drawLine (BufferedImage image) {
        //一共画3条
        int num  = 3;
        Graphics2D g2 = (Graphics2D)image.getGraphics();
        //生成两个点的坐标，即4个值
        for(int i = 0; i < num; i++) {
            int x1 = random.nextInt(width);
            int y1 = random.nextInt(height);
            int x2 = random.nextInt(width);
            int y2 = random.nextInt(height);
            g2.setStroke(new BasicStroke(1.5F));
            //干扰线是蓝色
            g2.setColor(Color.BLUE);
            //画线
            g2.drawLine(x1, y1, x2, y2);
        }
    }

    /**
     * 随机生成一个字符
     * @return
     */
    private char randomChar () {
        int index = random.nextInt(codes.length());
        return codes.charAt(index);
    }

    /**
     * 创建BufferedImage
     * @return
     */
    private BufferedImage createImage () {
        //宽，高，图片的类型
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = (Graphics2D)image.getGraphics();
        g2.setColor(this.bgColor);
        g2.fillRect(0, 0, width, height);
        return image;
    }

    /**
     * 返回验证码图片上的文本
     * @return
     */
    public String getText () {
        return text;
    }

    /**
     * 保存图片到指定的输出流
     * @param image
     * @param out
     * @throws IOException
     */
    public static void output (BufferedImage image, OutputStream out)
            throws IOException {
        ImageIO.write(image, "JPEG", out);
    }

    /**
     * 生成图形验证码
     * @return
     */
    public BufferedImage getImage () {
        //创建图片缓冲区
        BufferedImage image = createImage();
        //得到绘制环境
        Graphics2D g2 = (Graphics2D)image.getGraphics();
        //用来装载生成的验证码文本
        StringBuilder buff = new StringBuilder();
        // 向图片中画4个字符 循环四次，每次生成一个字符
        for(int i = 0; i < charLength; i++)  {
            //随机生成一个字母
            String s = randomChar() + "";
            //把字母添加到buff中
            buff.append(s);
            //设置当前字符的x轴坐标,注意图片宽度不同设置这里控制字符显示完全
            float x = i * 1.0F * width / 6;
            //设置随机字体
            g2.setFont(randomFont());
            //设置随机颜色
            g2.setColor(randomColor());
            //画图
            g2.drawString(s, x, height -5);
        }
        //把生成的字符串赋给了this.text
        this.text = buff.toString();
        //添加干扰线
        drawLine(image);
        return image;
    }


}
