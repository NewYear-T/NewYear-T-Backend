package com.example.newyear.Controller;


import com.example.newyear.Dto.ChallengeDto;
import com.example.newyear.Response.ListResponse;
import com.example.newyear.Response.ResponseService;
import com.example.newyear.Service.CategoryService;
import com.example.newyear.Service.ChallengeService;
import com.example.newyear.Service.CompletedService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @Operation(summary = "카테고리 전체 조회 API", description = "카테고리 전체 조회 API 입니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "카테고리 전체 조회에 성공하였습니다."),
            @ApiResponse(responseCode = "404", description = "해당 카테고리가 존재하지 않습니다.")
    })
    @GetMapping("/category")
    public ListResponse getCategory(){
        ListResponse response = categoryService.getCategories();

        return response;
    }

    @Operation(summary = "챌린지 목록 조회 API", description = "챌린지 목록을 조회하는 API 입니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "챌린지 목록 조회에 성공하였습니다."),
            @ApiResponse(responseCode = "404", description = "해당 챌린지가 존재하지 않습니다.")
    })
    @GetMapping("/category/{categoryId}")
    public ListResponse getChallenges(@PathVariable Long categoryId){
        List<ChallengeDto> challengeDtoList = challengeService.getChallengesByCategory(categoryId);

        return responseService.getListResponse(challengeDtoList);
    }

    @GetMapping("/category/param/{categoryId}")
    public ListResponse getChallenge(@PathVariable("categoryId") Long categoryId,
            @RequestParam(required = false) String gender, @RequestParam(required = false) String local ){
        List<ChallengeDto> challengeDtoList = challengeService.getChallengesBy(categoryId, gender,local);

        return responseService.getListResponse(challengeDtoList);
    }


}
