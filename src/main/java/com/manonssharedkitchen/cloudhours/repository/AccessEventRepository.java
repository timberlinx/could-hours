package com.manonssharedkitchen.cloudhours.repository;

import com.manonssharedkitchen.cloudhours.entity.AccessEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessEventRepository extends JpaRepository<AccessEvent, Integer> {

    // Find users by name
//    List<AccessEvent> findByAccessEventId(String accessEventId);
}