package com.example.lost_and_found.controller;

import com.example.lost_and_found.dto.ApiResponse;
import com.example.lost_and_found.dto.LostItemRequest;
import com.example.lost_and_found.entity.LostItem;
import com.example.lost_and_found.service.LostItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/lost-items")
@CrossOrigin(origins = "*")
public class LostItemController {

    @Autowired
    private LostItemService lostItemService;

    // Public endpoints (no authentication required)
    @GetMapping("/public/all")
    public ResponseEntity<ApiResponse<List<LostItem>>> getAllLostItemsPublic() {
        List<LostItem> items = lostItemService.getAllLostItems();
        ApiResponse<List<LostItem>> response = new ApiResponse<>(HttpStatus.OK.value(), "Lost items retrieved successfully", items, LocalDateTime.now());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/public/{id}")
    public ResponseEntity<ApiResponse<LostItem>> getLostItemByIdPublic(@PathVariable Long id) {
        LostItem item = lostItemService.getLostItemById(id);
        ApiResponse<LostItem> response = new ApiResponse<>(HttpStatus.OK.value(), "Lost item retrieved successfully", item, LocalDateTime.now());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/public/search")
    public ResponseEntity<ApiResponse<List<LostItem>>> searchLostItemsPublic(@RequestParam String keyword) {
        List<LostItem> items = lostItemService.searchLostItems(keyword);
        ApiResponse<List<LostItem>> response = new ApiResponse<>(HttpStatus.OK.value(), "Search completed successfully", items, LocalDateTime.now());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/public/status/{status}")
    public ResponseEntity<ApiResponse<List<LostItem>>> getLostItemsByStatusPublic(@PathVariable String status) {
        List<LostItem> items = lostItemService.getLostItemsByStatus(status); // Status conversion handled by service
        ApiResponse<List<LostItem>> response = new ApiResponse<>(HttpStatus.OK.value(), "Lost items retrieved successfully", items, LocalDateTime.now());
        return ResponseEntity.ok(response);
    }

    // Authenticated endpoints
    @GetMapping
    public ResponseEntity<ApiResponse<List<LostItem>>> getAllLostItems() {
        List<LostItem> items = lostItemService.getAllLostItems();
        ApiResponse<List<LostItem>> response = new ApiResponse<>(HttpStatus.OK.value(), "Lost items retrieved successfully", items, LocalDateTime.now());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<LostItem>> getLostItemById(@PathVariable Long id) {
        LostItem item = lostItemService.getLostItemById(id);
        ApiResponse<LostItem> response = new ApiResponse<>(HttpStatus.OK.value(), "Lost item retrieved successfully", item, LocalDateTime.now());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<LostItem>>> searchLostItems(@RequestParam String keyword) {
        List<LostItem> items = lostItemService.searchLostItems(keyword);
        ApiResponse<List<LostItem>> response = new ApiResponse<>(HttpStatus.OK.value(), "Search completed successfully", items, LocalDateTime.now());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/date-range")
    public ResponseEntity<ApiResponse<List<LostItem>>> getLostItemsByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<LostItem> items = lostItemService.getLostItemsByDateRange(startDate, endDate);
        ApiResponse<List<LostItem>> response = new ApiResponse<>(HttpStatus.OK.value(), "Lost items retrieved successfully", items, LocalDateTime.now());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<ApiResponse<List<LostItem>>> getLostItemsByStatus(@PathVariable String status) {
        List<LostItem> items = lostItemService.getLostItemsByStatus(status); // Status conversion handled by service
        ApiResponse<List<LostItem>> response = new ApiResponse<>(HttpStatus.OK.value(), "Lost items retrieved successfully", items, LocalDateTime.now());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/my-reports")
    public ResponseEntity<ApiResponse<List<LostItem>>> getMyLostItems() {
        List<LostItem> items = lostItemService.getMyLostItems();
        ApiResponse<List<LostItem>> response = new ApiResponse<>(HttpStatus.OK.value(), "Your lost items retrieved successfully", items, LocalDateTime.now());
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<LostItem>> createLostItem(@Valid @RequestBody LostItemRequest request) {
        LostItem item = lostItemService.createLostItem(request);
        ApiResponse<LostItem> response = new ApiResponse<>(HttpStatus.CREATED.value(), "Lost item reported successfully", item, LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<LostItem>> updateLostItem(
            @PathVariable Long id,
            @Valid @RequestBody LostItemRequest request) {
        LostItem item = lostItemService.updateLostItem(id, request);
        ApiResponse<LostItem> response = new ApiResponse<>(HttpStatus.OK.value(), "Lost item updated successfully", item, LocalDateTime.now());
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/status")
    @PreAuthorize("hasAnyRole('ADMIN', 'VOLUNTEER')")
    public ResponseEntity<ApiResponse<LostItem>> updateLostItemStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        LostItem item = lostItemService.updateLostItemStatus(id, status); // Status conversion handled by service
        ApiResponse<LostItem> response = new ApiResponse<>(HttpStatus.OK.value(), "Lost item status updated successfully", item, LocalDateTime.now());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Void>> deleteLostItem(@PathVariable Long id) {
        lostItemService.deleteLostItem(id);
        ApiResponse<Void> response = new ApiResponse<>(HttpStatus.OK.value(), "Lost item deleted successfully", null, LocalDateTime.now());
        return ResponseEntity.ok(response);
    }
}
