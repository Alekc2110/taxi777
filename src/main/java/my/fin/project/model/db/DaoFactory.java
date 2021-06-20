package my.fin.project.model.db;

import my.fin.project.model.db.dao.interfaces.*;

public abstract class DaoFactory {

    private static DaoFactory daoFactory;

    public abstract CarDao createCarDao();
    public abstract UserDao createUserDao();
    public abstract OrderDao createOrderDao();

    public static DaoFactory getInstance() {
        if (daoFactory == null) {
            synchronized (DaoFactory.class) {
                if (daoFactory == null) {
                    daoFactory = new JDBCDaoFactory();
                }
            }
        }
        return daoFactory;
    }
}
