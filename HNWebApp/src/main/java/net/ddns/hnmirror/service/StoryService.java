package net.ddns.hnmirror.service;

import java.util.List;

import net.ddns.hnmirror.domain.Story;

public interface StoryService {
	public List<Story> listStory(int page);
	public List<Story> search(String searchStr, String searchBy, int page);
}
