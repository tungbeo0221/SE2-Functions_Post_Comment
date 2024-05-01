package SE2.SocialMediaProject.repository;

import SE2.SocialMediaProject.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
