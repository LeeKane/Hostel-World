package service;

import bean.Card;

/**
 * Created by LeeKane on 17/2/20.
 */
public interface CardService {
    public Card getCard(int cardId);
    public void cardActivited(int cardId);
    public void income(double income,int cardId);
}
