package net.xhalo.video.dao;

import net.xhalo.video.model.Category;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryDao {

    Category getCategoryById(@Param("id") Long id);

    List<Category> getAllCategories();
}
