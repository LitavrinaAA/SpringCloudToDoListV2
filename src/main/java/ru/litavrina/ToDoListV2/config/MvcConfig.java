package ru.litavrina.ToDoListV2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class MvcConfig implements WebMvcConfigurer {
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/tasks").setViewName("tasks");
//        registry.addViewController("/tasksold").setViewName("tasks_old");
        registry.addViewController("/hello").setViewName("hello");
    }
}
