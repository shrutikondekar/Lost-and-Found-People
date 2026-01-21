package com.example.lost_and_found.controller;

import com.example.lost_and_found.dto.ApiResponse;
import com.example.lost_and_found.dto.FoundItemRequest;
import com.example.lost_and_found.entity.FoundItem;
import com.example.lost_and_found.service.FoundItemService;
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
@RequestMapping("/api/found-items")
@CrossOrigin(origins = "*")
public class FoundItemController {

    @Autowired
    private FoundItemService foundItemService;

    // Public endpoints (no authentication required)
    @GetMapping("/public/all")
    public ResponseEntity<ApiResponse<List<FoundItem>>> getAllFoundItemsPublic() {
        List<FoundItem> items = foundItemService.getAllFoundItems();
        ApiResponse<List<FoundItem>> response = new ApiResponse<>(HttpStatus.OK.value(), "Found items retrieved successfully", items, LocalDateTime.now());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/public/{id}")
    public ResponseEntity<ApiResponse<FoundItem>> getFoundItemByIdPublic(@PathVariable Long id) {
        FoundItem item = foundItemService.getFoundItemById(id);
        ApiResponse<FoundItem> response = new ApiResponse<>(HttpStatus.OK.value(), "Found item retrieved successfully", item, LocalDateTime.now());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/public/search")
    public ResponseEntity<ApiResponse<List<FoundItem>>> searchFoundItemsPublic(@RequestParam String keyword) {
        List<FoundItem> items = foundItemService.searchFoundItems(keyword);
        ApiResponse<List<FoundItem>> response = new ApiResponse<>(HttpStatus.OK.value(), "Search completed successfully", items, LocalDateTime.now());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/public/status/{status}")
    public ResponseEntity<ApiResponse<List<FoundItem>>> getFoundItemsByStatusPublic(@PathVariable String status) {
        List<FoundItem> items = foundItemService.getFoundItemsByStatus(status); // Status conversion handled by service
        ApiResponse<List<FoundItem>> response = new ApiResponse<>(HttpStatus.OK.value(), "Found items retrieved successfully", items, LocalDateTime.now());
        return ResponseEntity.ok(response);
    }

    // Authenticated endpoints
    @GetMapping
    public ResponseEntity<ApiResponse<List<FoundItem>>> getAllFoundItems() {
        List<FoundItem> items = foundItemService.getAllFoundItems();
        ApiResponse<List<FoundItem>> response = new ApiResponse<>(HttpStatus.OK.value(), "Found items retrieved successfully", items, LocalDateTime.now());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<FoundItem>> getFoundItemById(@PathVariable Long id) {
        FoundItem item = foundItemService.getFoundItemById(id);
        ApiResponse<FoundItem> response = new ApiResponse<>(HttpStatus.OK.value(), "Found item retrieved successfully", item, LocalDateTime.now());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<FoundItem>>> searchFoundItems(@RequestParam String keyword) {
        List<FoundItem> items = foundItemService.searchFoundItems(keyword);
        ApiResponse<List<FoundItem>> response = new ApiResponse<>(HttpStatus.OK.value(), "Search completed successfully", items, LocalDateTime.now());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/date-range")
    public ResponseEntity<ApiResponse<List<FoundItem>>> getFoundItemsByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<FoundItem> items = foundItemService.getFoundItemsByDateRange(startDate, endDate);
        ApiResponse<List<FoundItem>> response = new ApiResponse<>(HttpStatus.OK.value(), "Found items retrieved successfully", items, LocalDateTime.now());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<ApiResponse<List<FoundItem>>> getFoundItemsByStatus(@PathVariable String status) {
        List<FoundItem> items = foundItemService.getFoundItemsByStatus(status); // Status conversion handled by service
        ApiResponse<List<FoundItem>> response = new ApiResponse<>(HttpStatus.OK.value(), "Found items retrieved successfully", items, LocalDateTime.now());
        return ResponseEntity.ok(response);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'VOLUNTEER')")
    public ResponseEntity<ApiResponse<FoundItem>> createFoundItem(@Valid @RequestBody FoundItemRequest request) {
        FoundItem item = foundItemService.createFoundItem(request);
        ApiResponse<FoundItem> response = new ApiResponse<>(HttpStatus.CREATED.value(), "Found item created successfully", item, LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'VOLUNTEER')")
    public ResponseEntity<ApiResponse<FoundItem>> updateFoundItem(
            @PathVariable Long id,
            @Valid @RequestBody FoundItemRequest request) {
        FoundItem item = foundItemService.updateFoundItem(id, request);
        ApiResponse<FoundItem> response = new ApiResponse<>(HttpStatus.OK.value(), "Found item updated successfully", item, LocalDateTime.now());
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/status")
    @PreAuthorize("hasAnyRole('ADMIN', 'VOLUNTEER')")
    public ResponseEntity<ApiResponse<FoundItem>> updateFoundItemStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        FoundItem item = foundItemService.updateFoundItemStatus(id, status); // Status conversion handled by service
        ApiResponse<FoundItem> response = new ApiResponse<>(HttpStatus.OK.value(), "Found item status updated successfully", item, LocalDateTime.now());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Void>> deleteFoundItem(@PathVariable Long id) {
        foundItemService.deleteFoundItem(id);
        ApiResponse<Void> response = new ApiResponse<>(HttpStatus.OK.value(), "Found item deleted successfully", null, LocalDateTime.now());
        return ResponseEntity.ok(response);
    }
}