package chapter4.image;

import javafx.scene.effect.ImageInput;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Arrays;
import java.util.Iterator;

/**
 * @Aut: chunlei.wang
 * @Date: 2019/09/16
 * @Desc: 图像的读写
 */
public class ImageTest {
    public static void main(String[] args) {
//        readAndWrite();
//        readComparison();
//        cropImage("E:\\programes\\storage\\src\\main\\resources\\img\\1.jpg",
//                "E:\\programes\\storage\\src\\main\\resources\\img\\2_4.png",
//                100, 200, 100, 200, "jpg", "png");
        combinImagesHorizontally("E:\\\\programes\\\\storage\\\\src\\\\main\\\\resources\\\\img\\\\1.jpg",
                "E:\\\\programes\\\\storage\\\\src\\\\main\\\\resources\\\\img\\\\1.jpg",
                "jpg", "E:\\\\programes\\\\storage\\\\src\\\\main\\\\resources\\\\img\\\\2_5.jpg");
//        combinImagesVertically("E:\\\\programes\\\\storage\\\\src\\\\main\\\\resources\\\\img\\\\1.jpg",
//                 "E:\\\\programes\\\\storage\\\\src\\\\main\\\\resources\\\\img\\\\1.jpg",
//                 "jpg", "E:\\\\programes\\\\storage\\\\src\\\\main\\\\resources\\\\img\\\\2_6.jpg");
}

