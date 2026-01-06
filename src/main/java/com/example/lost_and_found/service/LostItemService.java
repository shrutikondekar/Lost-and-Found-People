package com.example.lost_and_found.service;

import com.example.lost_and_found.dto.LostItemRequest;
import com.example.lost_and_found.entity.LostItem;
import com.example.lost_and_found.entity.User;
import com.example.lost_and_found.exception.ResourceNotFoundException;
import com.example.lost_and_found.repository.LostItemRepository;
import com.example.lost_and_found.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class LostItemService {

    @Autowired
    private LostItemRepository lostItemRepository;

    @Autowired
    private UserRepository userRepository;

    public List<LostItem> getAllLostItems() {
        return lostItemRepository.findAll();
    }

    public LostItem getLostItemById(Long id) {
        return lostItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lost item not found with id: " + id));
    }

    public List<LostItem> searchLostItems(String keyword) {
        return lostItemRepository.searchByKeyword(keyword);
    }

    public List<LostItem> getLostItemsByDateRange(LocalDate startDate, LocalDate endDate) {
        return lostItemRepository.findByLostDateBetween(startDate, endDate);
    }

    public List<LostItem> getLostItemsByStatus(String status) {
        return lostItemRepository.findByStatus(status);
    }

    public LostItem createLostItem(LostItemRequest request) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        LostItem lostItem = new LostItem();
        lostItem.setPersonName(request.getPersonName());
        lostItem.setAge(request.getAge());
        lostItem.setGender(request.getGender());
        lostItem.setDescription(request.getDescription());
        lostItem.setLastSeenLocation(request.getLastSeenLocation());
        lostItem.setLostDate(request.getLostDate());
        lostItem.setReporterName(request.getReporterName());
        lostItem.setReporterContact(request.getReporterContact());
        lostItem.setImageUrl(request.getImageUrl());
        lostItem.setStatus("LOST");
        lostItem.setReportedBy(user);
        lostItem.setCreatedAt(LocalDateTime.now());

        return lostItemRepository.save(lostItem);
    }

    public LostItem updateLostItem(Long id, LostItemRequest request) {
        LostItem lostItem = getLostItemById(id);
        
        lostItem.setPersonName(request.getPersonName());
        lostItem.setAge(request.getAge());
        lostItem.setGender(request.getGender());
        lostItem.setDescription(request.getDescription());
        lostItem.setLastSeenLocation(request.getLastSeenLocation());
        lostItem.setLostDate(request.getLostDate());
        lostItem.setReporterName(request.getReporterName());
        lostItem.setReporterContact(request.getReporterContact());
        lostItem.setImageUrl(request.getImageUrl());

        return lostItemRepository.save(lostItem);
    }

    public LostItem updateLostItemStatus(Long id, String status) {
        LostItem lostItem = getLostItemById(id);
        lostItem.setStatus(status);
        return lostItemRepository.save(lostItem);
    }

    public void deleteLostItem(Long id) {
        LostItem lostItem = getLostItemById(id);
        lostItemRepository.delete(lostItem);
    }

    public List<LostItem> getMyLostItems() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        
        return lostItemRepository.findByReportedBy(user);
    }
}