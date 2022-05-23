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
public enum SexEnum {


    MAN(0, "男"),
    WOMAN(1, "女");

    private final Integer key;

    private final String value;

    public static String getNameByCode(Integer key) {
        if (null == key) {
            return null;
        }

        for (SexEnum e : SexEnum.values()) {
            if (Objects.equals(e.getKey(), key)) {
                return e.getValue();
            }
        }
        return null;
    }
}
