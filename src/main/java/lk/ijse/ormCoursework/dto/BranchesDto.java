package lk.ijse.ormCoursework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BranchesDto {
    private String brId;
    private String bName;
    private String location;
    private String bStatus;
}
