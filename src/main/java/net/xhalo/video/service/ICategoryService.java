package net.xhalo.video.service;

import net.xhalo.video.model.Category;

import java.util.List;

public interface ICategoryService {

    List<Category> getAllCategories();

    Category getCategoryById(Long categoryId);

    boolean addCategory(Category category);
}
