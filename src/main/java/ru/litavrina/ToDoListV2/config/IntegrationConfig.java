package ru.litavrina.ToDoListV2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.GenericTransformer;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.messaging.MessageChannel;

import java.io.File;
//1.в пом-файле три зависимости дял интеграции
//2. (тут) класс конфигурации (бины в контейнер)
//3. в сервисах FileGateway
//4. добавить в контролер: поле сервиса FileGateway и при создании
@Configuration
public class IntegrationConfig {
    @Bean
    public MessageChannel textInputChanel() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel fileWriterChanel() {
        return new DirectChannel();
    }

    @Bean
    @Transformer(inputChannel = "textInputChanel", outputChannel = "fileWriterChanel")
    public GenericTransformer<String, String> mainTransformer() {
        return text -> {
            text = text.toUpperCase();
            return text;
        };
    }
// сохраняет на жетский файл
    @Bean
    @ServiceActivator(inputChannel = "fileWriterChanel")
    public FileWritingMessageHandler outHandler () {
        FileWritingMessageHandler handler =
                new FileWritingMessageHandler(new File("C:/Users/Ananas/GB/Spring/ToDoListFinal/ToDoListV2"));
        handler.setExpectReply(false);
        handler.setFileExistsMode(FileExistsMode.APPEND);
        handler.setAppendNewLine(true);
        return handler;
    }
}
