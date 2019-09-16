package chapter4.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class ValidateCodeTest {
    static char[] codeSequence = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
    'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
    '2', '3', '4', '5', '6', '7', '8', '9'};
    static int charNum = codeSequence.length;
    public static void main(String[] args) throws IOException {
        generateCode("E:\\programes\\storage\\src\\main\\resources\\img\\validate.jpg");
    }

    public static void generateCode(String filePath) throws IOException {
        // 首先定义验证码边框
        int width = 80;
        int height = 32;
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // 定义图片上的图形和干扰线
        Graphics2D gd = bufferedImage.createGraphics();
        gd.setColor(Color.LIGHT_GRAY);  // 填充
        gd.fillRect(0, 0, width, height);
        gd.setColor(Color.BLACK); // 边框
        gd.drawRect(0, 0, width - 1, height - 1);
        // 随机产生 16 条灰色干扰线，是图像中的认证码不易识别
        gd.setColor(Color.gray);
        // 创建一个随机数生成器类，用于随机产生干扰线
        Random random = new Random();
        for (int i = 0; i < 16; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int x1 = random.nextInt(12);
            int y1 = random.nextInt(12);
            gd.drawLine(x, y, x + x1, y + y1);
        }

        // 计算字体的位置
        int codeCount = 4;
        int fontHeight;
        int codeX;
        int codeY;

        codeX = (width - 2) / (codeCount + 1);
        fontHeight = height - 10;
        codeY = height - 7;

        // 创建字体，字体的大小根据图片的高度来定
        Font font = new Font("Fixedsys", Font.PLAIN, fontHeight);
        gd.setFont(font);

        // 随机产生 codeCount 数字的验证码
        for (int i = 0; i < codeCount; i++) {
            // 每次随机拿一个字母，赋予随机的颜色
            String  strRand = String.valueOf(codeSequence[random.nextInt(charNum)]);
            int red = random.nextInt(255);
            int green = random.nextInt(255);
            int blue = random.nextInt(255);
            gd.setColor(new Color(red, green, blue));
            // 把字母放在图片中
            gd.drawString(strRand, (i + 1) * codeX, codeY);
        }

        ImageIO.write(bufferedImage, "jpg", new File(filePath));
    }
}
