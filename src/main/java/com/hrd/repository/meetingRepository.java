package com.hrd.repository;


import com.hrd.com.hrd.Entity.meeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface meetingRepository extends JpaRepository<meeting,Integer> {
    @Query("select c.id,c.meeting_id,playName,login_password,meeting_stream,meeting_count,conference_picture_url from meeting c where c.id =:id order by c.id desc")
    Optional<meeting> findById(@Param("id") Integer id);
}
