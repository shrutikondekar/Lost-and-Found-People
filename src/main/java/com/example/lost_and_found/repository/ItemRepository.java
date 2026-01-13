package com.example.lost_and_found.repository;

import com.example.lost_and_found.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {}
