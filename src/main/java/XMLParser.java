import org.mindrot.jbcrypt.BCrypt;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class XMLParser extends DefaultHandler {
    private static final int BCRYPT_LOG_ROUNDS = 12;

    private boolean processingId = false;
    private boolean processingPassword = false;
    private String id;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if ("id".equalsIgnoreCase(qName)) {
            processingId = true;
        } else if ("password_info".equalsIgnoreCase(qName)) {
            processingPassword = true;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        if (processingId) {
            id = new String(ch, start, length);
            processingId = false;
        } else if (processingPassword) {
            String password = new String(ch, start, length);
            System.out.println(String.format("# Id: %s\tPassword: %s", id, password));

            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(BCRYPT_LOG_ROUNDS));
            String sql = String.format("update user__info set password_info = '%s' where id = %s;", hashedPassword, id);
            System.out.println(sql);

            processingPassword = false;
        }
    }
}
