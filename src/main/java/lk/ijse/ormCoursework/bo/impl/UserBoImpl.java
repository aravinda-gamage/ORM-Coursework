package lk.ijse.ormCoursework.bo.impl;

import lk.ijse.ormCoursework.bo.custom.UserBo;
import lk.ijse.ormCoursework.dao.custom.UserDao;
import lk.ijse.ormCoursework.dao.impl.UserDaoImpl;
import lk.ijse.ormCoursework.dto.UserDto;
import lk.ijse.ormCoursework.entity.User;
import lk.ijse.ormCoursework.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UserBoImpl implements UserBo {
    private Session session;
    // UserDAO userDAO=(UserDAO) DAOFactory.getDaoFactory ().getDAO (DAOFactory.DAOTypes.USER);
    UserDao userDao=new UserDaoImpl();
    @Override
    public boolean saveUser(UserDto dto) {
        session= SessionFactoryConfig.getInstance ().getSession ();
        Transaction transaction=session.beginTransaction ();

        try{
            userDao.setSession (session);
            String id=userDao.save (new User(
                    dto.getUserId (),
                    dto.getUserName (),
                    dto.getPassword ()));
            transaction.commit ();
            session.close ();
            if (id!=null){
                return true;
            }
        }catch (Exception e){
            transaction.rollback ();
        }
        return false;
    }

    @Override
    public UserDto getUser(String id) throws Exception {
        session=SessionFactoryConfig.getInstance ().getSession ();
        Transaction transaction=session.beginTransaction ();

        userDao.setSession (session);
        User u=userDao.getObject (id);
        session.close ();
        return new UserDto (
                u.getUserId (),
                u.getUserName (),
                u.getPassword ()
        );
    }

    @Override
    public boolean updateUser(UserDto dto) {
        session=SessionFactoryConfig.getInstance ().getSession ();
        Transaction transaction=session.beginTransaction ();

        try {
            userDao.setSession (session);
            userDao.update (new User (
                    dto.getUserId (),
                    dto.getUserName (),
                    dto.getPassword ()
            ));

            transaction.commit ();
            session.close ();
            return true;
        }catch (Exception e){
            transaction.rollback ();;
        }
        return false;
    }

    @Override
    public List<UserDto> loadAll() {
        session=SessionFactoryConfig.getInstance ().getSession ();
        Transaction transaction=session.beginTransaction ();

        userDao.setSession (session);
        List<User>list= userDao.loadAll ();
        List<UserDto>userList= new ArrayList<>();

        for (User u:list) {
            userList.add(
                    new UserDto (
                            u.getUserId (),
                            u.getUserName (),
                            u.getPassword ()
                    ));
        }

        return userList;

    }
}
