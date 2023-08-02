package penterest.spring.domain.follow.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import penterest.spring.domain.follow.service.FollowService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class FollowController {

    private final FollowService followService;

    @PostMapping("/follow/{toAccount}/{fromAccount}")
    public void addFollow(@PathVariable String toAccount, @PathVariable String fromAccount) throws Exception {
        followService.addFollow(toAccount, fromAccount);
    }

    @PostMapping("/unfollow/{toAccount}/{fromAccount}")
    public void unFollow(@PathVariable String toAccount, @PathVariable String fromAccount) throws Exception {
        followService.unFollow(toAccount, fromAccount);
    }

    @GetMapping("/follower/{account}")
    public ResponseEntity<List<String>> getFollower(@PathVariable String account) throws Exception {
        List<String> followingEmails = followService.getFollowerList(account);
        return ResponseEntity.ok(followingEmails);
    }

    @GetMapping("/following/{account}")
    public ResponseEntity<List<String>> getFollowing(@PathVariable String account) throws Exception {
        List<String> followingEmails = followService.getFollowingList(account);
        return ResponseEntity.ok(followingEmails);
    }

}
