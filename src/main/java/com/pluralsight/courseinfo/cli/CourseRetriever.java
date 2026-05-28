package com.pluralsight.courseinfo.cli;

import com.pluralsight.courseinfo.cli.service.CourseRetrievalService;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;


public class CourseRetriever {
    private static final Logger LOG = LoggerFactory.getLogger(CourseRetriever.class);

    public static void main(String[] args) {
        LOG.info("Hello, welcome to the Course Retriever!");
        if (args == null || args.length < 2) {
            LOG.warn("Usage: CourseRetriever <courseId> <authorId>");
            return;
        }

        try {
            retrieveCourse(args[0], args[1]);
        } catch (Exception e) {
            LOG.error("An error occurred while retrieving the course information", e);
        }
    }

    private static void retrieveCourse(String courseId, String authorId) {
        LOG.info("Retrieving information for course ID: '{}'", courseId);
        LOG.info("Retrieving author for course ID: '{}'", authorId);
        CourseRetrievalService courseRetrievalService = new CourseRetrievalService();

        String coursesToStore = courseRetrievalService.getCoursesFor(authorId);
        LOG.info("Courses retrieved for author ID '{}': {}", authorId, coursesToStore);
    }
}
