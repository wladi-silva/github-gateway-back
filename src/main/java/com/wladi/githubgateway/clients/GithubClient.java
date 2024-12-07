package com.wladi.githubgateway.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.wladi.githubgateway.configurations.FeignConfiguration;
import com.wladi.githubgateway.models.Repository;
import com.wladi.githubgateway.models.User;

@FeignClient(name = "githubClient", url = "https://api.github.com", configuration = FeignConfiguration.class)
public interface GithubClient {

    @GetMapping("/users/{username}")
    User getUser(@PathVariable String username);

    @GetMapping("/users/{username}/repos")
    List<Repository> getUserRepositories(@PathVariable String username);

}
