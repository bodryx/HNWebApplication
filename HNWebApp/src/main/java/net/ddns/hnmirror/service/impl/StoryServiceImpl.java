package net.ddns.hnmirror.service.impl;


import java.util.List;

import net.ddns.hnmirror.dao.impl.StoryDAOImpl;
import net.ddns.hnmirror.domain.Story;
import net.ddns.hnmirror.service.StoryService;

public class StoryServiceImpl implements StoryService{

	public List<Story> listStory(int page) {
		StoryDAOImpl storyDAOImpl = new StoryDAOImpl();

		return storyDAOImpl.listStory(page);
	}

	public List<Story> search(String searchStr, String searchBy, int page) {
		StoryDAOImpl storyDAOImpl = new StoryDAOImpl();
		return storyDAOImpl.search(searchStr, searchBy, page);
	}
	
	

}
