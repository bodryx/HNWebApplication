package net.ddns.hnmirror.dao;

import java.sql.SQLException;
import java.util.List;

import javax.jms.JMSException;

import net.ddns.hnmirror.domain.Story;

public interface StoryDAO {

	public List<Story> listStory(int page);
	
	public List<Story> search(String searchStr, String searchBy, int page);
	
}
