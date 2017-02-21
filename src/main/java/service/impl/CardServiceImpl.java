package service.impl;

import bean.Card;
import mapper.CardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import service.CardService;

import java.util.Calendar;

/**
 * Created by LeeKane on 17/2/20.
 */
public class CardServiceImpl implements CardService {
    @Autowired
    private CardMapper cardMapper;
    @Override
    public Card getCard(int cardId) {
        return cardMapper.getCard(cardId);
    }

    @Override
    public void cardActivited(int cardId) {
        Calendar calendar = Calendar.getInstance();
        java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());
        calendar.setTime(startDate);
        calendar.add(Calendar.YEAR, 1);
        java.sql.Date activatiedOverDate=new java.sql.Date(calendar.getTime().getTime());
        cardMapper.cardActivitied(activatiedOverDate,cardId);
    }

}
