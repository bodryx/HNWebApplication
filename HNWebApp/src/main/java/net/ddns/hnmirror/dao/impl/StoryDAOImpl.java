package net.ddns.hnmirror.dao.impl;

import java.util.List;
import java.util.Random;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.ddns.hnmirror.config.Config;
import net.ddns.hnmirror.dao.StoryDAO;
import net.ddns.hnmirror.domain.Story;
import net.ddns.hnmirror.domain.Token;
import net.ddns.hnmirror.mom.Receiver;
import net.ddns.hnmirror.mom.Sender;
import net.ddns.hnmirror.web.StoryController;


public class StoryDAOImpl implements StoryDAO {
	private String ipAppSrv;
	private int portAppSrv;
	
	public StoryDAOImpl() {
		ipAppSrv = StoryController.ipAppSrv;
		portAppSrv = StoryController.portAppSrv;
	} 
	
	public int generateTokenId() {
		Random rn = new Random();
		int tokenId = rn.nextInt(1000) + 1;
		return tokenId;
	}
	
	public List<Story> listStory(int page) {

		Sender sender = new Sender("webserver-queue");
		int tokenId = generateTokenId();
		Token token = new Token(tokenId, "page");
		token.setPage(page);
		sender.sendMessage(token);

		Receiver receiver = new Receiver("tcp://"+ ipAppSrv + ":" + portAppSrv, "appserver-queue", 24 * 3600000);
		return receiver.receive(tokenId);
	}

	
	public List<Story> search(String searchStr, String searchBy, int page) {
		Sender sender = new Sender("webserver-queue");
		int tokenId = generateTokenId();
		Token token = new Token(tokenId, "search");
		token.setSearchString(searchStr);
		token.setSearchBy(searchBy);
		token.setPage(page);
		sender.sendMessage(token);

		Receiver receiver = new Receiver("tcp://"+ ipAppSrv + ":" + portAppSrv, "appserver-queue", 24 * 3600000);	
		return receiver.receive(tokenId);
	}

}
