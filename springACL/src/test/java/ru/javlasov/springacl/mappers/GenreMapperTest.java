package ru.javlasov.springacl.mappers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.javlasov.springacl.dto.GenreDto;
import ru.javlasov.springacl.model.Genre;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class GenreMapperTest {

    @Autowired
    private GenreMapper mapperUnderTest;

    @Test
    @DisplayName("Test convert genre -> genreDto")
    void entityToDto() {
        //given
        var model = new Genre(1L, "Genre");

        //when
        var dto = mapperUnderTest.entityToDto(model);

        //then
        Assertions.assertNotNull(dto);
        Assertions.assertEquals(model.getId(), dto.getId());
        Assertions.assertEquals(model.getName(), dto.getName());
    }

    @Test
    @DisplayName("Test convert genreDto -> genre")
    void dtoToModel() {
        //given
        var dto = new GenreDto(1L, "Genre");

        //when
        var model = mapperUnderTest.dtoToModel(dto);

        //then
        Assertions.assertNotNull(dto);
        Assertions.assertEquals(model.getId(), dto.getId());
        Assertions.assertEquals(model.getName(), dto.getName());
    }

    @Test
    @DisplayName("Test convert genre list -> genreDto list")
    void testEntityToDto() {
        //given
        List<Genre> modelList = new ArrayList<>();
        var model = new Genre(1L, "Genre");
        modelList.add(model);

        //when
        var dtoList = mapperUnderTest.entityToDto(modelList);

        //then
        Assertions.assertNotNull(dtoList);
        Assertions.assertEquals(modelList.size(), dtoList.size());
    }
}