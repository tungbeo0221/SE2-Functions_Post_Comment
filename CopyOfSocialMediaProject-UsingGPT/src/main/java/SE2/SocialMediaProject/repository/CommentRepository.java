package SE2.SocialMediaProject.repository;

import java.lang.Exception;
import java.time.LocalDateTime;
import java.util.Optional;

import SE2.SocialMediaProject.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
