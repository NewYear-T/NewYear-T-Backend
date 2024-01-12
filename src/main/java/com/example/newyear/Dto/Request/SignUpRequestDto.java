package com.example.newyear.Dto.Request;

import com.example.newyear.Entity.enums.Gender;
import com.example.newyear.Entity.enums.Local;
import com.example.newyear.Entity.enums.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequestDto {
    private String userName; // 이름

    private String loginId; // 아이디

    private String password; // 패스워드

    private String gender; // 성별

    private String local; // 행정구역 ( 지역 )

    private int age;
}
