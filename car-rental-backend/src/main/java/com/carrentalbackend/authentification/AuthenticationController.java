package com.carrentalbackend.authentification;

import com.carrentalbackend.authentification.dto.AgentRegisterRequest;
import com.carrentalbackend.authentification.dto.AuthenticationRequest;
import com.carrentalbackend.authentification.dto.AuthenticationResponse;
import com.carrentalbackend.authentification.dto.CustomerRegisterRequest;
import com.carrentalbackend.users.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

  private final AuthenticationService service;
  private final UserService userService;

  public AuthenticationController(AuthenticationService service, UserService userService) {
    this.service = service;
    this.userService = userService;
  }

  @CrossOrigin(origins = "*")
  @PostMapping("/agent-register")
  public ResponseEntity<AuthenticationResponse> agentRegister(
          @RequestBody AgentRegisterRequest request
  ) {
    return ResponseEntity.ok(service.agentRegister(request));
  }

  @CrossOrigin(origins = "*")
  @PostMapping("/customer-register")
  public ResponseEntity<AuthenticationResponse> customerRegister(
          @RequestBody CustomerRegisterRequest request
  ) {
    return ResponseEntity.ok(service.customerRegister(request));
  }

  @CrossOrigin(origins = "*")
  @PostMapping("/authenticate")
  public ResponseEntity<AuthenticationResponse> authenticate(
          @RequestBody AuthenticationRequest request
  ) {
    return ResponseEntity.ok(service.authenticate(request));
  }

  @CrossOrigin(origins = "*")
  @PostMapping("/refresh-token")
  public void refreshToken(
          HttpServletRequest request,
          HttpServletResponse response
  ) throws IOException {
    service.refreshToken(request, response);
  }

}
