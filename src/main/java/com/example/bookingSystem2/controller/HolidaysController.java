package com.example.bookingSystem2.resource;

import com.example.bookingSystem2.model.Holiday;
import com.example.bookingSystem2.model.HolidayService;
import com.example.bookingSystem2.model.Users;
import com.example.bookingSystem2.repository.HolidaysRepository;
import com.example.bookingSystem2.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Controller
public class HolidaysController {

    private HolidayService holidayService;

    public HolidaysController(HolidayService holidayService) {

        this.holidayService = holidayService;
    }

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private HolidaysRepository holidaysRepository;

    @GetMapping("/calendar")
    public String prepareHoliday(Model model) {
        Holiday dateRange = new Holiday();
        dateRange.setHolidayFrom(new Date());
        dateRange.setHolidayTo(new Date());


        model.addAttribute("holiday", dateRange);
        return "calendar";
    }

    @PostMapping("/calendar_processing")
    public ResponseEntity<Object> createHoliday(/*@RequestBody*/ Holiday holiday) {
        Users user = usersRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        holiday.setUser(user);
        return  holidayService.addHoliday(holiday);
    }

    @GetMapping("/bookings")
    public String showListHolidays(Model model) {
        Users user = usersRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        List<Holiday> listHolidays = holidaysRepository.findByUserId(user.getId());
        model.addAttribute("listHolidays", listHolidays);
        return "bookings";
    }

    @RequestMapping(value = "/delete_holiday", method = RequestMethod.POST)
    private ResponseEntity<Object> deleteHoliday(@RequestParam String id){
        int intId = Integer.parseInt(id);
        return holidayService.deleteHoliday(intId);
    }
}
