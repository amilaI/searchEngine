package com.searchengine.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.lucene.queryParser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.searchengine.indexing.IndexItem;
import com.searchengine.indexing.Searcher;

/**
 * Engineering Dashboard (EDB) - Always Learning Module
 * Content Search Engine 
 * 
 * ContentSearchController will receive the incoming search query  request ,
 * and returns a view with search results.
 *  
 * @author Amila Iddamalgoda - amila.iddamalgoda@pearson.com
 * @version 1.0
 * @since 10/05/2014
 */

@Controller
@RequestMapping("/User")
public class ContentSearchController {

	//Physical location where the index will be stored.
    private static final String INDEX_DIR = "/home/amilai/Lucene/REST/indexing";
    //Maximum Result size
    private static final int MAX_RESULT_SIZE = 100;
    
	@RequestMapping(value="/retrieve", method = RequestMethod.GET)
	public ModelAndView getSearchResultSet(HttpServletRequest request , HttpServletResponse response) {		
		
		String query = request.getParameter("search-text");
		System.out.println("Search Query Returned  - " + query);
		
		int numberOfResults=0;
		long time =  0;
		
		List<IndexItem> resultsBycontent  = new ArrayList<IndexItem>();
		
		
		try {
			Date start = new Date();
			resultsBycontent =getResults(query);
			Date end = new Date();
		    System.out.println(end.getTime() - start.getTime() + " total milliseconds");
		    time=end.getTime() - start.getTime();
		} catch (ParseException e) {			
			e.printStackTrace();
		} catch (IOException e) {			
			e.printStackTrace();
		}
		
		// The returning result set will be appended to this ModelAndView
		ModelAndView modelAndView = new ModelAndView("results");

		if (!resultsBycontent.isEmpty()) {

			numberOfResults = resultsBycontent.size();
			modelAndView.addObject("results", resultsBycontent);

		} else {

			modelAndView.addObject("error", "No result found");
		}

		modelAndView.addObject("time", time);
		modelAndView.addObject("numberOfResults", numberOfResults);
		return modelAndView;

	}
	
	private List<IndexItem> getResults(String searchQuery) throws ParseException, IOException {
	
        // creating the Searcher to the current index location
        Searcher searcher = new Searcher(INDEX_DIR);

        //List<IndexItem> resultByTitle = searcher.findByTitle(searchQuery, DEFAULT_RESULT_SIZE);
        List<IndexItem> resultByContent = searcher.findByContent(searchQuery, MAX_RESULT_SIZE);
        searcher.close();
        
		return resultByContent;
    
		
		/*Icon format
		 * 
		 * If it is a doc   http://c.dryicons.com/images/icon_sets/coquette_part_6_icons_set/png/64x64/doc_file.png
		 * If it is a pdf   http://c.dryicons.com/images/icon_sets/coquette_part_4_icons_set/png/128x128/pdf_file.png
		 * If it is a ppt   http://a.dryicons.com/images/icon_sets/coquette_part_5_icons_set/png/128x128/ppt_file.png
		 * If it is a xsl   http://a.dryicons.com/images/icon_sets/coquette_part_6_icons_set/png/128x128/xls_file.png
		 * If it is a attachment http://a.dryicons.com/images/icon_sets/colorful_stickers_icons_set/png/128x128/attachment.png
		 * If it is a video    http://b.dryicons.com/images/icon_sets/minimalistica_red_part_2_icons/png/128x128/video_player.png
		 * 		 * .xls.ppt.docx.pptx.xlsx 
		 */

	}
	
}
