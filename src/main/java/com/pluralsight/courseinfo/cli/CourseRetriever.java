package com.pluralsight.courseinfo.cli;

public class CourseRetriever {

    public static void main(String[] args) {
        System.out.println("Hello, welcome to the Course Retriever!");
        if(args.length == 0) {
            System.out.println("Please provide a course ID as an argument.");
            return;
        }

        try {
            retrieveCourse(args[0]);
        } catch (Exception e) {
            System.out.println("An error occurred while retrieving the course information: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void retrieveCourse(String courseId) {
        System.out.println("Retrieving information for course ID: " + courseId);
    }
}
