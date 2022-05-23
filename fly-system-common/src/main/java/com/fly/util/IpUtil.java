package com.fly.util;

import com.fly.exception.BizException;
import lombok.extern.slf4j.Slf4j;

import java.net.Inet4Address;

/**
 * @author Fly
 * @since 2022/5/20 17:20
 */
@Slf4j
public class IpUtil {

    /**
     * 获取本地ip地址
     *
     * @return
     */
    public static String getLocalIpAddress() throws Exception {

        try {
            String ipAddress = Inet4Address.getLocalHost().getHostAddress();
            log.info("ipAddress:{}", ipAddress);
            return ipAddress;
        } catch (Exception e) {
            throw new BizException(e.getMessage());
        }
    }
}
