package SE2.SocialMediaProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import SE2.SocialMediaProject.repository.CommentRepository;

@Controller
public class CommentController {
    @Autowired
    CommentRepository commentRepository;
}
