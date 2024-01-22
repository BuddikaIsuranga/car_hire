package lk.ijse.service.custom;

import lk.ijse.dto.CategoryDto;
import lk.ijse.service.SupperService;

import java.util.List;

public interface CatergoryService extends SupperService {
     void save(CategoryDto categoryDto);

    void update(CategoryDto categoryDto);

    CategoryDto searchCategory(String categoryId);

    void delete(CategoryDto categoryDto);

     List<CategoryDto>getAll();
}
