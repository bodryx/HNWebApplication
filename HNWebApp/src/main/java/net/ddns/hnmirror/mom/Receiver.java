package net.ddns.hnmirror.mom;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.jms.JmsException;

import net.ddns.hnmirror.domain.Story;
import net.ddns.hnmirror.domain.Token;

public class Receiver {
	private ConnectionFactory factory = null;
	private Connection connection = null;
	private Session session = null;
	private Destination destination = null;
	private MessageConsumer consumer = null;
	private final String queueName;
	private String brokerUrl;
	private long timeReceive;
	private Handler handler = new Handler();

	public Receiver(String brokerUrl, String queueName, long timeReceive) {
		this.queueName = queueName;
		this.brokerUrl = brokerUrl;
		this.timeReceive = timeReceive;
	}

	public List<Story> receive(int tokenId) {
		List<Story> stories = new ArrayList<Story>();
		try {
			factory = new ActiveMQConnectionFactory(brokerUrl); // ActiveMQConnection.DEFAULT_BROKER_URL);
			connection = factory.createConnection();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			destination = session.createQueue(queueName);
			consumer = session.createConsumer(destination);
			connection.start();

			while (true) {
				Message msg = consumer.receive();
				if (msg instanceof ObjectMessage) {
					ObjectMessage msg0 = (ObjectMessage) msg;
					Token token = (Token) msg0.getObject();

					stories = handler.handleToken(tokenId, token);
					
					consumer.close();
					session.close();
					connection.close();
					return stories;
				}

			}

			// Thread.sleep(timeReceive);

			// consumer.close();
			// session.close();
			// connection.close();
		}  catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return stories;

	}
}