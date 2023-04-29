package com.iotstar.onlinetest.repositories;

import com.iotstar.onlinetest.models.HisItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HisItemDAO extends JpaRepository<HisItem, Long> {
}
