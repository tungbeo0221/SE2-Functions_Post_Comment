package SE2.SocialMediaProject.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import SE2.SocialMediaProject.model.Post;
import SE2.SocialMediaProject.repository.PostRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("/posts")
public class PostController {
  @Autowired
  private final PostRepository postRepository;

  private final int PAGINATIONSIZE = 3;

  public PostController(PostRepository postRepository) {
    this.postRepository = postRepository;
  }

  @GetMapping("")
  public String getPaginatedPosts(
      @RequestParam(value = "page", defaultValue = "0") int page,
      @RequestParam(value = "size", defaultValue = "" + PAGINATIONSIZE) int size,
      Model model) {
    PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));
    Page<Post> postsPage = postRepository.findAll(pageRequest);
    List<Post> posts = postsPage.toList();

    long postCount = postRepository.count();
    int numOfPages = (int) Math.ceil((postCount * 1.0) / PAGINATIONSIZE);
    model.addAttribute("posts", posts);
    model.addAttribute("postCount", postCount);
    model.addAttribute("pageRequested", page);
    model.addAttribute("paginationSize", PAGINATIONSIZE);
    model.addAttribute("numOfPages", numOfPages);
    return "posts";
  }

  @GetMapping("/{id}")
  public String getPostById(@PathVariable long id, Model model) {
    Optional<Post> postOptional = postRepository.findById(id);

    if (postOptional.isPresent()) {
      model.addAttribute("post", postOptional.get());
    } else {
      model.addAttribute("error", "no-post");
    }
    return "post";
  }

  @GetMapping("/add")
  public String add(Model model) {
    model.addAttribute("newPost", new Post());
    return "addPost";
  }

  @PostMapping("/add")
  public String addPost(@RequestBody Post newPost) {
    postRepository.save(newPost);
    return "redirect:/posts";
  }

  @PutMapping("/{id}")
  public String editPost(@RequestBody Post newPost, @PathVariable Long id, Model model) {
    return postRepository.findById(id).map(post -> {
      model.addAttribute("editedPost", post);
      return "editPost";
    }).orElse("redirect:/");
  }

  @DeleteMapping("/{id}")
  public String deletePost(@PathVariable Long id) {
    postRepository.deleteById(id);
    return "redirect:/";
  }
}
