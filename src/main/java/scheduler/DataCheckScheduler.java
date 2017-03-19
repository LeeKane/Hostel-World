package scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import service.CardService;
import service.userService;

/**
 * Created by LeeKane on 17/3/19.
 */
public class DataCheckScheduler {
    @Autowired
    private CardService cardService;

    @Scheduled(cron = "0 0 3 * * ?")
    public void doCheck() {
        cardService.checkData();
    }
}
