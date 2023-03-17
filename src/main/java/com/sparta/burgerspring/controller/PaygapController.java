//package com.sparta.burgerspring.controller;
//
//import com.sparta.burgerspring.service.SalaryService;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.Optional;
//@RestController
//public class PaygapController {
//
//    private SalaryService salaryService;
//
//    public PaygapController(SalaryService salaryService) {
//        this.salaryService = salaryService;
//    }
//
//    @GetMapping(value = "/paygap/{department}")
//    public ResponseEntity<String> getPaygap(@PathVariable String department){
//        String paygapInfo = salaryService.genderPaygap(department);
//        return getStringResponseEntity(paygapInfo);
//    }
//    @GetMapping(value = "/paygap/")
//    public ResponseEntity<String> getPaygapDefault(){
//        String paygapInfo = salaryService.genderPaygap("none");
//        return getStringResponseEntity(paygapInfo);
//    }
//    @GetMapping(value = "/paygap")
//    public ResponseEntity<String> getDefaultAllPaygap(){
//        String paygapInfo = salaryService.genderPaygap("none");
//        return getStringResponseEntity(paygapInfo);
//    }
//
//    private ResponseEntity<String> getStringResponseEntity(String paygapInfo) {
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.add("content-type","application/json");
//        ResponseEntity<String> response = null;
//        if(paygapInfo!=null){
//            response = new ResponseEntity<>("{\"message\": \""+paygapInfo+"\" }",
//                    httpHeaders, HttpStatus.OK);
//        }
//        return response;
//    }
//}