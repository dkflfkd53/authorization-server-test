package com.founderz.authorizationservertest.controller;

import com.founderz.authorizationservertest.controller.dto.ClientRegistrationRequest;
import com.founderz.authorizationservertest.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @PostMapping("/")
    public void registerClient(@RequestBody ClientRegistrationRequest request) {
        clientService.registerClient(request);
    }
}
