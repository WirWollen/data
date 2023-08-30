package com.list.data.controllers;

import com.list.data.services.UpdateDBService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/dev")
public class DevController {
    private final UpdateDBService updateDBService;

    @GetMapping
    public boolean startParse() {
        return updateDBService.parseAllItems();
//        return updateDBService.dev();
    }
}
