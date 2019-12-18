package com.github.swainc;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

@SpringBootTest
class SecurityApplicationTests {

    @Test
    void contextLoads() {

    }

    @Test
    public void imageCodeGenerate() throws IOException {

        String randomString = getRandomString(); // 调用生成随机字符串方法获取并接受随机字符串
        int width = 100; // 图片宽度
        int height = 30; // 图片高度

        Color color = getRandomColor(); // 获取随机颜色 用于背景色
        Color reverse = getReverseColor(color); // 反色 用于前景色

        BufferedImage bi = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB); // 创建一个彩色图片
        Graphics2D g = bi.createGraphics(); // 获取绘图对象
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16)); // 设置字体
        g.setColor(color); // 设置颜色
        g.fillRect(0, 0, width, height); // 绘制背景
        g.setColor(reverse); // 设置颜色
        g.drawString(randomString, 18, 20); // 绘制随机字符
        for (int i = 0, n = random.nextInt(100); i < n; i++) // 画最多一百个噪音点
        {
            g.drawRect(random.nextInt(width), random.nextInt(height), 1, 1); // 随机噪音点
        }

        ImageIO.write(bi, "JPEG", new File("code.jpeg"));
    }

    // 设置随机字符字典。其中不包含0,o,1,I等难以辨认的字符
    public static final char[] CHARS = { '2', '3', '4', '5', '6', '7', '8',
            '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M',
            'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a',
            'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'p',
            'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

    public static Random random = new Random(); // 随机数对象

    public static String getRandomString() {

        StringBuffer buffer = new StringBuffer(); // 字符串缓存
        for (int i = 0; i < 6; i++) // 六次循环获取字符
        {
            buffer.append(CHARS[random.nextInt(CHARS.length)]); // 每次随机取一个字符
        }
        return buffer.toString();
    }

    public static Color getRandomColor() {

        return new Color(random.nextInt(255), random.nextInt(255),
                random.nextInt(255));
    }

    public static Color getReverseColor(Color c) {

        return new Color(255 - c.getRed(), 255 - c.getGreen(),
                255 - c.getBlue());
    }

}
