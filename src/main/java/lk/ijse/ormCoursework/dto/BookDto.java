package lk.ijse.ormCoursework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookDto {
    private String bId;
    private String title;
    private String author;
    private String genre;
    private String status;
}
