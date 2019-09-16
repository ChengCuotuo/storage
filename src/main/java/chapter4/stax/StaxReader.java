package chapter4.stax;


import javax.xml.stream.*;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;

/**
 * @Auth: chunlei.wang
 * @Date: 2019/09/09
 * @Desc:  通过 Stax 对 xml 文档进行读取
 */
public class StaxReader {
    public static void main(String[] args) {
        readByStream();
        System.out.println("======= 分割线 ========");
        readBuEvent();
    }

    /**
     * 基于指针的 API ， XMLStreamReader
     * 流模型
     */
    public static void readByStream()  {
        String xmlFile = "book.xml";
        XMLInputFactory factory = XMLInputFactory.newFactory();
        XMLStreamReader streamReader = null;
        try {
            streamReader = factory.createXMLStreamReader(new FileReader(xmlFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }

        // 基于指针遍历
        try {
            while (streamReader.hasNext()) {
                int event = streamReader.next();
                // 如果是元素的开始
                if (event == XMLStreamConstants.START_ELEMENT){
                    // 列出所有的书籍名称
                    if ("title".equalsIgnoreCase(streamReader.getLocalName())) {
                        System.out.println("title: " + streamReader.getElementText());
                    }
                }
            }
        } catch (XMLStreamException e) {
            e.printStackTrace();
        } finally {
            try {
                streamReader.close();
            } catch (XMLStreamException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 基于迭代器的 API , XMLEventReader
     */
    public static void readBuEvent() {
        String xmlFile = "book.xml";
        XMLInputFactory factory = XMLInputFactory.newInstance();
        boolean titleFlag  = false;
        try {
            // 创建基于迭代器的事件读取器对象
            XMLEventReader eventReader = factory.createXMLEventReader(new FileReader(xmlFile));
            // 遍历 Event 迭代器
            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();
                // 如果事件对象是元素的开始
                if (event.isStartElement()) {
                    // 转换成开始元素事件对象
                    StartElement start = event.asStartElement();
                    // 打印元素标签的本地名称
                    String name = start.getName().getLocalPart();
                    if ("title".equalsIgnoreCase(name)) {
                        titleFlag = true;
                        System.out.println("title: ");
                    }

                    // 读取所有属性
                    Iterator attrs = start.getAttributes();
                    while (attrs.hasNext()) {
                        // 打印所有属性信息
                        Attribute attr = (Attribute) attrs.next();
                        System.out.println(": " + attr.getName().getLocalPart() +
                                "=" + attr.getValue());
                    }

                    // 如果是正文
                    if (event.isCharacters()) {
                        String s = event.asCharacters().getData();
                        if (null != s && s.trim().length() > 0 && titleFlag) {
                            System.out.println(s.trim());
                        }
                    }

                    // 如果事件对象是元素的结束
                    if (event.isEndElement()) {
                        EndElement end = event.asEndElement();
                        String endName = end.getName().getLocalPart();
                        if ("title".equalsIgnoreCase(endName)) {
                            titleFlag = false;
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }
}
