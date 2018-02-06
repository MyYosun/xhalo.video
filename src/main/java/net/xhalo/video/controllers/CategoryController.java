package net.xhalo.video.controllers;

import net.xhalo.video.model.Category;
import net.xhalo.video.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class CategoryController {
	@Autowired
	private ICategoryService categoryService;

	@ResponseBody
	@RequestMapping(value = "/getAllCategories", method = RequestMethod.GET)
	public List<Category> getAllCategories(){
		List<Category> categoryList = categoryService.getAllCategories();
		return categoryList;
	}
}
