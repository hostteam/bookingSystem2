package com.example.bookingSystem2;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.bookingSystem2.model.Users;
import com.example.bookingSystem2.repository.UsersRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(true)
public class UsersRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UsersRepository repo;

    @Test
    public void testCreateUser() {
        Users user = new Users();
        user.setName("Valera");
        user.setSurname("Kumar");
        user.setEmail("erwewr@kj.lk");
        user.setPassword("$2a$10$itDC.E6ZlnRAcvTueCGfWulyg9OGrK5Za4LpR46z4bSSzlY06LbYG");
        user.setGroupId(2);

        Users savedUser = repo.save(user);

        Users existUser = entityManager.find(Users.class, savedUser.getId());

        assertThat(user.getEmail()).isEqualTo(existUser.getEmail());
    }
}

