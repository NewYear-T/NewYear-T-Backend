package com.example.newyear.Service;

import com.example.newyear.Dto.CategoryDto;
import com.example.newyear.Entity.Category;
import com.example.newyear.Repository.CategoryRepository;
import com.example.newyear.Response.ListResponse;
import com.example.newyear.Response.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ResponseService responseService;

    /**
     * 카테고리 조회
     */
    public ListResponse getCategories(){
        List<CategoryDto> categoryDtoList = categoryRepository.findAll().stream().
                map(CategoryDto::from).collect(Collectors.toList());


        return responseService.getListResponse(categoryDtoList);
    }
}
