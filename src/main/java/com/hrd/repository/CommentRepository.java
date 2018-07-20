package com.hrd.repository;

import com.hrd.com.hrd.Entity.comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "comment",path = "comment")
public interface CommentRepository extends PagingAndSortingRepository<comment,Integer> {

    @Query("select c.id,c.content,c.icon,c.type,c.username,c.comment_date from comment c where c.type =:type order by c.id desc")
    Page<comment> findComment(@Param("type") String type,Pageable page);

    //@Query("select c.id,c.content,c.icon,c.type,c.username,c.comment_date from comment c where c.type =:type order by c.id desc")
    //List<comment> findByType(@Param("type") String type);

    @Transactional
    void deleteByType(@Param("type") String type);

}

