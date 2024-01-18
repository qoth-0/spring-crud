package com.example.crud.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor // Entity 사용 시 기본생성자 필수
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private String password;
    @CreationTimestamp
    private LocalDateTime create_time;
    @UpdateTimestamp
    private  LocalDateTime update_time;

//    회원가입 시 사용
    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

//    회원수정 시 사용
    public void userUpdate(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
