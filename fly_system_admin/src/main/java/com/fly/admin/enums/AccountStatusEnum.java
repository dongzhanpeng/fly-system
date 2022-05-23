package com.fly.admin.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * @author Fly
 * @since 2022/5/17 11:04
 */
@AllArgsConstructor
@Getter
public enum AccountStatusEnum {


    LOGOUT(0, "登出"),
    LOGIN(1, "登录"),
    CANCEL(2, "注销"),
    lock(3, "锁定");

    private final Integer key;

    private final String value;

    public static String getNameByCode(Integer key) {
        if (null == key) {
            return null;
        }

        for (AccountStatusEnum e : AccountStatusEnum.values()) {
            if (Objects.equals(e.getKey(), key)) {
                return e.getValue();
            }
        }
        return null;
    }
}
