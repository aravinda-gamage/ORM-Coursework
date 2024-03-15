package lk.ijse.ormCoursework.bo.custom;

import lk.ijse.ormCoursework.dto.UserDto;

import java.util.List;

public interface UserBo {
    boolean saveUser(UserDto dto);
    UserDto getUser(String id) throws Exception;
    boolean updateUser(UserDto dto);
    List<UserDto> loadAll();
}
