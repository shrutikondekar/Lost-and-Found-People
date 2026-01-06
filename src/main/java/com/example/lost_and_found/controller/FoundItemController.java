package com.example.lost_and_found.controller;

import com.example.lost_and_found.dto.ApiResponse;
import com.example.lost_and_found.dto.FoundItemRequest;
import com.example.lost_and_found.entity.FoundItem;
import com.example.lost_and_found.service.FoundItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/found-items")
@CrossOrigin(origins = "*")
public class FoundItemController {

    @Autowired
    private FoundItemService foundItemService;

    // Public endpoints (no authentication required)
    @GetMapping("/public/all")
    public ResponseEntity<ApiResponse> getAllFoundItemsPublic() {
        List<FoundItem> items = foundItemService.getAllFoundItems();
        return ResponseEntity.ok(new ApiResponse(true, "Found items retrieved successfully", items));
    }

    @GetMapping("/public/{id}")
    public ResponseEntity<ApiResponse> getFoundItemByIdPublic(@PathVariable Long id) {
        FoundItem item = foundItemService.getFoundItemById(id);
        return ResponseEntity.ok(new ApiResponse(true, "Found item retrieved successfully", item));
    }

    @GetMapping("/public/search")
    public ResponseEntity<ApiResponse> searchFoundItemsPublic(@RequestParam String keyword) {
        List<FoundItem> items = foundItemService.searchFoundItems(keyword);
        return ResponseEntity.ok(new ApiResponse(true, "Search completed successfully", items));
    }

    @GetMapping("/public/status/{status}")
    public ResponseEntity<ApiResponse> getFoundItemsByStatusPublic(@PathVariable String status) {
        List<FoundItem> items = foundItemService.getFoundItemsByStatus(status);
        return ResponseEntity.ok(new ApiResponse(true, "Found items retrieved successfully", items));
    }

    // Authenticated endpoints
    @GetMapping
    public ResponseEntity<ApiResponse> getAllFoundItems() {
        List<FoundItem> items = foundItemService.getAllFoundItems();
        return ResponseEntity.ok(new ApiResponse(true, "Found items retrieved successfully", items));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getFoundItemById(@PathVariable Long id) {
        FoundItem item = foundItemService.getFoundItemById(id);
        return ResponseEntity.ok(new ApiResponse(true, "Found item retrieved successfully", item));
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse> searchFoundItems(@RequestParam String keyword) {
        List<FoundItem> items = foundItemService.searchFoundItems(keyword);
        return ResponseEntity.ok(new ApiResponse(true, "Search completed successfully", items));
    }

    @GetMapping("/date-range")
    public ResponseEntity<ApiResponse> getFoundItemsByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<FoundItem> items = foundItemService.getFoundItemsByDateRange(startDate, endDate);
        return ResponseEntity.ok(new ApiResponse(true, "Found items retrieved successfully", items));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<ApiResponse> getFoundItemsByStatus(@PathVariable String status) {
        List<FoundItem> items = foundItemService.getFoundItemsByStatus(status);
        return ResponseEntity.ok(new ApiResponse(true, "Found items retrieved successfully", items));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'VOLUNTEER')")
    public ResponseEntity<ApiResponse> createFoundItem(@RequestBody FoundItemRequest request) {
        FoundItem item = foundItemService.createFoundItem(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse(true, "Found item created successfully", item));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'VOLUNTEER')")
    public ResponseEntity<ApiResponse> updateFoundItem(
            @PathVariable Long id,
            @RequestBody FoundItemRequest request) {
        FoundItem item = foundItemService.updateFoundItem(id, request);
        return ResponseEntity.ok(new ApiResponse(true, "Found item updated successfully", item));
    }

    @PutMapping("/{id}/status")
    @PreAuthorize("hasAnyRole('ADMIN', 'VOLUNTEER')")
    public ResponseEntity<ApiResponse> updateFoundItemStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        FoundItem item = foundItemService.updateFoundItemStatus(id, status);
        return ResponseEntity.ok(new ApiResponse(true, "Found item status updated successfully", item));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> deleteFoundItem(@PathVariable Long id) {
        foundItemService.deleteFoundItem(id);
        return ResponseEntity.ok(new ApiResponse(true, "Found item deleted successfully"));
    }
}