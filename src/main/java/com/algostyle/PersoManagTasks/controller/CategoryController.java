package com.algostyle.PersoManagTasks.controller;

import com.algostyle.PersoManagTasks.dto.category.CategoryRequestBody;
import com.algostyle.PersoManagTasks.dto.category.CategoryResponseBody;
import com.algostyle.PersoManagTasks.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryResponseBody>> getAllCategories(){
        return new ResponseEntity<>(this.categoryService.getAllCategories(), HttpStatus.OK);
    }

    @GetMapping("/get-category/{id}")
    public ResponseEntity<CategoryResponseBody> getCategoryById(@PathVariable Long id){
        return new ResponseEntity<>(this.categoryService.getCategoryById(id), HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<String> addCategory(@Valid @RequestBody CategoryRequestBody categoryRequestBody){
        this.categoryService.addCategory(categoryRequestBody);
        return new ResponseEntity<>("la catégorie est ajoutée avec succès",HttpStatus.OK);
    }

    @PutMapping("/update-category/{id}")
    public ResponseEntity<String> updateCategory(@PathVariable Long id, @Valid @RequestBody CategoryRequestBody categoryRequestBody){
        this.categoryService.updateCategory(id,categoryRequestBody);
        return new ResponseEntity<>("la catégorie est modifiée avec succès",HttpStatus.OK);
    }


    @DeleteMapping("/delete-category/{id}")
    public ResponseEntity<String> deleteCategoryById(@PathVariable Long id){
        this.categoryService.deleteCategoryById(id);
        return new ResponseEntity<>("la catégorie est supprimée avec succès",HttpStatus.OK);
    }
}
