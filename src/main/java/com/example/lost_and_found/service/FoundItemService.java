package com.example.lost_and_found.service;

import com.example.lost_and_found.dto.FoundItemRequest;
import com.example.lost_and_found.entity.FoundItem;
import com.example.lost_and_found.entity.User;
import com.example.lost_and_found.exception.ResourceNotFoundException;
import com.example.lost_and_found.repository.FoundItemRepository;
import com.example.lost_and_found.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class FoundItemService {

    @Autowired
    private FoundItemRepository foundItemRepository;

    @Autowired
    private UserRepository userRepository;

    public List<FoundItem> getAllFoundItems() {
        return foundItemRepository.findAll();
    }

    public FoundItem getFoundItemById(Long id) {
        return foundItemRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Found item not found with id: " + id));
    }

    public List<FoundItem> searchFoundItems(String keyword) {
        return foundItemRepository.searchByKeyword(keyword);
    }

    public List<FoundItem> getFoundItemsByDateRange(LocalDate startDate, LocalDate endDate) {
        return foundItemRepository.findByFoundDateBetween(startDate, endDate);
    }

    public List<FoundItem> getFoundItemsByStatus(String status) {
        return foundItemRepository.findByStatus(status);
    }

    public FoundItem createFoundItem(FoundItemRequest request) {

        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        FoundItem foundItem = new FoundItem();

        foundItem.setPersonName(request.getPersonName());
        foundItem.setAge(request.getAge());              // ✅ Integer → Integer
        foundItem.setGender(request.getGender());
        foundItem.setDescription(request.getDescription());
        foundItem.setLocation(request.getLocation());
        foundItem.setFoundDate(request.getFoundDate());
        foundItem.setContactNumber(request.getContactNumber());
        foundItem.setImageUrl(request.getImageUrl());

        foundItem.setStatus("FOUND");
        foundItem.setReportedBy(user);
        foundItem.setCreatedAt(LocalDateTime.now());

        return foundItemRepository.save(foundItem);
    }

    public FoundItem updateFoundItem(Long id, FoundItemRequest request) {

        FoundItem foundItem = getFoundItemById(id);

        foundItem.setPersonName(request.getPersonName());
        foundItem.setAge(request.getAge());
        foundItem.setGender(request.getGender());
        foundItem.setDescription(request.getDescription());
        foundItem.setLocation(request.getLocation());
        foundItem.setFoundDate(request.getFoundDate());
        foundItem.setContactNumber(request.getContactNumber());
        foundItem.setImageUrl(request.getImageUrl());

        return foundItemRepository.save(foundItem);
    }

    public FoundItem updateFoundItemStatus(Long id, String status) {
        FoundItem foundItem = getFoundItemById(id);
        foundItem.setStatus(status);
        return foundItemRepository.save(foundItem);
    }

    public void deleteFoundItem(Long id) {
        FoundItem foundItem = getFoundItemById(id);
        foundItemRepository.delete(foundItem);
    }
}
