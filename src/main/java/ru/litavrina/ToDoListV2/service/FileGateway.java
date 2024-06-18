package ru.litavrina.ToDoListV2.service;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.file.FileHeaders;
import org.springframework.messaging.handler.annotation.Header;
//1.в пом-файле три зависимости дял интеграции
//2. (тут) класс конфигурации (бины в контейнер)
//3. в сервисах FileGateway
//4. добавить в контролер: поле этого класса и при создании

//что бы интеграция поняла, что это его бин, нужно @MessagingGateway

@MessagingGateway(defaultRequestChannel = "textInputChanel")
public interface FileGateway {
    //@Header - что бы поняла откуда брать имя файла
    public void writeToFile(@Header(FileHeaders.FILENAME) String fileName, String data);
}
