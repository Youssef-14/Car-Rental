package com.carrentalbackend.users;

import com.carrentalbackend.users.dto.ChangePasswordRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/v1/users")
@PreAuthorize("isAuthenticated()")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/verifyid/{id}")
    public CompletableFuture<ResponseEntity<?>> getUserById(@PathVariable("id") Long id) {
        CompletableFuture<User> userFuture = CompletableFuture.supplyAsync(() -> service.getUserById(id));

        if (userFuture.isCompletedExceptionally()) {
            return CompletableFuture.completedFuture(ResponseEntity.badRequest().build());
        }

        return userFuture.thenApply(user -> {
            System.out.println(user);
            return ResponseEntity.ok(user.toString());
        });
    }

    @PatchMapping("/change-password")
    public ResponseEntity<?> changePassword(
            @RequestBody ChangePasswordRequest request,
            Principal connectedUser
    ) {
        service.changePassword(request, connectedUser);
        return ResponseEntity.ok().build();
    }
}
