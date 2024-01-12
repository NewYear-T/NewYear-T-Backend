package com.example.newyear.Entity.enums;

import lombok.Getter;

@Getter
public enum Local {

    SEOUL("서울"), BUSAN("부산"), DAEGU("대구"),
    GWANGJU("광주"),INCHEON("인천"),DAEJEON("대전"),
    ULSAN("울산"),GYEONGGI("경기"),GANGWON("강원"),
    CHUNGNAM("충청남도"), CHUNBUK("충북"),JEONNAM("전라남도"),
    JEONBUK("전라북도"),GYEONGNAM("경상남도"),GYEONGBUK("경상북도"),
    JEJU("제주도");

    String description;

    Local(String description){this.description = description;}

    public static Local findByDescription(String description) {
        for (Local local : values()) {
            if (local.getDescription().equals(description)) {
                return local;
            }
        }
        return null; // 혹은 적절한 예외 처리
    }


}
