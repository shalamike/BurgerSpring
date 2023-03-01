package com.sparta.burgerspring.model.repositories;

import com.sparta.burgerspring.model.entities.Title;
import com.sparta.burgerspring.model.entities.TitleId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TitleRepository extends JpaRepository<Title, TitleId> {
}