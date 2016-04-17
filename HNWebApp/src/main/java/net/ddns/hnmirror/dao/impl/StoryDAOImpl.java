package net.ddns.hnmirror.dao.impl;

import java.util.List;
import java.util.Random;

import net.ddns.hnmirror.dao.StoryDAO;
import net.ddns.hnmirror.domain.Story;
import net.ddns.hnmirror.domain.Token;
import net.ddns.hnmirror.mom.Receiver;
import net.ddns.hnmirror.mom.Sender;


public class StoryDAOImpl implements StoryDAO {

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

		Receiver receiver = new Receiver("tcp://192.168.1.200:61616", "appserver-queue", 24 * 3600000);
		//Receiver receiver = new Receiver("tcp://192.168.1.215:61616", "appserver-queue", 24 * 3600000);
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

		Receiver receiver = new Receiver("tcp://192.168.1.200:61616", "appserver-queue", 24 * 3600000);
		//Receiver receiver = new Receiver("tcp://192.168.1.215:61616", "appserver-queue", 24 * 3600000);
		
		return receiver.receive(tokenId);
	}

}
