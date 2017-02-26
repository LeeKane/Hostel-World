package mapper;

import bean.Card;
import bean.user;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * Created by LeeKane on 17/2/20.
 */
public interface CardMapper {
    @Select("SELECT * FROM card WHERE cardId = #{cardId}")
    Card getCard(@Param("cardId")int cardId);
    @Update("update card set activatied=1,activatiedOverDate=#{activatiedOverDate} where cardId=#{cardId};")
    void cardActivitied(@Param("activatiedOverDate")java.sql.Date activatiedOverDate,@Param("cardId")int cardId);
    @Update("update card set balance=0,balance=balance+#{income} where cardId=#{cardId};")
    void income(@Param("income")double income,@Param("cardId")int cardId);
    @Delete("delete from card WHERE cardId = #{cardId}")
    void cancel(@Param("cardId")int cardId);

}
