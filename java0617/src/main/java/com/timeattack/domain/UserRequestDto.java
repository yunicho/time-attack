package com.timeattack.demo.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
public class UserRequestDto {
    private final String email;
    private final String name;
    private final Integer age;
    private final Boolean gender;
}