    // 读写图像文件
    public static void readAndWrite() {
        BufferedImage image = null ;
        try {
            // 将文件读取到内存中
            image  = ImageIO.read(new File("E:\\programes\\storage\\src\\main\\resources\\img\\1.jpg"));
            System.out.println("Height: " + image.getHeight()); // 高度像素
            System.out.println("Width: " + image.getWidth()); // 宽度像素
            // 写文件，将图片指定为 png 格式，图片的编码格式是自动转变的
            ImageIO.write(image, "png", new File("E:\\programes\\storage\\src\\main\\resources\\img\\2_2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 图片的读取
     * 不指定读取的类 ImageIO 会自动进行测试，并选择相对应的类型的 Reader 进行读取，耗费时间较长
     * 指定 ImageIO 的 ImageReader 读取速度会提升
     */
    public static void readComparison() {
        System.out.println("======加载速度测试=======");
        // ImageIO 需要测试图片的类型，加载合适的 ImageReader 来读取图片，耗时更长
        long startTime = System.nanoTime();
        try {
            BufferedImage image  = ImageIO.read(new File("E:\\programes\\storage\\src\\main\\resources\\img\\1.jpg"));
            System.out.println("Height: " + image.getHeight());
            System.out.println("Width: " + image.getWidth());
            long endTime = System.nanoTime();
            System.out.println((endTime - startTime) / 1000000.0 + "秒");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 指定用 jpg Reader 来加载，速度会更快
        startTime = System.nanoTime();
        // 返回的是一个迭代器，而不是返回的一个明确的 Reader 类
        // 使用 jpg、png、bmp 测试返回的 Iterator 都只有一个对象
        Iterator<ImageReader> readers = ImageIO.getImageReadersByFormatName("jpg");

        ImageReader reader = readers.next();
        System.out.println(reader.getClass().getName());
        try {
            ImageInputStream iis = ImageIO.createImageInputStream(new File("E:\\programes\\storage\\src\\main\\resources\\img\\1.jpg"));
            // 第一个参数设置的是读取的数据源，第二个设置是否根据输入源的数据输入顺序读取数据
            // 如果输入源文件包含多张照片，而程序不保证顺序读取时，第二个参数应该设置为 false
            // 对于那些只允许存储一张照片的格式永远传递 true 是合理的
            reader.setInput(iis, true);
            System.out.println("Height: " + reader.getHeight(0));
            System.out.println("Width: " + reader.getWidth(0));
            long endTime = System.nanoTime();
            System.out.println((endTime - startTime) / 1000000.0 + "秒");
        } catch (IOException e) {
            e.printStackTrace();
        }

        /**
         * Height: 313
         * Width: 500
         * 135.041秒
         * com.sun.imageio.plugins.jpeg.JPEGImageReader
         * Height: 313
         * Width: 500
         * 0.7775秒
         */
    }

    /**
     * cropImage 将原始图片文件切割一个矩形，并输出到目标图片文件
     * @param fromPath 原始图片的路径
     * @param toPath 目标图片
     * @param x 坐标起点 x
     * @param y 坐标起点 y
     * @param width 矩形的宽度
     * @param height 矩形的高度
     * @param readImageFormat 原始文件格式
     * @param writeImageFormat 目标文件格式
     */
    public static void cropImage(String fromPath, String toPath, int x, int y, int width, int height,
                                 String readImageFormat, String writeImageFormat)  {
        FileInputStream fis = null;
        ImageInputStream iis = null;
        try {
            // 读取原始图片文件
            fis = new FileInputStream(fromPath);
            Iterator iterator = ImageIO.getImageReadersByFormatName("jpg");
            ImageReader reader = (ImageReader)iterator.next();
            iis = ImageIO.createImageInputStream(fis);
            reader.setInput(iis, true);

            // 定义一个矩形，并放入切割参数中
            ImageReadParam param = reader.getDefaultReadParam();
            Rectangle rect = new Rectangle(x, y, width, height);
            param.setSourceRegion(rect);

            // 从源文件读取一个矩形大小的图像
            BufferedImage bi = reader.read(0, param);

            // 写入到目标文件
            ImageIO.write(bi, writeImageFormat, new File(toPath));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                iis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 水平拼接图片
     * @param firstPath
     * @param secondPath
     * @param imageFormat
     * @param toPath
     */
    public static void combinImagesHorizontally(String firstPath, String secondPath,
                                                String imageFormat, String toPath) {
        try {
            // 读取第一张图片
            File first = new File(firstPath);
            BufferedImage imageOne = ImageIO.read(first);
            int width1 = imageOne.getWidth();
            int height1= imageOne.getHeight();
            // 从第一张图片中读取 RGB
            int[] firstRGB = new int[width1 * height1];
            // 对原来的图片进行扫描，扫描从 (0, 0) 开始，扫描的宽度是 width1 高度是 height1
            firstRGB = imageOne.getRGB(0, 0,width1, height1, firstRGB, 0, width1);

            // 对第二张图片做同样的处理
            File second = new File(secondPath);
            BufferedImage imageTwo = ImageIO.read(second);
            int width2 = imageTwo.getWidth();
            int height2 = imageTwo.getHeight();
            // 从第一张图片中读取 RGB
            int[] secondRGB = new int[width2 * height2];
            // 对原来的图片进行扫描，扫描从 (0, 0) 开始，扫描的宽度是 width1 高度是 height1
            secondRGB = imageTwo.getRGB(0, 0,width2, height2, secondRGB, 0, width2);

//            System.out.println(Arrays.toString(firstRGB));

            // 生成新图片
            int height3 = (height1 > height2) ? height1 : height2;
            int width3 = width1 + width2;
            BufferedImage imageNew = new BufferedImage(width3, height3, BufferedImage.TYPE_INT_RGB);

            // 设置左半部分的 RGB 从（0，0）开始
            // 在新的图片的左半部分写入 firstRGB 也就是第一张图片，实际上写入的是存储着第一张图片信息的数组
            imageNew.setRGB(0, 0, width1, height1, firstRGB, 0, width1);
            // 设置右半部分的 RGB 从（height1，0） 开始
            imageNew.setRGB(width1, 0, width2, height2, secondRGB, 0, width2);

            // 保存图片
            ImageIO.write(imageNew, imageFormat, new File(toPath));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *垂直拼接图片
     * @param firstPath
     * @param secondPath
     * @param imageFormat
     * @param toPath
     */
    public static void combinImagesVertically(String firstPath, String secondPath,
                                                String imageFormat, String toPath) {
        try {
            // 读取第一张图片
            File first = new File(firstPath);
            BufferedImage imageOne = ImageIO.read(first);
            int width1 = imageOne.getWidth();
            int height1= imageOne.getHeight();
            // 从第一张图片中读取 RGB
            int[] firstRGB = new int[width1 * height1];
            // 对原来的图片进行扫描，扫描从 (0, 0) 开始，扫描的宽度是 width1 高度是 height1
            firstRGB = imageOne.getRGB(0, 0,width1, height1, firstRGB, 0, width1);

            // 对第二张图片做同样的处理
            File second = new File(secondPath);
            BufferedImage imageTwo = ImageIO.read(second);
            int width2 = imageTwo.getWidth();
            int height2 = imageTwo.getHeight();
            // 从第一张图片中读取 RGB
            int[] secondRGB = new int[width2 * height2];
            // 对原来的图片进行扫描，扫描从 (0, 0) 开始，扫描的宽度是 width1 高度是 height1
            secondRGB = imageTwo.getRGB(0, 0,width2, height2, secondRGB, 0, width2);

//            System.out.println(Arrays.toString(firstRGB));

            // 生成新图片
            int height3 = height1 + height2;
            int width3 = (width1 > width2) ? width1 : width2;
            BufferedImage imageNew = new BufferedImage(width3, height3, BufferedImage.TYPE_INT_RGB);

            // 设置左半部分的 RGB 从（0，0）开始
            imageNew.setRGB(0, 0, width1, height1, firstRGB, 0, width1);
            // 设置右半部分的 RGB 从（height1，0） 开始
            imageNew.setRGB(0, height1, width2, height2, secondRGB, 0, width2);

            // 保存图片
            ImageIO.write(imageNew, imageFormat, new File(toPath));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
