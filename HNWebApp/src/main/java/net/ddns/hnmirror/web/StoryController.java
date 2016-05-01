package net.ddns.hnmirror.web;

import java.io.IOException;
import java.sql.SQLException;

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
	private int page = 1;
	
	public static String ipAppSrv;
	public static int portAppSrv;
	
	public StoryController() {
		 ApplicationContext context =new ClassPathXmlApplicationContext("Beans.xml");
		 Config config = (Config) context.getBean("configuration");
		 ipAppSrv = config.getIpAppSrv();
		 portAppSrv = config.getPortAppSrv();
	}
	
	@RequestMapping("/index")
	public String listStories(Model model) throws SQLException {
		page = 1;
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

	@RequestMapping("/more")
	public String askPage(Model model) throws SQLException {

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
	public ModelAndView search(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		page = 1;
		String searchStr = request.getParameter("search");
		
		String searchBy  = request.getParameter("searchbyauthor");
		searchBy = searchBy + request.getParameter("searchbytitle");
		searchBy = searchBy + request.getParameter("searchbyurl");
		searchBy = searchBy + request.getParameter("searchbytext");
		
		String queryString = request.getQueryString();	
		
		StoryServiceImpl storyServiceImpl = new StoryServiceImpl();
		ModelAndView modelAndView = new ModelAndView("story");

		modelAndView.addObject("search_res", "value='" + searchStr + "'");
		if(searchBy.contains("author")) {
			modelAndView.addObject("checkboxauthor",  "checked='checked'");
		}
		if(searchBy.contains("title")) {
			modelAndView.addObject("checkboxtitle",  "checked='checked'");
		}
		if(searchBy.contains("url")) {
			modelAndView.addObject("checkboxurl",  "checked='checked'");
		}
		if(searchBy.contains("text")) {
			modelAndView.addObject("checkboxtext",  "checked='checked'");
		}
		
		modelAndView.addObject("stories", storyServiceImpl.search(searchStr, searchBy, page));
		++page;
		modelAndView.addObject("page", "moresearch?" + queryString);
		
		return modelAndView;
	}

	@RequestMapping("/moresearch")
	public ModelAndView moreSearch(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String searchStr = request.getParameter("search");
		
		String searchBy  = request.getParameter("searchbyauthor");
		searchBy = searchBy + request.getParameter("searchbytitle");
		searchBy = searchBy + request.getParameter("searchbyurl");
		searchBy = searchBy + request.getParameter("searchbytext");
				
		String queryString = request.getQueryString();	
	
		StoryServiceImpl storyServiceImpl = new StoryServiceImpl();
		ModelAndView modelAndView = new ModelAndView("story");

		modelAndView.addObject("search_res", "value='" + searchStr + "'");
		if(searchBy.contains("author")) {
			modelAndView.addObject("checkboxauthor",  "checked='checked'");
		}
		if(searchBy.contains("title")) {
			modelAndView.addObject("checkboxtitle",  "checked='checked'");
		}
		if(searchBy.contains("url")) {
			modelAndView.addObject("checkboxurl",  "checked='checked'");
		}
		if(searchBy.contains("text")) {
			modelAndView.addObject("checkboxtext",  "checked='checked'");
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
