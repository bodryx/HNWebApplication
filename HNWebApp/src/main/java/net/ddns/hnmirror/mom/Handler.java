package net.ddns.hnmirror.mom;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.ddns.hnmirror.domain.Story;
import net.ddns.hnmirror.domain.Token;

public class Handler {

	public List<Story> handleToken(int tokenId, Token token) throws SQLException {
		List<Story> stories = new ArrayList<Story>();
		if (token.getResponse().equals("done")) {
			if (tokenId == token.getTokenId()) {
				stories = token.getStoriesArray();

			}
		}
		return stories;
	}
}
