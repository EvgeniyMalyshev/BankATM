package com.atm.data;

import org.springframework.context.annotation.Lazy;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "CURRENCY")
@Lazy
public class Currency {

    public Currency(String currencyName) {
        this.currencyName = currencyName;
    }

    public Currency() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long currencyId;
    private String currencyName;

    @OneToMany
    private Set<Banknote> amountOfBanknotes;

    public Long getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Long currencyId) {
        this.currencyId = currencyId;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public Set<Banknote> getAmountOfBanknotes() {
        return amountOfBanknotes;
    }

    public void setAmountOfBanknotes(Set<Banknote> amountOfBanknotes) {
        this.amountOfBanknotes = amountOfBanknotes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Currency currency = (Currency) o;
        return currencyId.equals(currency.currencyId) &&
                currencyName.equals(currency.currencyName) &&
                amountOfBanknotes.equals(currency.amountOfBanknotes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currencyId, currencyName);
    }
}
