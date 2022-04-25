package com.atm.data;

import org.springframework.context.annotation.Lazy;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "BANKNOTE")
@Lazy
public class Banknote {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long banknotesId;

    @ManyToOne
    private Currency currency;
    private String nominal;
    private String amount;
}
