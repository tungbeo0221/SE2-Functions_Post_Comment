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
@RequestMapping("")
public class CommentController {
  @Autowired
  private CommentRepository commentRepository;

  @Autowired
  private PostRepository postRepository;

  @Autowired
  private UserRepository userRepository;

  // we dont need this anymore
  // Endpoint to view all comments for a post

  // save comment for a post
  @PostMapping("/posts/{id}/comment")
  public String saveComment(@PathVariable(value = "id") Long postId,
      @RequestParam String content,
      Model model) {
    Optional<Post> postOpt = postRepository.findById(postId);
    if (postOpt.isPresent()) {
      Post post = postOpt.get();
      if (content == null || content.trim().isEmpty()) {
        model.addAttribute("error", "Comment content cannot be empty");
        return "post";
      }
      Comment newComment = new Comment();
      newComment.setPost(post);
      newComment.setContent(content);
      newComment.setCommentDate(LocalDateTime.now());
      newComment.setUserId(1L);
      commentRepository.save(newComment);
      return "redirect:/posts/" + postId;
    } else {
      return "redirect:/posts/" + postId + "?error=Post not found";
    }
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
