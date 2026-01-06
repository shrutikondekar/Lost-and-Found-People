package com.example.lost_and_found.repository;

import com.example.lost_and_found.entity.LostItem;
import com.example.lost_and_found.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface LostItemRepository extends JpaRepository<LostItem, Long> {
    List<LostItem> findByStatus(String status);
    List<LostItem> findByReportedBy(User user);
    
    @Query("SELECT l FROM LostItem l WHERE " +
           "LOWER(l.personName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(l.description) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(l.lastSeenLocation) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<LostItem> searchByKeyword(@Param("keyword") String keyword);
    
    List<LostItem> findByLostDateBetween(LocalDate startDate, LocalDate endDate);
}