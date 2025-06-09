package com.workintech.s19d2.controller;

import com.workintech.s19d2.entity.Account;
import com.workintech.s19d2.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    // [GET] /account → tüm hesapları getir (USER veya ADMIN)
    @GetMapping
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public List<Account> findAll() {
        return accountService.findAll();
    }

    // [GET] /account/{id} → ID’ye göre hesap getir (USER veya ADMIN)
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public ResponseEntity<Account> findById(@PathVariable Long id) {
        return accountService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // [POST] /account → yeni hesap oluştur (sadece ADMIN)
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Account save(@RequestBody Account account) {
        return accountService.save(account);
    }

    // [PUT] /account/{id} → hesap güncelle (sadece ADMIN)
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Account update(@PathVariable Long id, @RequestBody Account account) {
        return accountService.update(id, account);
    }

    // [DELETE] /account/{id} → hesap sil (sadece ADMIN)
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void delete(@PathVariable Long id) {
        accountService.delete(id);
    }
}
