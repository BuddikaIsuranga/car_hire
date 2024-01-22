package lk.ijse.service.impl;

import lk.ijse.Entity.CatergoryEntity;
import lk.ijse.dao.DaoFactory.DaoType;
import lk.ijse.dao.DaoFactory.FactoryDao;
import lk.ijse.dao.custom.CatergoryDao;
import lk.ijse.dto.CategoryDto;
import lk.ijse.service.custom.CatergoryService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CatergoryServiceImpl implements CatergoryService {
   private CatergoryDao catergoryDao= FactoryDao.getDao(DaoType.CATERGORY);
    @Override
    public void save(CategoryDto categoryDto) {
        CatergoryEntity categoryEntity = new CatergoryEntity();
        categoryEntity.setCategoryid(categoryDto.getCategoryid());
        categoryEntity.setCategoryname(categoryDto.getCategoryname());



        catergoryDao.save(categoryEntity);
    }

    @Override
    public void update(CategoryDto categoryDto) {
        CatergoryEntity categoryEntity = new CatergoryEntity();
        categoryEntity.setCategoryid(categoryDto.getCategoryid());
        categoryEntity.setCategoryname(categoryDto.getCategoryname());


        catergoryDao.update(categoryEntity);
    }

    @Override
    public CategoryDto searchCategory(String categoryId) {
        CatergoryEntity categoryEntity = catergoryDao.search(categoryId);
        if (categoryEntity != null) {
            return new CategoryDto(
                    categoryEntity.getCategoryid(),
                    categoryEntity.getCategoryname()
            );
        }
        return null;

    }

    @Override
    public void delete(CategoryDto categoryDto) {
        CatergoryEntity search = catergoryDao.search(categoryDto.getCategoryid());
        catergoryDao.delete(search);
    }

    @Override
    public List<CategoryDto> getAll() {
        return catergoryDao.getAll()
                .stream().map(
                        element -> new CategoryDto(
                                element.getCategoryid(),
                                element.getCategoryname())
                ).collect(Collectors.toList());
    }
}
