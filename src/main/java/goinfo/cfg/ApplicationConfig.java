package goinfo.cfg;

import org.springframework.boot.autoconfigure.jdbc.TomcatDataSourceConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.sql.DataSource;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

@Configuration
public class ApplicationConfig extends TomcatDataSourceConfiguration {

    MailSender mailSender;
    SimpleMailMessage simpleMailMessage;

    @Bean
    public DataSource dateSource() {
        return super.dataSource();
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource){
        return new NamedParameterJdbcTemplate(dataSource);
    }


    public MailSender mailSender(){
        JavaMailSenderImpl javaMailSenderImpl = new JavaMailSenderImpl();
        javaMailSenderImpl.setHost("smtp.gmail.com");
        javaMailSenderImpl.setPort(587);

        javaMailSenderImpl.setUsername("GoinfoBridgeService");
        javaMailSenderImpl.setPassword("@GoinfoBridgeService");


        Properties mailProp = new Properties();
        mailProp.put("mail.smtp.auth", true);
        mailProp.put("mail.smtp.starttls.enable", true);


        javaMailSenderImpl.setJavaMailProperties(mailProp);


        return (MailSender) javaMailSenderImpl;
    }


    public SimpleMailMessage simpleMailMessage() {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("smlsun@gmail.com");

        String[] ccMailList = {
            "debora0630@gmail.com",
            "jw11150122@gmail.com",
            "lyhcode@gmail.com",
            "debora0630@gmail.com"
        };
        msg.setCc(ccMailList);
        msg.setFrom("goinfobridgeservice@gmail.com");

        SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String mailContent="日期時間："+ sdf.format(date);
        try {
            InetAddress ip;
            ip = InetAddress.getLocalHost();
            mailContent += "\nIP address : " + ip.getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        mailContent += "\njava 版本："+ System.getProperty("java.runtime.version");
        mailContent += "\njava 編譯："+ System.getProperty("sun.management.compiler");

        mailContent += "\n作業系統名稱："+ System.getProperty("os.name");
        mailContent += "\n作業系統版本："+ System.getProperty("os.version");
        mailContent += "\n作業系統架構："+ System.getProperty("os.arch");

        msg.setText(mailContent);
        return msg;
    }

    @PostConstruct
    public void startupMailNotify(){
        System.out.println("====== startup server finish======");
        this.simpleMailMessage = simpleMailMessage();
        this.mailSender = mailSender();

        this.simpleMailMessage.setSubject("goinfo 客服整合系統啟動");

        try{
            this.mailSender.send(this.simpleMailMessage);
        }
        catch(MailException ex) {
            System.err.println(ex.getMessage());
        }
    }
    @PreDestroy
    public void shutdownMailNotify(){
        System.out.println("====== shutdown server finish======");
        this.simpleMailMessage.setSubject("goinfo 客服整合系統關閉");
        try{
            this.mailSender.send(this.simpleMailMessage);
        }
        catch(MailException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
