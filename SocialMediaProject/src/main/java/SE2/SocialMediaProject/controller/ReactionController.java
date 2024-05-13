package SE2.SocialMediaProject.controller;

import SE2.SocialMediaProject.repository.ReactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ReactionController {
    @Autowired
    ReactionRepository reactionRepository;
}
