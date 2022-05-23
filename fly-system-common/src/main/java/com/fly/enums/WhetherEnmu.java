package com.fly.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * @author Fly
 * @since 2022/5/17 11:04
 */
@AllArgsConstructor
@Getter
public enum WhetherEnmu {


    NO(0, "否"),
    YES(1, "是");

    private final Integer key;

    private final String value;

    public static String getNameByCode(Integer key) {
        if (null == key) {
            return null;
        }

        for (WhetherEnmu e : WhetherEnmu.values()) {
            if (Objects.equals(e.getKey(), key)) {
                return e.getValue();
            }
        }
        return null;
    }
}
