package ru.kifor4ik;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import ru.kifor4ik.repository.group.FacultyRepository;
import ru.kifor4ik.service.lesson.LessonService;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
@EnableSwagger2
@EnableWebMvc
public class Main implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    private final LessonService lessonService;

    public Main(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @Bean
    public InternalResourceViewResolver defaultViewResolver() {
        return new InternalResourceViewResolver();
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(lessonService.getById(1L));
    }
}
