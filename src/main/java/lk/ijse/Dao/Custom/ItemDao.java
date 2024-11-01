package lk.ijse.Dao.Custom;

import lk.ijse.Dao.CrudDao;
import lk.ijse.Entity.Item;
import lk.ijse.Entity.Tm.OrderTm;
import org.hibernate.Session;

import java.util.List;

public interface ItemDao extends CrudDao<Item> {
    List<Long> getItemCode();
    Item getObject(String value);
    boolean update(Session session, Item object);

    Item getItemById(Long value);
    boolean updateQty(Long itemId, int qty);
}



