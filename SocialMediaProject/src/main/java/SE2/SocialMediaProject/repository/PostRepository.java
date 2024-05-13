package SE2.SocialMediaProject.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import SE2.SocialMediaProject.model.Post;

public interface PostRepository extends PagingAndSortingRepository<Post, Long>, CrudRepository<Post, Long> {

}
