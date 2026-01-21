package com.example.lost_and_found.service;

import com.example.lost_and_found.entity.Item;
import com.example.lost_and_found.entity.User;
import com.example.lost_and_found.repository.ItemRepository;
import com.example.lost_and_found.repository.UserRepository;
import com.example.lost_and_found.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AdminService {

    private static final Logger logger = LoggerFactory.getLogger(AdminService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ItemRepository itemRepository;

    public List<User> getAllUsers() {
        logger.info("Admin request to fetch all users.");
        return userRepository.findAll();
    }

    public List<Item> getAllItems() {
        logger.info("Admin request to fetch all items.");
        return itemRepository.findAll();
    }

    @Transactional
    public Item resolveItem(Long id) {
        logger.info("Admin request to resolve item with id: {}", id);
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Failed to resolve item: No item found with id: {}", id);
                    return new ResourceNotFoundException("Item not found with id: " + id);
                });
        
        item.setStatus(com.example.lost_and_found.entity.enums.ItemStatus.RESOLVED);
        Item savedItem = itemRepository.save(item);
        logger.info("Successfully resolved item with id: {}", id);
        return savedItem;
    }
}
