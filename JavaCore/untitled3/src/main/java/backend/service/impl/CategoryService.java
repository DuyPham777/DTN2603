package backend.service.impl;

import backend.repository.ICategoryRepository;
import backend.repository.impl.CategoryRepository;
import backend.service.ICategoryService;
import entity.Category;

import java.util.List;

public class CategoryService implements ICategoryService {
    private final ICategoryRepository categoryRepository;

    public CategoryService() {
        categoryRepository = new CategoryRepository();
    }

    @Override
    public List<Category> getCategories() {
        return categoryRepository.getCategories();
    }
}
