package net.xhalo.video.controllers;

import net.xhalo.video.model.Category;
import net.xhalo.video.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;

@Controller
@Scope(value = "prototype")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;

    @RequestMapping(value = "/getAllCategories", method = RequestMethod.GET)
    @ResponseBody
    public List<Category> getAllCategories() {
        List<Category> categoryList = categoryService.getAllCategories();
        return categoryList;
    }

    @RequestMapping(value = "/category-{categoryId}.html")
    public String getVideosByCategoryId(@PathVariable(value = "categoryId") Long categoryId, Model model) {
        Category result = categoryService.getCategoryById(categoryId);
        if (result == null)
            return "/error/404";
        model.addAttribute("category", result);
        return "showVideos";
    }

    @RequestMapping(value = "/adminAddCategory")
    @ResponseBody
    public String addCategory(@Valid Category category, Errors errors) {
        if (errors.hasErrors()) {
            return "addFail";
        }
        category.setBelongToOther(true);
        if (categoryService.addCategory(category)) {
            return "addSuccess";
        }
        return "addFail";
    }
}
