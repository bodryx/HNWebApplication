package net.ddns.hnmirror.web;

import java.io.IOException;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import net.ddns.hnmirror.config.Config;
import net.ddns.hnmirror.service.impl.StoryServiceImpl;

@Controller
public class StoryController {
	private int page;

	public static String ipAppSrv;
	public static int portAppSrv;

	public StoryController() {
		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		Config config = (Config) context.getBean("configuration");
		ipAppSrv = config.getIpAppSrv();
		portAppSrv = config.getPortAppSrv();
		page = 1;
	}

	@RequestMapping("/index")
	public String firstPage(Model model) {
		page = 1;
		return pageOfStories(model);
	}

	@RequestMapping("/more")
	public String nextPage(Model model) {
		return pageOfStories(model);
	}

	public String pageOfStories(Model model) {
		StoryServiceImpl storyServiceImpl = new StoryServiceImpl();
		model.addAttribute("stories", storyServiceImpl.listStory(page));
		++page;
		model.addAttribute("page", "more?p=" + page);
		model.addAttribute("checkboxauthor", "checked='checked'");
		model.addAttribute("checkboxtitle", "checked='checked'");
		model.addAttribute("checkboxurl", "checked='checked'");
		model.addAttribute("checkboxtext", "checked='checked'");

		return "story";

	}

	@RequestMapping("/search")
	public ModelAndView firstPageOfSearch(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		page = 1;
		return pageOfSearch(request, response);

	}

	@RequestMapping("/moresearch")
	public ModelAndView nextPageOfSearch(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		return pageOfSearch(request, response);
	}

	public ModelAndView pageOfSearch(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String searchStr = request.getParameter("search");

		String searchBy = request.getParameter("searchbyauthor");
		searchBy = searchBy + request.getParameter("searchbytitle");
		searchBy = searchBy + request.getParameter("searchbyurl");
		searchBy = searchBy + request.getParameter("searchbytext");

		String queryString = request.getQueryString();

		StoryServiceImpl storyServiceImpl = new StoryServiceImpl();
		ModelAndView modelAndView = new ModelAndView("story");

		modelAndView.addObject("search_res", "value='" + searchStr + "'");
		if (searchBy.contains("author")) {
			modelAndView.addObject("checkboxauthor", "checked='checked'");
		}
		if (searchBy.contains("title")) {
			modelAndView.addObject("checkboxtitle", "checked='checked'");
		}
		if (searchBy.contains("url")) {
			modelAndView.addObject("checkboxurl", "checked='checked'");
		}
		if (searchBy.contains("text")) {
			modelAndView.addObject("checkboxtext", "checked='checked'");
		}

		modelAndView.addObject("stories", storyServiceImpl.search(searchStr, searchBy, page));
		++page;
		modelAndView.addObject("page", "moresearch?" + queryString);

		return modelAndView;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		return "redirect:/index";
	}
}
