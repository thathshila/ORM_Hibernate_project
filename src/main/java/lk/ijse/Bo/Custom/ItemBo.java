package lk.ijse.Bo.Custom;

import lk.ijse.Bo.SuperBo;
import lk.ijse.Dto.ItemDto;
import lk.ijse.Entity.Item;

import java.util.List;

public interface ItemBo extends SuperBo {
    public boolean save(ItemDto itemDto);
    public boolean delete(Long code);
    public boolean update(ItemDto itemDto);
    public ItemDto findById(Long code);
    public List<Item> getItems();

}
