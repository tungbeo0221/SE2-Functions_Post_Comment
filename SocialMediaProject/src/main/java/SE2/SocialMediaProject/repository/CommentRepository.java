package SE2.SocialMediaProject.repository;

import SE2.SocialMediaProject.model.Comment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CommentRepository extends JpaRepository<Comment, Long> {
  @Query("SELECT c FROM Comment c WHERE c.post.id = ?1")
  List<Comment> findCommentsByPostId(Long id);
}
