package SE2.SocialMediaProject.model;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "commentId", nullable = false)
    private int commentId;
    private String content;
    private int userId;
    private int postId;
    private Date commentDate;

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getUserId() {    // have to get userId from User model later
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    public int getPostId(){
       //TODO: get postId from Post model
        return 0;
    }

    public void setPostId(int postId){
        this.postId = postId;
    }
}
