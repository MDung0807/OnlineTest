package com.iotstar.onlinetest.repositories;

import com.iotstar.onlinetest.models.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestDAO extends JpaRepository<Test, Long> {

}
