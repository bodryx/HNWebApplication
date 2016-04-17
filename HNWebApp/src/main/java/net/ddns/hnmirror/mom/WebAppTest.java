package net.ddns.hnmirror.mom;

import java.sql.SQLException;
import java.util.List;
import javax.jms.JMSException;


import net.ddns.hnmirror.domain.Story;

import net.ddns.hnmirror.service.impl.StoryServiceImpl;

public class WebAppTest {

	
	public static void main(String[] args) throws InterruptedException, SQLException, JMSException {

	
		/*Sender sender = new Sender("webserver-queue");
		int tokenId = 45;
		Token token = new Token(tokenId, "all");
		sender.sendMessage(token);

		Receiver receiver = new Receiver("tcp://192.168.1.7:61616", "appserver-queue", 24 * 3600000);
		List<Story> stories = receiver.receive(tokenId);*/
		
		StoryServiceImpl storyServiceImpl = new StoryServiceImpl();
		
		List<Story> stories = storyServiceImpl.listStory(1);
	 
		
		
		
	
		/*for (int i = 0; i < stories.size(); i++) {
			System.out.println("Title = " + stories.get(i).getTitle());
		}*/
		
	}

	
	 
}

