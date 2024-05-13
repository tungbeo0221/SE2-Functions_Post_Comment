package SE2.SocialMediaProject.repository;

import org.springframework.data.repository.CrudRepository;

import SE2.SocialMediaProject.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

}
