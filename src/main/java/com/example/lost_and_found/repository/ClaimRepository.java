package com.example.lost_and_found.repository;

import com.example.lost_and_found.entity.Claim;
import com.example.lost_and_found.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ClaimRepository extends JpaRepository<Claim, Long> {
    List<Claim> findByUser(User user);
    List<Claim> findByStatus(String status);
    List<Claim> findByLostItemId(Long lostItemId);
    List<Claim> findByFoundItemId(Long foundItemId);
}