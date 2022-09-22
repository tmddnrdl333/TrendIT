package com.trendit.api.controller;

import com.trendit.api.response.CompanyListGetRes;
import com.trendit.api.response.SearchCompanyGetRes;
import com.trendit.api.service.CompanyService;
import com.trendit.common.model.response.BaseRes;
import com.trendit.db.entity.Company;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/company")
@AllArgsConstructor
public class CompanyController {

    CompanyService companyService;

    @GetMapping("/list")
    @ApiOperation(value="IT기업 리스트", notes = "IT기업 리스트를 가져옵니다.")
    @ApiResponses({
            @ApiResponse(code=200, message = "리스트를 성공적으로 불러왔습니다."),
            @ApiResponse(code=500, message = "서버 에러 발생.")
    })
    public ResponseEntity getCompanyList() {
        List<Company> data;
        try {
            data = companyService.getCompanyList();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(BaseRes.of(500, "서버 에러 발생."));
        }

        return ResponseEntity.status(200).body(CompanyListGetRes.of(200, "Success", data));
    }

    @GetMapping("/search/{companyName}")
    @ApiOperation(value="기업 이름으로 검색", notes = "기업 이름으로 검색")
    @ApiResponses({
            @ApiResponse(code=200, message = "리스트를 성공적으로 불러왔습니다."),
            @ApiResponse(code=500, message = "서버 에러 발생.")
    })
    public ResponseEntity<SearchCompanyGetRes> searchCompany(@PathVariable String companyName, Pageable pageable) {
        Page<Company> companyPage = companyService.searchCompanyWithPageable(companyName, pageable);
        return ResponseEntity.status(200).body(SearchCompanyGetRes.of(200, "Success", companyPage));
    }
}
