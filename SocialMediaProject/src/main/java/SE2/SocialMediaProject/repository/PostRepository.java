package SE2.SocialMediaProject.repository;


import SE2.SocialMediaProject.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}
