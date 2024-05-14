package SE2.SocialMediaProject.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
public class Comment {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="commentId", nullable = false)
  private Long commentId;

  @Column(name = "userId", nullable = false)
  private Long userId;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "postId", nullable = false)
  private Post post;

  @Column(nullable = false)
  private String content;

  @Column(nullable = false)
  private LocalDateTime commentDate;

  public Long getId() {
    return commentId;
  }

  public void setId(Long id) {
    this.commentId = id;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Post getPost() {
    return post;
  }

  public void setPost(Post post) {
    this.post = post;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public LocalDateTime getCommentDate() {
    return commentDate;
  }

  public void setCommentDate(LocalDateTime commentDate) {
    this.commentDate = commentDate;
  }
}
