package SE2.SocialMediaProject.controller;

import SE2.SocialMediaProject.model.Post;
import SE2.SocialMediaProject.model.Comment;
import SE2.SocialMediaProject.repository.PostRepository;
import SE2.SocialMediaProject.repository.CommentRepository;
import SE2.SocialMediaProject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/post/{id}/comments")
public class CommentController {
  @Autowired
  private CommentRepository commentRepository;

  @Autowired
  private PostRepository postRepository;

   @Autowired
   private UserRepository userRepository;
   
  // Endpoint to view all comments for a post
  @RequestMapping("/api/post/{id}/comments")
  public String viewAllCommentsForPost(@PathVariable(value = "id") Long postId, Model model) {
    Optional<Post> postOpt = postRepository.findById(postId);
    if (postOpt.isPresent()) {
      Post post = postOpt.get();
      List<Comment> comments = post.getComments(); // Assuming getComments() is a method in Post entity
      model.addAttribute("post", post);
      model.addAttribute("comments", comments);
    } else {
      model.addAttribute("error", "Post not found");
    }
    return "postComments"; // Thymeleaf template name
  }


  // create a new comment for a post
  @PostMapping
  public ResponseEntity<Comment> createComment(@PathVariable Long postId, @RequestBody Comment comment) {
    Post post = postRepository.findById(postId)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
            "Post not found with id " + postId));
    comment.setPost(post);
    comment.setCommentDate(LocalDateTime.now());
    Comment createdComment = commentRepository.save(comment);
    return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
  }

  // update an existing comment in a post
  @PutMapping
  public ResponseEntity<Comment> updateComment(@PathVariable Long postId,
      @PathVariable Long commentId,
      @RequestBody Comment updatedComment) {
    Comment comment = commentRepository.findById(commentId)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
            "Comment not found with id " + commentId));
    comment.setContent(updatedComment.getContent());
    comment.setCommentDate(LocalDateTime.now());
    Comment savedComment = commentRepository.save(comment);
    return new ResponseEntity<>(savedComment, HttpStatus.OK);
  }

  // deleting comment for a post
  @DeleteMapping("/{commentId}")
  public ResponseEntity<Void> deleteComment(@PathVariable Long postId, @PathVariable Long commentId) {
    Post post = postRepository.findById(postId)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
            "Post not found with id " + postId));
    Comment comment = commentRepository.findById(commentId)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
            "Comment not found with id " + commentId));
    if (!comment.getPost().equals(post)) {
      throw new IllegalArgumentException("Comment does not belong to the specified post");
    }
    post.getComments().remove(comment);
    postRepository.save(post);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
