package lk.ijse.ormCoursework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DetailDto {
    private String dId;
    private Date gDate;
    private Date rDate;
    private UserDto user;
    private BookDto book;
}
