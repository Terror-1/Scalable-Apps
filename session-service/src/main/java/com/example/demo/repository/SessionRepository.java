package com.example.demo.repository;

import com.example.demo.entity.Session;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface SessionRepository extends CassandraRepository<Session, String> {
    // Custom repository method to delete session by userId
    @Query("DELETE FROM session WHERE user_id = ?0")
    void deleteByUserId(String userId);
}
