package com.casumo.hometest.videorentalstore.films.repo;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.casumo.hometest.videorentalstore.films.FilmTestHelper;
import com.casumo.hometest.videorentalstore.films.domain.FilmType;
import com.casumo.hometest.videorentalstore.films.domain.Price;


/**
 * FilmRepositoryIntegrationTest class - Test FilmRepository class.
 */
@DataJpaTest()
class FilmTypeRepositoryIntegrationTest
{
    @Autowired
    private FilmTypeRepository filmTypeRepository;

    @Autowired
    private TestEntityManager entityManager;

    private Price basicPrice;

    @BeforeEach
    void setUp()
    {
        basicPrice = FilmTestHelper.generatePrice(FilmTestHelper.MOCK_BASIC_PRICE_NAME, FilmTestHelper.MOCK_BASIC_PRICE_VALUE);
        entityManager.persist(basicPrice);
        entityManager.flush();
    }

    // findById - ok
    @Test
    void givenExistentFilmTypeId_whenFindById_thenReturnExistentFilmType()
    {
        // given
        final FilmType mockFilmType = FilmTestHelper.generateFilmType(FilmTestHelper.MOCK_OLD_NAME, basicPrice);
        entityManager.persistAndFlush(mockFilmType);
        final Long id = (Long) entityManager.getId(mockFilmType);

        // when
        final Optional<FilmType> result = filmTypeRepository.findById(id);

        // then
        assertNotNull(result);
        final FilmType film = result.get();
        assertThat(film.getId(), is(id));
        assertThat(film.getName(), is(mockFilmType.getName()));
        assertThat(film.getPrice(), is(mockFilmType.getPrice()));
    }

    // findById - nok (id not found)
    @Test
    void givenNotExistentFilmTypeId_whenFindById_thenThrowSpecificException()
    {
        // given
        final FilmType mockFilmType = FilmTestHelper.generateFilmType(FilmTestHelper.MOCK_OLD_NAME, basicPrice);
        entityManager.persistAndFlush(mockFilmType);

        // when
        final Optional<FilmType> result = filmTypeRepository.findById(FilmTestHelper.MOCK_UNKNOWN_ID);

        // + then
        assertFalse(result.isPresent());
    }
}
