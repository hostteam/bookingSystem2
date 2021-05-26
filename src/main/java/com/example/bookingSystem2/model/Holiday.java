package com.example.bookingSystem2.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "holidays")
public class Holiday {
    private @Id @GeneratedValue Integer id;
    private @DateTimeFormat(pattern = "dd/MM/yyyy") Date holidayFrom;
    private @DateTimeFormat(pattern = "dd/MM/yyyy") Date holidayTo;
    private @NotNull String comment;
    @ManyToOne(fetch = FetchType.LAZY)
    private Users user;
}
