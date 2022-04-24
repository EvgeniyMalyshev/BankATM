package com.atm.data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CURRENCY")
public class Currency {
    @Id
    private Long currencyId;
    
}
