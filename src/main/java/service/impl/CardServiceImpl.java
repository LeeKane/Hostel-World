package service.impl;

import bean.Card;
import mapper.CardMapper;
import mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.CardService;

import java.util.Calendar;
import java.util.List;

/**
 * Created by LeeKane on 17/2/20.
 */
public class CardServiceImpl implements CardService {
    @Autowired
    private CardMapper cardMapper;
    @Autowired
    private UserMapper userMapper;
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

    @Override
    public void income(double income, int cardId) {
        cardMapper.income(income,cardId);
    }

    @Override
    public void cancel(int cardId) {
        cardMapper.cancel(cardId);
        userMapper.deleteUser(cardId);
    }

    @Override
    public void charge(double charge, int cardId) {
        cardMapper.charge(charge,cardId);
    }

    @Override
    public void change(double socerC, int cardId) {
        double charge=socerC/100;
        cardMapper.charge(charge,cardId);
        cardMapper.changeScore(socerC,cardId);
    }

    @Override
    public void checkData() {
        List<Card> cards=cardMapper.getAllCard();
        Calendar calendar = Calendar.getInstance();
        java.sql.Date nowDate = new java.sql.Date(calendar.getTime().getTime());
        for(int i=0;i<cards.size();i++)
        {
            if(cards.get(i).getActivatiedOverDate().before(nowDate))
            {
                    cardMapper.cardActivitiedCancel(cards.get(i).getCardId());
            }
            else if(cards.get(i).getStopDate().before(nowDate))
            {
                cardMapper.cancel(cards.get(i).getCardId());
            }
        }
    }

}
