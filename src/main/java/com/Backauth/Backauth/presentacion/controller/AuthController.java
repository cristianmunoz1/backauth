package com.Backauth.Backauth.presentacion.controller;


import com.Backauth.Backauth.aplicacion.AuthService;
import com.Backauth.Backauth.aplicacion.ServiceUser;
import com.Backauth.Backauth.core.dominio.AuthResponse;
import com.Backauth.Backauth.core.dominio.LoginRequest;
import com.Backauth.Backauth.core.dominio.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:8021", "http://localhost:3000"})
public class AuthController {
    private final AuthService authService;
    private final ServiceUser serviceUser;

    @PostMapping(value = "login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request)
    {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping(value = "register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request)
    {
        if (serviceUser.getUserEmail(request.getUserEmail()).isPresent())
        {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        else if (serviceUser.getUser(request.getUserId()).isPresent())
        {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        else
        {
            return new ResponseEntity<>(authService.register(request), HttpStatus.CREATED);
        }
    }
}
