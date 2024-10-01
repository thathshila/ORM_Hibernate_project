package lk.ijse.Dao.Custom;

import lk.ijse.Dao.CrudDao;
import lk.ijse.Entity.Item;

import java.util.List;

public interface ItemDao extends CrudDao<Item> {
    List<Long> getItemCode();
}
