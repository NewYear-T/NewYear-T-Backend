package com.example.newyear.Response;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ResponseService {
    public <T> SingleResponse<T> getSingleResponse(T data) {
        SingleResponse singleResponse = new SingleResponse();
        singleResponse.data = data;
        singleResponse.setSuccessResponse();

        return singleResponse;
    }

    public <T> ListResponse<T> getListResponse(List<T> datalist) {
        ListResponse listResponse = new ListResponse();
        listResponse.datalist = datalist;
        listResponse.setSuccessResponse();
        return listResponse;
    }



}