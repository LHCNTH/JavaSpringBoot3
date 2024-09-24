package com.llhtdqc.spring_boot_3.Controller;

import com.llhtdqc.spring_boot_3.DTO.Request.UserCreationRequest;
import com.llhtdqc.spring_boot_3.DTO.Request.UserResponseDTO;
import com.llhtdqc.spring_boot_3.DTO.Request.UserUpdateRequest;
import com.llhtdqc.spring_boot_3.Response.ApiResponse;
import com.llhtdqc.spring_boot_3.Entity.User;
import com.llhtdqc.spring_boot_3.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("get-users")
    public ResponseEntity<ApiResponse<List<UserResponseDTO>>> getUsers() {
        List<User> users = userService.getUsers();

        if (users.isEmpty()) {
            ApiResponse<List<UserResponseDTO>> response = new ApiResponse<>(null, HttpStatus.NO_CONTENT.value(), "Không có dữ liệu", false);
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        }

        List<UserResponseDTO> userResponses = users.stream().
                map(user -> new UserResponseDTO(
                        user.getId(),
                        user.getUserName(),
                        user.getPassWord(),
                        user.getFirstName(),
                        user.getLastName(),
                        user.getDob()
                    )
                ).toList();

        ApiResponse<List<UserResponseDTO>> response = new ApiResponse<>(userResponses, HttpStatus.OK.value(), "Xử lý thành công !", true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<?>> getUserById(@PathVariable("userId") String userId) {
        User user = userService.getUser(userId);

        // Kiểm tra nếu user không tồn tại
        if (user == null) {
            // Trả về phản hồi với thông báo lỗi
            ApiResponse<String> errorResponse = new ApiResponse<>(
                    "id không tồn tại", // data
                    HttpStatus.BAD_REQUEST.value(), // statusCode
                    "Xử lý thất bại!", // message
                    false // success
            );
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }

        // Chuyển đổi User sang UserResponseDTO
        UserResponseDTO userResponse = new UserResponseDTO(
                user.getId(),
                user.getUserName(),
                user.getPassWord(),
                user.getFirstName(),
                user.getLastName(),
                user.getDob()
        );

        // Trả về phản hồi khi tìm thấy user
        ApiResponse<UserResponseDTO> response = new ApiResponse<>(
                userResponse,
                HttpStatus.OK.value(),
                "Xử lý thành công!",
                true
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



    @PostMapping("/insert-user")
    public ResponseEntity<ApiResponse<User>> createUser(@RequestBody @Valid UserCreationRequest request) {
        User user = userService.createUser(request);
        ApiResponse<User> response = new ApiResponse<>(user, HttpStatus.OK.value(), "Xử lý thành công !", true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<ApiResponse<?>> updateUser(@PathVariable("userId") String userId, @RequestBody UserUpdateRequest request) {
        User checkUser = userService.getUser(userId);

        if (checkUser == null) {
            // Trả về phản hồi với thông báo lỗi
            ApiResponse<String> errorResponse = new ApiResponse<>(
                    "id không tồn tại", // data
                    HttpStatus.BAD_REQUEST.value(), // statusCode
                    "Xử lý thất bại!", // message
                    false // success
            );
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }

        User user = userService.updateUser(userId, request);

        UserResponseDTO userResponse = new UserResponseDTO(
                user.getId(),
                user.getUserName(),
                user.getPassWord(),
                user.getFirstName(),
                user.getLastName(),
                user.getDob());

        ApiResponse<UserResponseDTO> response = new ApiResponse<>(userResponse, HttpStatus.OK.value(), "Cập nhật thành công !", true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse<String>> deleteUser(@PathVariable("userId") String userId) {
        User user = userService.getUser(userId);

        if (user == null) {
            // Trả về phản hồi với thông báo lỗi
            ApiResponse<String> errorResponse = new ApiResponse<>(
                    "id không tồn tại", // data
                    HttpStatus.BAD_REQUEST.value(), // statusCode
                    "Xử lý thất bại!", // message
                    false // success
            );
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }

        userService.deleteUser(userId);
        ApiResponse<String> response = new ApiResponse<>(null, HttpStatus.OK.value(), "Xóa thành công !", true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
