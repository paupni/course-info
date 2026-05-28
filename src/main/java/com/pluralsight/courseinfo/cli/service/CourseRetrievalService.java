package com.pluralsight.courseinfo.cli.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class CourseRetrievalService {
    private static final String PS_URI = "https://app.pluralsight.com/profile/data/author/%s/all-content";

    private static final HttpClient CLIENT = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    public String getCoursesFor(String authorId) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(PS_URI.formatted(authorId)))
                .timeout(Duration.ofSeconds(10))
                .header("Accept", "application/json")
                .GET()
                .build();

        try {
            HttpResponse<String> apiResponse = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
            int status = apiResponse.statusCode();
            if (status >= 200 && status < 300) {
                return apiResponse.body();
            }
            throw new RuntimeException("Failed to retrieve courses: HTTP " + status + " - " + apiResponse.body());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Request interrupted while retrieving courses for author: " + authorId, e);
        } catch (IOException e) {
            throw new RuntimeException("I/O error while retrieving courses for author: " + authorId, e);
        }
    }
}
