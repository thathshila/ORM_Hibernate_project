package lk.ijse.Bo.Custom;

import lk.ijse.Bo.SuperBo;
import lk.ijse.Dto.ItemDto;

import java.util.List;

public interface ItemBo extends SuperBo {
    public boolean save(ItemDto itemDto);
    public boolean delete(ItemDto itemDto);
    public boolean update(ItemDto itemDto);
    public ItemDto findById(Long code);
    public List<ItemDto> getItems();

}
