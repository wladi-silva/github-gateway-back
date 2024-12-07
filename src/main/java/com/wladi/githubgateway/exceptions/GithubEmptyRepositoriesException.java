package com.wladi.githubgateway.exceptions;

public class GithubEmptyRepositoriesException extends RuntimeException {

    public GithubEmptyRepositoriesException() {
        super("Not found repositories in Github");
    }

}
