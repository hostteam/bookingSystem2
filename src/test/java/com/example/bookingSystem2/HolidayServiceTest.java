package com.example.bookingSystem2;

import com.example.bookingSystem2.model.Holiday;
import com.example.bookingSystem2.model.HolidayService;
import com.example.bookingSystem2.model.Users;
import com.example.bookingSystem2.repository.HolidaysRepository;
import com.example.bookingSystem2.repository.UsersRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest(properties = { "spring.config.name=store-tests-h2",
        "spring.datasource.url=jdbc:h2:mem:db-store-tests;DB_CLOSE_DELAY=-1;MODE=MSSQLServer" })
@ActiveProfiles("test")
public class HolidayServiceTest {

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    HolidaysRepository holidaysRepository;

    @Autowired
    HolidayService holidayService;

    @Test
    public void userForTest() {
        Users user = new Users();
        user.setName("Valera");
        user.setSurname("Kumar");
        user.setEmail("erwewr@kj.lk");
        user.setPassword("$2a$10$itDC.E6ZlnRAcvTueCGfWulyg9OGrK5Za4LpR46z4bSSzlY06LbYG");
        user.setGroupId(2);
        usersRepository.save(user);
        assertNotNull(usersRepository.findByEmail("erwewr@kj.lk"));
    }

    @Test
    public void addHolidays() {

        Holiday newHoliday = new Holiday();
        newHoliday.setUser(usersRepository.findByEmail("erwewr@kj.lk"));

        Date newDate = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(newDate);
        cal.add(Calendar.DATE, 2);

        newHoliday.setHolidayFrom(cal.getTime());

        cal.add(Calendar.DATE, 4);
        newHoliday.setHolidayTo(cal.getTime());

        newHoliday.setComment("checking insertion");
        holidayService.addHoliday(newHoliday);
        assertEquals(holidaysRepository.findAll().get(0).getComment(), newHoliday.getComment());
        System.out.println(holidaysRepository.findAll().get(0).getHolidayFrom() + " " +
                holidaysRepository.findAll().get(0).getHolidayTo() + " " +
                holidaysRepository.findAll().get(0).getId());
    }
    @Test
    public void checkBusyDates() {
        Set<Date> allBusydates = holidayService.allHolidaysFromCurDate();
        assertEquals(allBusydates.size(), 5);
        allBusydates.stream().forEach(a -> System.out.println(a));
    }
}
