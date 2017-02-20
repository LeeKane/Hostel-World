package mapper;

import bean.Card;
import bean.user;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by LeeKane on 17/2/20.
 */
public interface CardMapper {
    @Select("SELECT * FROM card WHERE cardId = #{cardId}")
    Card getCard(@Param("cardId")int cardId);
}
