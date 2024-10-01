package lk.ijse.Bo.Custom.Impl;

import lk.ijse.Bo.Custom.ItemBo;
import lk.ijse.Dao.Custom.ItemDao;
import lk.ijse.Dao.DaoFactory;
import lk.ijse.Dto.ItemDto;
import lk.ijse.Entity.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemBoImpl implements ItemBo {
    ItemDao itemDao = (ItemDao) DaoFactory.getDaoFactory().getDaoType(DaoFactory.DaoType.ITEM);

    @Override
    public boolean save(ItemDto itemDto) {
        Item item = new Item(1L, itemDto.getName(), itemDto.getPrice(), itemDto.getQty());
        return itemDao.save(item);
    }

    @Override
    public boolean delete(Long code) {
        return itemDao.delete(code);
    }

    @Override
    public boolean update(Long code, ItemDto itemDto) {
        Item item = new Item(code, itemDto.getName(), itemDto.getPrice(), itemDto.getQty());
        return itemDao.update(item);
    }

    @Override
    public ItemDto findById(Long code) {
//        Item item = itemDao.findById(code);
//        ItemDto itemDto = new ItemDto();
//        itemDto.setCode(item.getCode());
//        itemDto.setName(item.getName());
//        itemDto.setPrice(item.getPrice());
//        itemDto.setQty(item.getQty());
//        return itemDto;
        return null;
    }

    @Override
    public List<Item> getItems() {
       List<Item> itemList = new ArrayList<>();
       List<Item> allItems = itemDao.findAll();
       for (Item item : allItems) {
           itemList.add(new Item(item.getCode(),item.getName(), item.getPrice(), item.getQty()));
       }
       return itemList;
   }
}
