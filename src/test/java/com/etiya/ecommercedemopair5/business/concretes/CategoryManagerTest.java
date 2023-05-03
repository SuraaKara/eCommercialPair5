package com.etiya.ecommercedemopair5.business.concretes;

import com.etiya.ecommercedemopair5.business.dtos.responses.category.ListCategoryResponse;
import com.etiya.ecommercedemopair5.core.utils.mapping.ModelMapperManager;
import com.etiya.ecommercedemopair5.core.utils.mapping.ModelMapperService;
import com.etiya.ecommercedemopair5.core.utils.result.DataResult;
import com.etiya.ecommercedemopair5.repositories.CategoryDao;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CategoryManagerTest {

    //Mockito
    //CategoryManager'ın instance'ı lazım. Bu classı oluşturabilmek için bağımlılıklarını almalıyız.
    CategoryDao categoryDao;
    ModelMapperService modelMapperService;
    MessageSource messageSource;

    CategoryManager categoryManager;

    @BeforeEach
    void setUp(){
        //Her test öncesi çalıştırılacak alan.
        categoryDao = mock(CategoryDao.class);
        modelMapperService = new ModelMapperManager(new ModelMapper());
        messageSource = getBundleMessageSource();
        categoryManager = new CategoryManager(categoryDao, modelMapperService, messageSource);
    }

    ResourceBundleMessageSource getBundleMessageSource(){
        //İlgili dosyanın mesaj kaynağının neresi olacağını belirtiriz. API, Veritabanı, Dosya
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        return messageSource;
    }

    @AfterEach
    void tearDown(){
        //Her test sonrası çalıştırılacak alan.
    }

    @Test
    void add(){

    }

    @Test
    void getAll(){
        //Mock
        List<ListCategoryResponse> fakeData = new ArrayList<>();
        fakeData.add(new ListCategoryResponse(1,"Giyim",null));
        fakeData.add(new ListCategoryResponse(2,"Televizyon",null));

        when(categoryDao.findAll()).thenReturn(fakeData);       //categoryDao içerisindeki findAll metodu çağrıldığında bu dao yu mockla. Ve içerisine fakedata al.
        DataResult<List<ListCategoryResponse>> listDataResult = categoryManager.getAll();

    }
}
