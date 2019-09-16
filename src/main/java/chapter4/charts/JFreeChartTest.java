package chapter4.charts;

import org.apache.commons.lang3.CharUtils;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class JFreeChartTest {
    public static void main(String[] args) {
        writeBar("E:\\programes\\storage\\src\\main\\resources\\img\\bar.jpg");
        writePie("E:\\programes\\storage\\src\\main\\resources\\img\\pie.jpg");
        writeLine("E:\\programes\\storage\\src\\main\\resources\\img\\lines.jpg");
    }

    /**
     * 画柱状图
     * @param fileName
     */
    public static void writeBar(String fileName) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(11, "", "第一季度");
        dataset.addValue(41, "", "第二季度");
        dataset.addValue(51, "", "第三季度");
        dataset.addValue(4, "", "第四季度");

        // PlotOrientation.HORIZONTAL 横向 PlotOrientation,VERTICAL 横向
        // 引入文中主题样式
        ChartFactory.setChartTheme(getChineseTheme());
        JFreeChart chart = ChartFactory.createBarChart3D("柱状图", "2018年","产品总量", dataset);

        try {
            ChartUtilities.saveChartAsJPEG(new File(fileName), chart, 600, 300);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 画饼状图
     * @param fileName
     */
    public static void writePie(String fileName) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("C 人数", 100);
        dataset.setValue("C++ 人数", 200);
        dataset.setValue("Java 人数", 300);

        // PlotOrientation.HORIZONTAL 横向 PlotOrientation,VERTICAL 横向
        // 引入文中主题样式
        ChartFactory.setChartTheme(getChineseTheme());
        JFreeChart chart = ChartFactory.createPieChart3D("饼状图", dataset);

        try {
            ChartUtilities.saveChartAsJPEG(new File(fileName), chart, 600, 300);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 画折线图
     * @param fileName
     */
    public static void writeLine(String fileName) {
        DefaultCategoryDataset lines = new DefaultCategoryDataset();
        // 第一条线
        lines.addValue(100, "Java 核心技术", "1月");
        lines.addValue(200, "Java 核心技术", "2月");
        lines.addValue(400, "Java 核心技术", "3月");
        lines.addValue(500, "Java 核心技术", "4月");

        //第二条线
        lines.addValue(200, "Java 核心技术（进阶）", "1月");
        lines.addValue(400, "Java 核心技术（进阶）", "2月");
        lines.addValue(500, "Java 核心技术（进阶）", "3月");

        try {
            ChartFactory.setChartTheme(getChineseTheme());
            JFreeChart chart = ChartFactory.createLineChart("折线图", "时间", "人数", lines);
            ChartUtilities.saveChartAsJPEG(new File(fileName), chart, 600, 300);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static StandardChartTheme getChineseTheme() {
        StandardChartTheme chineseTheme = new StandardChartTheme("CN");
        chineseTheme.setExtraLargeFont(new Font("隶书", Font.BOLD, 20));
        chineseTheme.setRegularFont(new Font("宋书", Font.PLAIN, 15));
        chineseTheme.setLargeFont(new Font("宋书", Font.PLAIN, 15));

        return chineseTheme;
    }
}
