package com.piotrjankowski.polsl.indoor_plants_wiki.adapter;

import com.piotrjankowski.polsl.indoor_plants_wiki.model.Comment;
import com.piotrjankowski.polsl.indoor_plants_wiki.model.CommentRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SqlCommentRepository extends CommentRepository, JpaRepository<Comment, Integer> {

}
