package com.hrd.repository;

import com.hrd.comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "comment",path = "comment")
public interface CommentRepository extends PagingAndSortingRepository<comment,Integer> {

    @Query("select c.id,c.content,c.content,c.icon,c.type from comment c where c.type =:type order by c.id desc")
    Page<comment> findComment(@Param("type") int type,Pageable page);
}
