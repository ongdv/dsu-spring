package com.project.dsu.handler.common.user.dao;

import java.util.List;
import java.util.Map;

public interface UserInfoDAO {

	List<Map<String, Object>> selectUserInfo(Map<String, Object> request);

}
