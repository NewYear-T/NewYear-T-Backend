package com.example.newyear.Dto;

import com.example.newyear.Entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {

    private Long id;

    private String categoryName;

    public static CategoryDto from(Category category) {
        return new CategoryDto(category.getId(), category.getCategoryName());
    }
}
