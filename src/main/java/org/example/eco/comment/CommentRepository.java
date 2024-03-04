package org.example.eco.comment;

import org.example.eco.comment.entity.Comment;
import org.example.eco.common.repository.GenericRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CommentRepository extends GenericRepository<Comment, UUID> {
}
