package com.es.searchviewwithrecyclerview_kotlin

class Course(private var courseName: String, private var courseDescription: String) {
    // creating getter and setter methods.
    fun getCourseName(): String {
        return courseName
    }

    fun setCourseName(courseName: String) {
        this.courseName = courseName
    }

    fun getCourseDescription(): String {
        return courseDescription
    }

    fun setCourseDescription(courseDescription: String) {
        this.courseDescription = courseDescription
    }
}
