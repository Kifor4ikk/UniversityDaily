package ru.kifor4ik.service.subGroup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kifor4ik.exception.AlreadyExistException;
import ru.kifor4ik.exception.CreateException;
import ru.kifor4ik.exception.GetException;
import ru.kifor4ik.exception.SoftDeleteException;
import ru.kifor4ik.group.Course;
import ru.kifor4ik.repository.subgroup.SubGroupRepository;
import ru.kifor4ik.subgroup.SubGroup;

import java.sql.Date;
import java.time.LocalDate;

@Service
public class SubGroupService {

    private final SubGroupRepository subGroupRepository;

    @Autowired
    public SubGroupService(SubGroupRepository subGroupRepository) {
        this.subGroupRepository = subGroupRepository;
    }

    public boolean create(SubGroup subGroup){
        try {
            if(subGroupRepository.doesExistSame(subGroup) != 0)
                throw new AlreadyExistException("AlreadyExists", "Same subgroup already exists", "SgC10");

            subGroupRepository.create(subGroup, Date.valueOf(LocalDate.now()),"UNDEFINED");

            return true;
        } catch (Exception e){
            throw new CreateException("Create subgroup exception", e.getLocalizedMessage(), "SgC12");
        }
    }

    public SubGroup getById(Long id){
        try{
            return subGroupRepository.getById(id);
        } catch (Exception e){
            throw new GetException("Get subgroup exception", e.getLocalizedMessage(), "SgG10");
        }
    }

    public boolean update(SubGroup subGroup){
        try{
            return subGroupRepository.update(subGroup);
        } catch (Exception e){
            throw new GetException("Update subgroup exception", e.getLocalizedMessage(), "SgU10");
        }
    }

    public boolean softDelete(Long id){
        try {
            subGroupRepository.softDelete(id,Date.valueOf(LocalDate.now()),"UNDEFINED");
            return true;
        } catch (Exception e){
            throw new SoftDeleteException("SoftDelete subgroup exception", e.getLocalizedMessage(), "SgD10");
        }
    }
}
