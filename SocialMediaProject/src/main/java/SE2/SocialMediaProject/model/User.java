package SE2.SocialMediaProject.model;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "userId")
  private long id;

  @Column
  private String username;

  @Column
  @OneToMany(mappedBy = "user")
  private List<Post> posts;

  public void setId(Long id) {
    this.id = id;
  }

  public long getId() {
    return id;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getUsername() {
    return username;
  }

  public void setPosts(List<Post> posts) {
    this.posts = posts;
  }

  public List<Post> getPosts() {
    return posts;
  }
}