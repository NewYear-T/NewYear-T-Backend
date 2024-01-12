package com.example.newyear.Controller;


import com.example.newyear.Dto.ChallengeDto;
import com.example.newyear.Response.ListResponse;
import com.example.newyear.Response.ResponseService;
import com.example.newyear.Service.CategoryService;
import com.example.newyear.Service.ChallengeService;
import com.example.newyear.Service.CompletedService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    ChallengeService challengeService;

    @Autowired
    ResponseService responseService;

    @GetMapping("/category")
    public ListResponse getCategory(){
        ListResponse response = categoryService.getCategories();

        return response;
    }

    @GetMapping("/category/{categoryId}")
    public ListResponse getChallenges(@PathVariable Long categoryId){
        List<ChallengeDto> challengeDtoList = challengeService.getChallengesByCategory(categoryId);

        return responseService.getListResponse(challengeDtoList);
    }


}
