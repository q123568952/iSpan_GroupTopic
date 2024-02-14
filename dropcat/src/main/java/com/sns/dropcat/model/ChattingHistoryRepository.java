package com.sns.dropcat.model;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChattingHistoryRepository extends JpaRepository<ChattingHistory, Integer> {
    // 這裡可以添加更多自定義的數據庫操作方法
}

