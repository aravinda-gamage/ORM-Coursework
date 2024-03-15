package lk.ijse.ormCoursework.bo.custom;

import lk.ijse.ormCoursework.dto.BranchesDto;
import lk.ijse.ormCoursework.entity.Branches;

import java.util.ArrayList;

public interface BranchesBo {
    boolean saveBranches(BranchesDto dto) ;
    boolean updateBranches(BranchesDto dto);
    boolean deleteBranch(Branches dto) ;
    BranchesDto searchBranch(String id);
    ArrayList<BranchesDto> getAllBranches();
}
