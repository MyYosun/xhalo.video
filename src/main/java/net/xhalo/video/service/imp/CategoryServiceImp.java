package net.xhalo.video.service.imp;

import net.xhalo.video.dao.CategoryDao;
import net.xhalo.video.model.Category;
import net.xhalo.video.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoryServiceImp implements ICategoryService {
	@Autowired
	private CategoryDao categoryDao;

	public List<Category> getAllCategories() {
		return categoryDao.getAllCategories();
	}

	public Category getCategoryById(Category obj) {
		return categoryDao.getCategoryById(obj.getId());
	}
}
