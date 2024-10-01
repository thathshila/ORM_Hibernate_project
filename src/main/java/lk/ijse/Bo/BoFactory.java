package lk.ijse.Bo;

import lk.ijse.Bo.Custom.CustomerBo;
import lk.ijse.Bo.Custom.Impl.CustomerBoImpl;
import lk.ijse.Bo.Custom.Impl.ItemBoImpl;
import lk.ijse.Bo.Custom.Impl.OrderBoImpl;

public class BoFactory {
    private static BoFactory boFactory;

    private BoFactory() {
    }

    public enum BoType {
        CUSTOMER,ITEM,ORDER
    }

    public static BoFactory getBoFactory() {
        return boFactory == null ? boFactory = new BoFactory() : boFactory;
    }

    public SuperBo getBoType(BoType boType) {
        switch (boType) {
            case CUSTOMER:
                return new CustomerBoImpl();
            case ITEM:
                return new ItemBoImpl();
                case ORDER:
                    return new OrderBoImpl();
            default:
                return null;
        }
    }
}
