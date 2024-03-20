package com.rod.api.user;


import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@ToString(exclude = {"id"})
public class User {

    private Long id;
    private String username;
    private String password;
    private String name;
    private String phoneNumber;
    private String addressId;
    private String job;
    private String weight;
    private String height;


    @Builder(builderMethodName = "bulder")
    public User(Long id, String username, String password, String name, String phoneNumber, String job, String weight, String height) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.job = job;
        this.weight = weight;
        this.height = height;
    }
}