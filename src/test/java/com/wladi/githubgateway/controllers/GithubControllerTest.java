package com.wladi.githubgateway.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import com.wladi.githubgateway.clients.GithubClient;
import com.wladi.githubgateway.models.Repository;
import com.wladi.githubgateway.models.User;

@ExtendWith(MockitoExtension.class)
public class GithubControllerTest {

    private static final String USERNAME = "wladi-silva";
    private static final String REPOSITORY_NAME = "github-gateway-back";

    @Mock
    private GithubClient githubClient;

    @InjectMocks
    private GithubController githubController;

    @Test
    public void givenUserNameWhenGetUserThenValidResponse() {
        User user = new User(USERNAME);

        when(githubClient.getUser(user.getLogin())).thenReturn(user);

        ResponseEntity<User> userResponse = githubController.getUser(USERNAME);
        assertEquals(USERNAME, userResponse.getBody().getLogin());
    }

    @Test
    public void givenUserNameWhenGetRepositoriesThenValidResponse() {
        List<Repository> repositories = List.of(new Repository(REPOSITORY_NAME));

        when(githubClient.getUserRepositories(USERNAME)).thenReturn(repositories);

        ResponseEntity<List<Repository>> repositoriesResponse = githubController.getUserRepositories(USERNAME);
        assertTrue(repositoriesResponse.getBody().size() > 0);
    }

}
