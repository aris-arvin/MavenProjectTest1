import org.openqa.selenium.WebDriver;

import javax.mail.*;
import java.util.Properties;

public class ReadEmail {
    private WebDriver driver;

    public ReadEmail(WebDriver driver) {
        this.driver = driver;
    }

    //public int getCount() throws Exception {
    public static void main(String[] args) throws Exception {


        final String user = "testlasttest@mail.ru"; // имя первого пользователя
        final String user2 = "lasttestlast@mail.ru"; // имя второго пользователя
        final String pass = "fhfh3864";    // пароль первого пользователя
        final String pass2 = "fhfh38643864";    // пароль второго пользователя
        final String host = "imap.mail.ru";     // адрес почтового сервера

        // Создание свойств
        Properties props = new Properties();

        //включение debug-режима(true,false)
        props.put("mail.debug", "true");

        //Указываем протокол - IMAP с SSL
        props.put("mail.store.protocol", "imaps");
        Session session = Session.getInstance(props);
        Store store = session.getStore();

        //подключаемся к почтовому серверу
        store.connect(host, user, pass);

        //получаем папку с входящими сообщениями
        Folder inbox = store.getFolder("INBOX");
        //Folder sent = store.getFolder("SENT");


        //открываем её только для чтения
        //inbox.open(Folder.READ_ONLY);
        inbox.open(Folder.READ_ONLY);

        //получаем последнее сообщение (самое старое будет под номером 1)
        // Message m = inbox.getMessage(inbox.getMessageCount());
        Message m = inbox.getMessage(inbox.getMessageCount());
        //Message i = in.getMessage(in.getMessageCount());
        Multipart mp = (Multipart) m.getContent();
        BodyPart bp = mp.getBodyPart(0);

        //Выводим содержимое на экран
        // System.out.println(bp.getContent());
        System.out.println(m.getMessageNumber());
        System.out.println(inbox = store.getDefaultFolder());

        //  return i.getMessageNumber();
    }
}


