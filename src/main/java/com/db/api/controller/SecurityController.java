package com.db.api.controller;

import com.db.api.model.Security;
import com.db.api.repository.SecurityRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/security")
@CrossOrigin
public class SecurityController {
    private final SecurityRepository securityRepository;
    public SecurityController(SecurityRepository securityRepository) {
        this.securityRepository = securityRepository;
    }

    @GetMapping
    public List<Security> getSecurities() {
        return securityRepository.findAll();
    }

    @GetMapping("/{id}")
    public Security getSecurity(@PathVariable Long id) {
        return securityRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @PostMapping
    public ResponseEntity createSecurity(@RequestBody Security security) throws URISyntaxException {
        Security savedSecurity = securityRepository.save(security);
        return ResponseEntity.created(new URI("/securities/" + savedSecurity.getId())).body(savedSecurity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteClient(@PathVariable Long id) {
        securityRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
