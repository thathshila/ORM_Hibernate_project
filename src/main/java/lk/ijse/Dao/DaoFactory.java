package lk.ijse.Dao;


import lk.ijse.Dao.Custom.Impl.CustomerDaoImpl;
import lk.ijse.Dao.Custom.Impl.ItemDaoImpl;
import lk.ijse.Dao.Custom.Impl.OrderDaoImpl;

public class DaoFactory {
    private static DaoFactory daoFactory;
    private DaoFactory() {}

    public static DaoFactory getDaoFactory() {
        return daoFactory == null ? daoFactory = new DaoFactory() : daoFactory;
    }

    public enum DaoType{
        CUSTOMER,ITEM,ORDER
    }
    public SuperDao getDaoType(DaoType daoType){
    switch (daoType){
        case  CUSTOMER:
            return new CustomerDaoImpl();
        case  ITEM:
            return new ItemDaoImpl();
            case  ORDER:
                return new OrderDaoImpl();
        default:
            return null;
        }
    }
}
