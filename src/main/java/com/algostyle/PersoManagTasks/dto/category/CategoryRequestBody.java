package com.algostyle.PersoManagTasks.dto.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CategoryRequestBody {

    @NotBlank(message = "le nom de la catégorie est obligatoire")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
