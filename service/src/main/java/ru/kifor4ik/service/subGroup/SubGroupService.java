package ru.kifor4ik.service.subGroup;

import org.springframework.stereotype.Service;
import ru.kifor4ik.exception.CreateException;
import ru.kifor4ik.exception.GetException;
import ru.kifor4ik.exception.SoftDeleteException;
import ru.kifor4ik.group.Course;
import ru.kifor4ik.repository.subgroup.SubGroupRepository;
import ru.kifor4ik.subgroup.SubGroup;

@Service
public class SubGroupService {

    private final SubGroupRepository subGroupRepository;

    public SubGroupService(SubGroupRepository subGroupRepository) {
        this.subGroupRepository = subGroupRepository;
    }

    public boolean create(SubGroup subGroup){
        try {
            subGroupRepository.create(subGroup);
            return true;
        } catch (Exception e){
            throw new CreateException("Create subgroup exception", e.getLocalizedMessage(), "C00001");
        }
    }

    public SubGroup getById(Long id){
        try{
            return subGroupRepository.getById(id);
        } catch (Exception e){
            throw new GetException("Get subgroup exception", e.getLocalizedMessage(), "G00001");
        }
    }

    public boolean update(SubGroup subGroup){
        try{
            return subGroupRepository.update(subGroup);
        } catch (Exception e){
            throw new GetException("Update subgroup exception", e.getLocalizedMessage(), "U00001");
        }
    }

    public boolean softDelete(Long id){
        try {
            subGroupRepository.softDelete(id);
            return true;
        } catch (Exception e){
            throw new SoftDeleteException("SoftDelete subgroup exception", e.getLocalizedMessage(), "SD00001");
        }
    }
}
