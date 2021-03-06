package service;

import bean.Card;

/**
 * Created by LeeKane on 17/2/20.
 */
public interface CardService {
    public Card getCard(int cardId);
    public void cardActivited(int cardId);
    public void income(double income,int cardId);
    public void cancel(int cardId);
    public void charge(double charge,int cardId);
    public void change(double socerC,int cardId);
    public void checkData();
}
