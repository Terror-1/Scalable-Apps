package com.sessionservice.sessionservice.repository;

import com.sessionservice.sessionservice.entity.Session;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface SessionRepository extends CassandraRepository<Session, String> {
    // Custom repository method to delete session by userId
    @Query("DELETE FROM session WHERE user_id = ?0")
    void deleteByUserId(String userId);
}
