package com.iccd.storage.core.constants;

import org.apache.commons.lang3.StringUtils;

public interface StorageConstants {

    //公共分割字符串
    String COMMON_SEPARATOR = "__,__";

    String EMPTY_STR = StringUtils.EMPTY;

    //点常量
    String POINT_STR = ".";

    //下划线
    String SLASH = "/";

    Long ZERO_LONG = 0L;

    Integer ZERO_INT = 0;

    Integer ONE_INT = 1;
    Integer TWO_INT = 2;
    Integer MINUS_ONE_INT = -1;
    String TRUE_STR = "true";
    String FALSE_STR = "false";

    String BASE_COMPONENT_SCAN_PATH = "com.iccd.storage";

    /**
     * 公用加密字符串
     */
    String COMMON_ENCRYPT_STR = "****";
}
