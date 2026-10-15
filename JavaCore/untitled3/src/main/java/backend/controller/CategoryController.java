package backend.controller;

import backend.service.ICategoryService;
import backend.service.impl.CategoryService;
import entity.Category;

import java.util.List;

public class CategoryController {
    private final ICategoryService service;

    public CategoryController() {
        service = new CategoryService();
    }

    public List<Category> getCategories() {
        return service.getCategories();
    }
}
