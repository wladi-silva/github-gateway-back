package com.wladi.githubgateway.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wladi.githubgateway.clients.GithubClient;
import com.wladi.githubgateway.exceptions.GithubEmptyRepositoriesException;
import com.wladi.githubgateway.models.Repository;
import com.wladi.githubgateway.models.User;

@RestController
@RequestMapping("/api/github")
public class GithubController {

    private GithubClient githubClient;

    public GithubController(GithubClient githubClient) {
        this.githubClient = githubClient;
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> getUser(@PathVariable String username) {
        User user = githubClient.getUser(username);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/{username}/repos")
    public ResponseEntity<List<Repository>> getUserRepositories(@PathVariable String username) {
        List<Repository> repositories = githubClient.getUserRepositories(username);
        if (repositories.isEmpty()) {
            throw new GithubEmptyRepositoriesException();
        }
        return ResponseEntity.ok(repositories);
    }

}
