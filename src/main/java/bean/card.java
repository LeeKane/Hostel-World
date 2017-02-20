package bean;

import java.sql.Date;

/**
 * Created by LeeKane on 17/2/20.
 */
public class Card {
    private int cardId;
    private int activatied;
    private java.sql.Date activatiedOverDate;
    private java.sql.Date stopDate;
    private double balance;
    private double consumption;
    private int level;
    private int scocer;
    private int accountId;

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public int getActivatied() {
        return activatied;
    }

    public void setActivatied(int activatied) {
        this.activatied = activatied;
    }

    public Date getActivatiedOverDate() {
        return activatiedOverDate;
    }

    public void setActivatiedOverDate(Date activatiedOverDate) {
        this.activatiedOverDate = activatiedOverDate;
    }

    public Date getStopDate() {
        return stopDate;
    }

    public void setStopDate(Date stopDate) {
        this.stopDate = stopDate;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getConsumption() {
        return consumption;
    }

    public void setConsumption(double consumption) {
        this.consumption = consumption;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getScocer() {
        return scocer;
    }

    public void setScocer(int scocer) {
        this.scocer = scocer;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
}
