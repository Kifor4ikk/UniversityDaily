package ru.kifor4ik.controller.subGroup;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import ru.kifor4ik.service.subGroup.SubGroupService;
import ru.kifor4ik.subgroup.SubGroup;

@RestController
@RequestMapping("api/v3/subGroup")
public class SubGroupController {

    private final SubGroupService subGroupService;

    public SubGroupController(SubGroupService subGroupService) {
        this.subGroupService = subGroupService;
    }

    @ApiOperation("��������. ��������� �������� �������. ������ ����� ������ ����� ���� � ����������)")
    @PostMapping("/new")
    public boolean create(int subGroupNumber, String description){
        return subGroupService.create(new SubGroup(0L, subGroupNumber, description));
    }

    @ApiOperation("����� �� ����")
    @GetMapping("/findBy/id")
    public SubGroup getById(Long id){
        return subGroupService.getById(id);
    }

    @ApiOperation("����������. ����� �� ������.")
    @PutMapping("/update")
    public boolean update(SubGroup subGroup){
        return subGroupService.update(subGroup);
    }

    @ApiOperation("������� �����")
    @DeleteMapping("/softDelete")
    public boolean softDelete(Long id){
        return subGroupService.softDelete(id);
    }

}
