package cn.bookStore.utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

/**
 * 发送邮件的工具类
 */
public class MailUtils {

	public static void sendMail(String email, String emailMsg, HttpServletRequest request)
			throws AddressException, MessagingException {
		// 1.创建一个程序与邮件服务器会话对象 Session

		Properties props = new Properties();
		props.setProperty("mail.transport.protocol", "SMTP");
		props.setProperty("mail.host", "smtp.qq.com");
		props.setProperty("mail.smtp.auth", "true");// 指定验证为true

		// 创建验证器
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("964498287@qq.com", "dtflifduzaklbchg");
			}
		};

		Session session = Session.getInstance(props, auth);

		// 2.创建一个Message，它相当于是邮件内容
		Message message = new MimeMessage(session);

		message.setFrom(new InternetAddress("964498287@qq.com")); // 设置发送者

		message.setRecipient(RecipientType.TO, new InternetAddress(email)); // 设置发送方式与接收者
		message.setSubject("用户激活");
		// message.setContent("这是一封有附件的邮件邮件：<a href=\"www.baidu.com\">激活</a>", "text/html; charset=utf-8");
		//message.setText("这是一封激活邮件，请<a href='#'>点击</a>");
		message.setContent("<h1>此邮件为官方激活邮件！请点击下面链接完成激活操作！</h1><h3><a href='http://localhost:8888/bookStore_war/user/active.do?activeCode="+request.getAttribute("activeCode")+"'>点击我</a></h3>","text/html;charset=UTF-8");
		// message.setContent("<h1>此邮件为官方激活邮件！请点击下面链接完成激活操作！</h1><h3><a href='http://sylbookstore.51vip.biz:33259/bookStore_war/user/active.do?activeCode="+request.getAttribute("activeCode")+"'>点击我</a></h3>","text/html;charset=UTF-8");

		//message.setContent(emailMsg, "text/html;charset=utf-8");

		// 3.创建 Transport用于将邮件发送

		Transport.send(message);
	}
}
