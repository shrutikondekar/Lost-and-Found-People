package com.example.lost_and_found.service;

import com.example.lost_and_found.entity.Item;
import com.example.lost_and_found.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepository repo;

    public List<Item> getAll() {
        return repo.findAll();
    }

    public Item save(Item item) {
        return repo.save(item);
    }
}
