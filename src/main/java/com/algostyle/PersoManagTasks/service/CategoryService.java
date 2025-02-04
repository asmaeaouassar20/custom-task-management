package com.algostyle.PersoManagTasks.service;

import com.algostyle.PersoManagTasks.dto.category.CategoryRequestBody;
import com.algostyle.PersoManagTasks.dto.category.CategoryResponseBody;
import com.algostyle.PersoManagTasks.exception.AlreadyExistsCategoryException;
import com.algostyle.PersoManagTasks.exception.IdNotFoundException;
import com.algostyle.PersoManagTasks.model.Category;
import com.algostyle.PersoManagTasks.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private MapperService mapperService;

    public List<CategoryResponseBody> getAllCategories(){
        List<CategoryResponseBody> list=new ArrayList<>();
        for(Category category:this.categoryRepository.findAll()){
            list.add(this.mapperService.toCategoryResponseBody(category));
        }
        return list;
    }


    public void addCategory(CategoryRequestBody categoryRequestBody){
        if(categoryRepository.findByName(categoryRequestBody.getName()).isPresent()){
            throw new AlreadyExistsCategoryException("le nom de la catégorie saisi existe déjà");
        }
        Category category=mapperService.toCategory(categoryRequestBody);
        categoryRepository.save(category);
    }

    public void updateCategory(Long id, CategoryRequestBody categoryRequestBody){
        Optional<Category> fetchedCategoryOptional=this.categoryRepository.findById(id);
        if(!fetchedCategoryOptional.isPresent()){
            throw new IdNotFoundException("id de la catégorie saisi n'existe pas ");
        }
        Category categoryFromDB=fetchedCategoryOptional.get();
        categoryFromDB.setName(categoryRequestBody.getName());
        this.categoryRepository.save(categoryFromDB);
    }

    public CategoryResponseBody getCategoryById(Long id){
        Optional<Category> fetchedCategoryOptional=this.categoryRepository.findById(id);
        if(!fetchedCategoryOptional.isPresent()){
            throw new IdNotFoundException("id de la catégorie n'existe pas");
        }
        Category categoryFromDB=fetchedCategoryOptional.get();
        return this.mapperService.toCategoryResponseBody(categoryFromDB);
    }

    public void deleteCategoryById(Long id){
        Optional<Category> fetchedCategoryOptional=this.categoryRepository.findById(id);
        if(!fetchedCategoryOptional.isPresent()){
            throw new IdNotFoundException("id de la catégorie à supprimer n'existe pas");
        }
        this.categoryRepository.deleteById(id);
    }
}
