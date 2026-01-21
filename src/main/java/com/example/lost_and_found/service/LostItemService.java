package com.example.lost_and_found.service;

import com.example.lost_and_found.dto.LostItemRequest;
import com.example.lost_and_found.entity.LostItem;
import com.example.lost_and_found.entity.User;
import com.example.lost_and_found.entity.enums.ItemStatus;
import com.example.lost_and_found.exception.BadRequestException;
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
                .orElseThrow(() ->
                        new ResourceNotFoundException("Lost item not found with id: " + id));
    }

    public List<LostItem> searchLostItems(String keyword) {
        return lostItemRepository.searchByKeyword(keyword);
    }

    public List<LostItem> getLostItemsByDateRange(LocalDate startDate, LocalDate endDate) {
        return lostItemRepository.findByLostDateBetween(startDate, endDate);
    }

    public List<LostItem> getLostItemsByStatus(String status) {
        ItemStatus itemStatus;
        try {
            // Convert string status to enum
            itemStatus = ItemStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Invalid item status: " + status, e);
        }
        return lostItemRepository.findByStatus(itemStatus);
    }

    public LostItem createLostItem(LostItemRequest request) {

        String username = SecurityContextHolder.getContext()
                .getAuthentication().getName();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

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
        
        // Assign status from request or default to LOST
        if (request.getStatus() != null && !request.getStatus().isEmpty()) {
            try {
                // Convert string status to enum
                lostItem.setStatus(ItemStatus.valueOf(request.getStatus().toUpperCase()));
            } catch (IllegalArgumentException e) {
                throw new BadRequestException("Invalid item status provided in request: " + request.getStatus(), e);
            }
        } else {
            // Default to LOST if not provided in the request
            lostItem.setStatus(ItemStatus.LOST);
        }
        lostItem.setReportedBy(user);
        // createdAt and updatedAt are handled by @PrePersist and @PreUpdate

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
        
        // If status is provided in the request, convert and update it
        if (request.getStatus() != null && !request.getStatus().isEmpty()) {
            try {
                // Convert string status to enum
                lostItem.setStatus(ItemStatus.valueOf(request.getStatus().toUpperCase()));
            } catch (IllegalArgumentException e) {
                throw new BadRequestException("Invalid item status provided for update: " + request.getStatus(), e);
            }
        }
        // createdAt and updatedAt are handled by @PrePersist and @PreUpdate

        return lostItemRepository.save(lostItem);
    }

    public LostItem updateLostItemStatus(Long id, String status) {
        LostItem lostItem = getLostItemById(id);
        ItemStatus itemStatus;
        try {
            // Convert string status to enum
            itemStatus = ItemStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Invalid item status: " + status, e);
        }
        lostItem.setStatus(itemStatus);
        return lostItemRepository.save(lostItem);
    }

    public void deleteLostItem(Long id) {
        LostItem lostItem = getLostItemById(id);
        lostItemRepository.delete(lostItem);
    }

    public List<LostItem> getMyLostItems() {

        String username = SecurityContextHolder.getContext()
                .getAuthentication().getName();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        return lostItemRepository.findByReportedBy(user);
    }
}
