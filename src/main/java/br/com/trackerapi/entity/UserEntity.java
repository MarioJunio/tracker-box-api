package br.com.trackerapi.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Document("users")
public class UserEntity implements Serializable {

    @Id
    private String id;

    @Indexed
    private String username;

    @Indexed
    private String password;

    private String fullname;
    private String color;
}
