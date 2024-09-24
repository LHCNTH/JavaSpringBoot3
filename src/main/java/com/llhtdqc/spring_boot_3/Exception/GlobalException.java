package com.llhtdqc.spring_boot_3.Exception;

import com.llhtdqc.spring_boot_3.Response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {
    // Xử lý tên người dùng đã tồn tại
    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ApiResponse<String>> userExisted(RuntimeException ex) {
        ApiResponse<String> response = new ApiResponse<>(ex.getMessage(), 400, "Xử lý thất bại", false );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // Mật khẩu ít nhất 8 ký tự
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<String>> passwordNotValid(MethodArgumentNotValidException ex) {
        ApiResponse<String> response =
                new ApiResponse<>(ex.getFieldError().getDefaultMessage(),400,  "Xử lý thất bại", false);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
