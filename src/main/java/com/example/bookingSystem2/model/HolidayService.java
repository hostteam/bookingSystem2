package com.example.bookingSystem2.model;

import com.example.bookingSystem2.repository.HolidaysRepository;
import com.example.bookingSystem2.repository.UsersRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class HolidayService {

    private UsersRepository usersRepository;
    private HolidaysRepository holidaysRepository;

    public HolidayService(UsersRepository usersRepository, HolidaysRepository holidaysRepository) {
        this.usersRepository = usersRepository;
        this.holidaysRepository = holidaysRepository;
    }

    //Create new holiday
    @Transactional
    public ResponseEntity<Object> addHoliday(Holiday holiday) {
        Holiday newHoliday = new Holiday();
        newHoliday.setHolidayFrom(holiday.getHolidayFrom());
        newHoliday.setHolidayTo(holiday.getHolidayTo());
        newHoliday.setComment(holiday.getComment());
        newHoliday.setUser(holiday.getUser());
        Holiday savedHoliday = holidaysRepository.save(newHoliday);
        if (holidaysRepository.findById(savedHoliday.getId()).isPresent())
            return ResponseEntity.accepted().body("We added your holiday");
        else
            return ResponseEntity.unprocessableEntity().body("Something went wrong try one more time");
    }

    //Delete a specified holiday by given the id

    @Transactional
    public ResponseEntity<Object> deleteHoliday(Integer id) {
        if (holidaysRepository.findById(id).isPresent()) {
            holidaysRepository.deleteById(id);
            if (holidaysRepository.findById(id).isPresent()) {
                return ResponseEntity.unprocessableEntity().body("Something went wrong try one more time");
            } else return ResponseEntity.ok().body("Successfully deleted holiday");
        } else
            return ResponseEntity.unprocessableEntity().body("No Records Found");
    }

    //all booked dates from current date
    public Set<String> allHolidaysFromCurDate() {
        Set<String> disabledDates = new HashSet<>();
        List<Holiday> allHolidays = holidaysRepository.findAll();
        Date curDate = new Date();

        //adding all dates after curDate to Set
        for (Holiday range : allHolidays) {
            Date start = range.getHolidayFrom();
            Date end = range.getHolidayTo();

            Calendar cal = Calendar.getInstance();
            cal.setTime(end);
            cal.add(Calendar.DATE, 1);
            end = cal.getTime();

            if (end.compareTo(curDate) >= 0) {
                if (start.compareTo(curDate) < 0)
                    start = curDate;

                Calendar calendar = new GregorianCalendar();
                calendar.setTime(start);

                while (calendar.getTime().before(end)) {
                    Date result = calendar.getTime();
                    DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                    String formattedDate = formatter.format(result);
                    disabledDates.add(formattedDate);

                    calendar.add(Calendar.DATE, 1);
                }
            }
        }
        return disabledDates;
    }
}
