package com.fly.util;

import cn.hutool.core.lang.UUID;
import com.fly.exception.BizException;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Fly
 * @since 2022/5/21 10:44
 */
@Slf4j
public class UuidUtil {

    public static String getUuid() {
        try {
            String uuid = UUID.randomUUID().toString().replace("-", "");
            log.info("uuid:{}", uuid);
            return uuid;
        } catch (Exception e) {
            throw new BizException(e.getMessage());
        }
    }

}
