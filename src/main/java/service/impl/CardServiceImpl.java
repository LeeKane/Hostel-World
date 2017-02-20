package service.impl;

import bean.Card;
import mapper.CardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import service.CardService;

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
}
