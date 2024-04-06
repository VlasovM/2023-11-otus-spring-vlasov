package ru.javlasov.springacl.mappers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.javlasov.springacl.dto.AuthorDto;
import ru.javlasov.springacl.model.Author;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class AuthorMapperTest {

    @Autowired
    private AuthorMapper mapperUnderTest;

    @Test
    @DisplayName("Test convert author -> authorDto")
    void entityToDto() {
        //given
        var model = new Author(1L, "Author");

        //when
        var dto = mapperUnderTest.entityToDto(model);

        //then
        Assertions.assertNotNull(dto);
        Assertions.assertEquals(model.getId(), dto.getId());
        Assertions.assertEquals(model.getFullName(), dto.getFullName());
    }

    @Test
    @DisplayName("Test convert authorDto -> author")
    void dtoToModel() {
        //given
        var dto = new AuthorDto(1L, "Author");

        //when
        var model = mapperUnderTest.dtoToModel(dto);

        //then
        Assertions.assertNotNull(dto);
        Assertions.assertEquals(model.getId(), dto.getId());
        Assertions.assertEquals(model.getFullName(), dto.getFullName());
    }

    @Test
    @DisplayName("Test convert model list -> dto list")
    void testEntityToDto() {
        //given
        List<Author> modelList = new ArrayList<>();
        var model = new Author(1L, "Author");
        modelList.add(model);

        //when
        var dtoList = mapperUnderTest.entityToDtoList(modelList);

        //then
        Assertions.assertNotNull(dtoList);
        Assertions.assertEquals(modelList.size(), dtoList.size());
    }

}