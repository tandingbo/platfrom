package com.tanbobo.platfrom.core.dao;

import com.tanbobo.platfrom.core.dto.SystemSerialNumberDTO;

/**
 * Created by tanbobo on 2016/6/28.
 */
public interface SerialNumberDAO {
    SystemSerialNumberDTO find(SystemSerialNumberDTO systemSerialNumberDTO);
}
