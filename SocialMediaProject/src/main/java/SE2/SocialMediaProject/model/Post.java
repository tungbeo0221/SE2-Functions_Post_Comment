package SE2.SocialMediaProject.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "posts")
public class Post {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column
  private long id;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @Column
  private String status;

  @Column
  private String imageUrl;

  @Column(columnDefinition = "TEXT")
  private String content;

  @Column
  private String title;

  public void setId(long id) {
    this.id = id;
  }

  public long getId() {
    return id;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public User User() {
    return user;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getStatus() {
    return status;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getContent() {
    return content;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getTitle() {
    return title;
  }
}
