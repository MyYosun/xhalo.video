package net.xhalo.video.service.imp;

import net.xhalo.video.dao.CategoryDao;
import net.xhalo.video.model.Category;
import net.xhalo.video.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static net.xhalo.video.config.MaginNumberProperties.NUM_ONE;

@Service
@Transactional
public class CategoryServiceImp implements ICategoryService {

    @Autowired
    private CategoryDao categoryDao;

    @Cacheable(value = "category", key = "#root.methodName")
    public List<Category> getAllCategories() {
        return categoryDao.getAllCategories();
    }

    @Cacheable(value = "category", key = "#categoryId")
    public Category getCategoryById(Long categoryId) {
        return categoryDao.getCategoryById(categoryId);
    }

    @Override
    @CacheEvict(value = "category", allEntries = true, beforeInvocation = true)
    public boolean addCategory(Category category) {
        return categoryDao.addCategory(category) == NUM_ONE;
    }
}
