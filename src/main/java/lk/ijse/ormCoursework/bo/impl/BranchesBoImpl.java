package lk.ijse.ormCoursework.bo.impl;

import lk.ijse.ormCoursework.bo.custom.BranchesBo;
import lk.ijse.ormCoursework.dao.custom.BranchesDao;
import lk.ijse.ormCoursework.dao.impl.BranchesDaoImpl;
import lk.ijse.ormCoursework.dto.BranchesDto;
import lk.ijse.ormCoursework.entity.Branches;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class BranchesBoImpl implements BranchesBo {
    BranchesDao branchesDao=new BranchesDaoImpl();

    @Override
    public boolean saveBranches(BranchesDto dto) {
        return branchesDao.save(new Branches(
                dto.getBrId(),
                dto.getBName(),
                dto.getLocation(),
                dto.getBStatus()

        ));
    }

    @Override
    public boolean updateBranches(BranchesDto dto) {
        return branchesDao.update(new Branches(
                dto.getBrId(),
                dto.getBName(),
                dto.getLocation(),
                dto.getBStatus()

        ));
    }

    @Override
    public boolean deleteBranch(Branches dto) {
        return branchesDao.delete(new Branches(
                dto.getBrId(),
                dto.getBrName(),
                dto.getLocation(),
                dto.getBrStatus()


        ));
    }

    @Override
    public BranchesDto searchBranch(String id) {
        Branches dto  = branchesDao.search(id);

        return new BranchesDto(
                dto.getBrId(),
                dto.getBrName(),
                dto.getLocation(),
                dto.getBrStatus()

        );
    }

    @Override
    public ArrayList<BranchesDto> getAllBranches() {
        ArrayList<BranchesDto> brList = new ArrayList<>();

        brList.addAll(branchesDao.getAll().stream().map(br -> {
            return new BranchesDto(
                    br.getBrId(),
                    br.getBrName(),
                    br.getLocation(),
                    br.getBrStatus()
            );
        }).collect(Collectors.toList()));

        return brList;
    }
}
