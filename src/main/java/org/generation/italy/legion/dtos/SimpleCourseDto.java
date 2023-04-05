package org.generation.italy.legion.dtos;

import org.generation.italy.legion.model.entities.Course;

import java.util.stream.StreamSupport;

public class SimpleCourseDto {
    private long id;
    private String title;
    private String description;
    private String program;

    public SimpleCourseDto(long id, String title, String description, String program) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.program = program;
    }

    public static SimpleCourseDto fromEntity(Course c) {
        return new SimpleCourseDto(c.getId(), c.getTitle(), c.getDescription(), c.getProgram());
    }

    public static Iterable<SimpleCourseDto> fromEntityIterable(Iterable<Course> iC) {
        return StreamSupport.stream(iC.spliterator(), false)
                .map(SimpleCourseDto::fromEntity)
                .toList();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }
}
