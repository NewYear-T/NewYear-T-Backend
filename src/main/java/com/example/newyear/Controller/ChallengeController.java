package com.example.newyear.Controller;

import com.example.newyear.Response.ResponseService;
import com.example.newyear.Service.CompletedService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChallengeController {
    @Autowired
    CompletedService completedService;

    @Autowired
    ResponseService responseService;



}
