package net.ddns.hnmirror.dao;

import java.util.List;

import net.ddns.hnmirror.domain.Story;

public interface StoryDAO {

	public List<Story> listStory(int page);
	
	public List<Story> search(String searchStr, String searchBy, int page);
	
}
