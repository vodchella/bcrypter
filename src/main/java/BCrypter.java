import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;

public class BCrypter {
    public static void main(String[] args) {
        try {
            File inputFile = new File("/home/khangroup/Загрузки/users_not_salted_pswd.xml");
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            XMLParser userhandler = new XMLParser();
            saxParser.parse(inputFile, userhandler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
