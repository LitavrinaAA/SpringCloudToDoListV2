package ru.litavrina.ToDoListV2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import ru.litavrina.ToDoListV2.aspect.TrackUserActionAspect;


@Configuration
@EnableAspectJAutoProxy
public class AspectConfiguration {

    @Bean
    public TrackUserActionAspect aspect()
    {
        return new TrackUserActionAspect();
    }

}