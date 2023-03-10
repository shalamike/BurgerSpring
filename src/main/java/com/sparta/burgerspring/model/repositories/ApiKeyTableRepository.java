package com.sparta.burgerspring.model.repositories;

import com.sparta.burgerspring.model.entities.ApiKeyTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiKeyTableRepository extends JpaRepository<ApiKeyTable, String> {
}