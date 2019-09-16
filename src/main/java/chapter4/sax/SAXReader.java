package chapter4.sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auth: chunlei.wang
 * @Date: 2019/09/09
 * @Desc:  通过 SAX 方法读取 XML 文件
 * SAX 的基本类就是 DefaultHandler 里面定义了对不同事件点的处理方法
 * startDocument
 *      startElement
 *          characters
 *      endElement
 * endDocument
 * 通过这五个事件，以流的方式对 xml 文件进行访问，对每一个标签进行处理
 */
public class SAXReader {
    public static void main(String[] args) throws SAXException, IOException {
        XMLReader parser = XMLReaderFactory.createXMLReader();
        BookHandler bookHandler = new BookHandler();
        parser.setContentHandler(bookHandler);
        parser.parse("book.xml");
        System.out.println(bookHandler.getNameList());
        /**
         * Start parsing document....
         * Book title:Everyday Italian
         * Book title:Harry potter
         * Book title:Learning URL
         * End
         * [Everyday Italian, Harry potter, Learning URL]
         */
    }
}
class BookHandler extends DefaultHandler {
    private List<String> nameList;
    private boolean title = false;

    public List<String> getNameList() {
        return this.nameList;
    }

    // xml 文档开始加载
    @Override
    public void startDocument() throws SAXException{
        System.out.println("Start parsing document....");
        nameList = new ArrayList<>();
    }

    // 文档解析结束
    @Override
    public void endDocument() throws SAXException {
        System.out.println("End");
    }

    // 访问某一个元素
    @Override
    public void startElement (String uri, String localName, String qName, Attributes attributes)
            throws SAXException{
        if ("title".equals(qName)) {
            this.title = true;
        }
    }

    // 结束访问元素
    @Override
    public void endElement(String namespaceURI, String localName, String qName)
            throws SAXException{
        if (title) {
            this.title = false;
        }
    }

    // 访问元素正文
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException{
        if (title) {
            String bookTitle = new String(ch, start, length);
            System.out.println("Book title:" + bookTitle);
            nameList.add(bookTitle);
        }
    }
}
