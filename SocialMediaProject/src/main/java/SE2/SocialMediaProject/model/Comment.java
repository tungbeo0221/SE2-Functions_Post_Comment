package SE2.SocialMediaProject.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
public class Comment {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "commentId", nullable = false)
  private Long commentId;

  @Column(name = "userId", nullable = false)
  private Long userId;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "id", nullable = false)
  private Post post;

  @Column(nullable = false)
  private String content;

  @Column(nullable = false)
  private LocalDateTime commentDate;

  public Comment(Long commentId, Long userId, Post post, String content, LocalDateTime commentDate) {
    this.commentId = commentId;
    this.userId = userId;
    this.post = post;
    this.content = content;
    this.commentDate = commentDate;
  }

  public Long getCommentId() {
    return commentId;
  }

  public void setCommentId(Long commentId) {
    this.commentId = commentId;
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
