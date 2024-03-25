package com.rod.api.enums;

public enum Messenger {

    SUCCESS,
    FAIL, // ID, PW 둘 다 없음.
    SQL_ERROR,
    WRONG_PASSWORD // ID는 존재하나 PW가 틀렸음.
    ;
}
