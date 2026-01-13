package com.example.lost_and_found.controller;

import com.example.lost_and_found.entity.Item;
import com.example.lost_and_found.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    @Autowired
    private ItemService service;

    @GetMapping
    public List<Item> getItems() {
        return service.getAll();
    }

    @PostMapping
    public Item addItem(@RequestBody Item item) {
        return service.save(item);
    }
}
