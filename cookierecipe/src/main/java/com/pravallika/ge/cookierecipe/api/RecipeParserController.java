package com.pravallika.ge.cookierecipe.api;

import java.io.IOException;

import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecipeParserController {
	
	public static final Logger logger = LoggerFactory.getLogger(RecipeParserController.class);
			
	
	@PostMapping("/getRecipe")
	public String getRecipe(@RequestParam String url){
		
		Elements ingredientsList = null;
		Elements recipeSteps = null;
		JSONObject jsonResult = new JSONObject();
		try {
			
			Document document = Jsoup.connect(url).post();
			ingredientsList = document.getElementsByClass("list--bullets");
			for(Element ingredient: ingredientsList) {
				Elements liValue = ingredient.getElementsByTag("li");
				logger.info("element value {}", liValue);
			}
			recipeSteps = document.getElementsByClass("recipe__instructions");
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("caught Exception while fetching receipe details from {}", url);
		}
		
		
		String result = ingredientsList.toString() + recipeSteps.toString();
		return result;
		
	}

}
