package com.example.lost_and_found.repository;

import com.example.lost_and_found.entity.FoundItem;
import com.example.lost_and_found.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface FoundItemRepository extends JpaRepository<FoundItem, Long> {
    List<FoundItem> findByStatus(String status);
    List<FoundItem> findByReportedBy(User user);
    
    @Query("SELECT f FROM FoundItem f WHERE " +
           "LOWER(f.personName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(f.description) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(f.location) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<FoundItem> searchByKeyword(@Param("keyword") String keyword);
    
    List<FoundItem> findByFoundDateBetween(LocalDate startDate, LocalDate endDate);
}