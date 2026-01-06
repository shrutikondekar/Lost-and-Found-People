package com.example.lost_and_found.controller;

import com.example.lost_and_found.dto.ApiResponse;
import com.example.lost_and_found.dto.LostItemRequest;
import com.example.lost_and_found.entity.LostItem;
import com.example.lost_and_found.service.LostItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/lost-items")
@CrossOrigin(origins = "*")
public class LostItemController {

    @Autowired
    private LostItemService lostItemService;

    // Public endpoints (no authentication required)
    @GetMapping("/public/all")
    public ResponseEntity<ApiResponse> getAllLostItemsPublic() {
        List<LostItem> items = lostItemService.getAllLostItems();
        return ResponseEntity.ok(new ApiResponse(true, "Lost items retrieved successfully", items));
    }

    @GetMapping("/public/{id}")
    public ResponseEntity<ApiResponse> getLostItemByIdPublic(@PathVariable Long id) {
        LostItem item = lostItemService.getLostItemById(id);
        return ResponseEntity.ok(new ApiResponse(true, "Lost item retrieved successfully", item));
    }

    @GetMapping("/public/search")
    public ResponseEntity<ApiResponse> searchLostItemsPublic(@RequestParam String keyword) {
        List<LostItem> items = lostItemService.searchLostItems(keyword);
        return ResponseEntity.ok(new ApiResponse(true, "Search completed successfully", items));
    }

    @GetMapping("/public/status/{status}")
    public ResponseEntity<ApiResponse> getLostItemsByStatusPublic(@PathVariable String status) {
        List<LostItem> items = lostItemService.getLostItemsByStatus(status);
        return ResponseEntity.ok(new ApiResponse(true, "Lost items retrieved successfully", items));
    }

    // Authenticated endpoints
    @GetMapping
    public ResponseEntity<ApiResponse> getAllLostItems() {
        List<LostItem> items = lostItemService.getAllLostItems();
        return ResponseEntity.ok(new ApiResponse(true, "Lost items retrieved successfully", items));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getLostItemById(@PathVariable Long id) {
        LostItem item = lostItemService.getLostItemById(id);
        return ResponseEntity.ok(new ApiResponse(true, "Lost item retrieved successfully", item));
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse> searchLostItems(@RequestParam String keyword) {
        List<LostItem> items = lostItemService.searchLostItems(keyword);
        return ResponseEntity.ok(new ApiResponse(true, "Search completed successfully", items));
    }

    @GetMapping("/date-range")
    public ResponseEntity<ApiResponse> getLostItemsByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<LostItem> items = lostItemService.getLostItemsByDateRange(startDate, endDate);
        return ResponseEntity.ok(new ApiResponse(true, "Lost items retrieved successfully", items));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<ApiResponse> getLostItemsByStatus(@PathVariable String status) {
        List<LostItem> items = lostItemService.getLostItemsByStatus(status);
        return ResponseEntity.ok(new ApiResponse(true, "Lost items retrieved successfully", items));
    }

    @GetMapping("/my-reports")
    public ResponseEntity<ApiResponse> getMyLostItems() {
        List<LostItem> items = lostItemService.getMyLostItems();
        return ResponseEntity.ok(new ApiResponse(true, "Your lost items retrieved successfully", items));
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createLostItem(@RequestBody LostItemRequest request) {
        LostItem item = lostItemService.createLostItem(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse(true, "Lost item reported successfully", item));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateLostItem(
            @PathVariable Long id,
            @RequestBody LostItemRequest request) {
        LostItem item = lostItemService.updateLostItem(id, request);
        return ResponseEntity.ok(new ApiResponse(true, "Lost item updated successfully", item));
    }

    @PutMapping("/{id}/status")
    @PreAuthorize("hasAnyRole('ADMIN', 'VOLUNTEER')")
    public ResponseEntity<ApiResponse> updateLostItemStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        LostItem item = lostItemService.updateLostItemStatus(id, status);
        return ResponseEntity.ok(new ApiResponse(true, "Lost item status updated successfully", item));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> deleteLostItem(@PathVariable Long id) {
        lostItemService.deleteLostItem(id);
        return ResponseEntity.ok(new ApiResponse(true, "Lost item deleted successfully"));
    }
}