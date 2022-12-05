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

    @ApiOperation("Создание. Валидации описания никакой. Кстати номер группы может быть и миллионный)")
    @PostMapping("/new")
    public boolean create(@RequestParam Integer subGroupNumber, @RequestParam String description){
        return subGroupService.create(new SubGroup(0L, subGroupNumber, description));
    }

    @ApiOperation("Поиск по айди")
    @GetMapping("/findBy/id")
    public SubGroup getById(@RequestParam Long id){
        return subGroupService.getById(id);
    }

    @ApiOperation("Обновление. Меняй че хочешь.")
    @PutMapping("/update")
    public boolean update(@RequestBody SubGroup subGroup){
        return subGroupService.update(subGroup);
    }

    @ApiOperation("Удаляем мягко")
    @DeleteMapping("/softDelete")
    public boolean softDelete(@RequestParam Long id){
        return subGroupService.softDelete(id);
    }

}
