package lk.ijse.ormCoursework.bo.impl;

import lk.ijse.ormCoursework.bo.custom.DetailBo;
import lk.ijse.ormCoursework.dao.custom.BookDao;
import lk.ijse.ormCoursework.dao.custom.DetailDao;
import lk.ijse.ormCoursework.dao.custom.UserDao;
import lk.ijse.ormCoursework.dao.impl.BookDaoImpl;
import lk.ijse.ormCoursework.dao.impl.DetailDaoImpl;
import lk.ijse.ormCoursework.dao.impl.UserDaoImpl;
import lk.ijse.ormCoursework.dto.BookDto;
import lk.ijse.ormCoursework.dto.DetailDto;
import lk.ijse.ormCoursework.dto.UserDto;
import lk.ijse.ormCoursework.entity.Book;
import lk.ijse.ormCoursework.entity.Detail;
import lk.ijse.ormCoursework.entity.User;
import lk.ijse.ormCoursework.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class DetailBoImpl implements DetailBo {
    DetailDao detailDao=new DetailDaoImpl();
    UserDao userDao=new UserDaoImpl();
    BookDao bookDao=new BookDaoImpl();

    private Session session;
    //Student=User
    @Override
    public List<String> getUserIds() {
        try{
            session= SessionFactoryConfig.getInstance ().getSession ();
            userDao.setSession (session);
            return userDao.UserIds();

        }catch (Exception e){
            session.close ();
            return null;
        }
    }

    @Override
    public List<String> getBookIds() {
        try{
            session=SessionFactoryConfig.getInstance ().getSession ();
            bookDao.setSession (session);
            return bookDao.bookIds ();
        }catch (Exception e){
            session.close ();
            return null;
        }
    }

    @Override
    public UserDto getUser(String id) {
        session=SessionFactoryConfig.getInstance ().getSession ();
        Transaction transaction=session.beginTransaction ();

        userDao.setSession (session);
        try {
            User user = userDao.getObject (id);
            session.close ();
            return new UserDto (
                    user.getUserId(),
                    user.getUserName(),
                    user.getPassword()

            );

        } catch (Exception e) {
            e.printStackTrace ();
            transaction.rollback ();
            return null;
        }
    }

    @Override
    public BookDto getBook(String id) {
        session=SessionFactoryConfig.getInstance ().getSession ();
        Transaction transaction=session.beginTransaction ();

        bookDao.setSession (session);
        try {
            Book book=bookDao.getObject (id);
            session.close ();
            return new BookDto (
                    book.getId(),
                    book.getTitle(),
                    book.getAuthor(),
                    book.getGenre(),
                    book.getStatus()
            );

        } catch (Exception e) {
            e.printStackTrace ();
            transaction.rollback ();
            return null;
        }

    }

    @Override
    public DetailDto getDetail(String detailId) {
        session=SessionFactoryConfig.getInstance ().getSession ();
        Transaction transaction=session.beginTransaction ();

        detailDao.setSession (session);
        try {
            Detail det = detailDao.getObject (detailId);
            session.close ();
            return new DetailDto (
                    det.getId(),
                    det.getgDate(),
                    det.getrDate(),
                    new UserDto (
                            det.getUser().getUserId(),
                            det.getUser().getUserName(),
                            det.getUser().getPassword()

                    ),
                    new BookDto (
                            det.getBook().getId(),
                            det.getBook().getTitle(),
                            det.getBook().getAuthor(),
                            det.getBook().getGenre(),
                            det.getBook().getStatus()
                    )
            );
        } catch (Exception e) {
            e.printStackTrace ();
            transaction.rollback ();
            return null;
        }
    }

    public boolean updateBook(BookDto dto) {
        session=SessionFactoryConfig.getInstance ().getSession ();
        Transaction transaction=session.beginTransaction ();

        try {
            bookDao.setSession (session);
            bookDao.update (new Book (
                    dto.getBId(),
                    dto.getTitle(),
                    dto.getAuthor(),
                    dto.getGenre(),
                    dto.getStatus()
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
    public List<DetailDto> loadAllDetail() {
        return null;
    }

    @Override
    public boolean saveDetail(DetailDto det) {
        session=SessionFactoryConfig.getInstance ().getSession ();
        Transaction transaction=session.beginTransaction ();

        try{
            detailDao.setSession(session);
            detailDao.save(
                    new Detail(
                            det.getDId(),
                            det.getGDate(),
                            det.getRDate(),
                            new User (
                                    det.getUser().getUserId(),
                                    det.getUser().getUserName(),
                                    det.getUser().getPassword()

                            ),
                            new Book (
                                    det.getBook().getBId(),
                                    det.getBook().getTitle(),
                                    det.getBook().getAuthor(),
                                    det.getBook().getGenre(),
                                    det.getBook().getStatus()

                            )));
            transaction.commit();
            session.close();
            return true;

        }catch (Exception e){
            transaction.rollback ();
            e.printStackTrace ();
            return false;
        }
    }

    @Override
    public boolean updateDetail(DetailDto det) {
        session=SessionFactoryConfig.getInstance ().getSession ();
        Transaction transaction=session.beginTransaction ();

        try{
            detailDao.setSession (session);
            detailDao.update (
                    new Detail(
                            det.getDId(),
                            det.getGDate(),
                            det.getRDate(),
                            new User (
                                    det.getUser().getUserId(),
                                    det.getUser().getUserName(),
                                    det.getUser().getPassword()

                            ),
                            new Book (
                                    det.getBook().getBId(),
                                    det.getBook().getTitle(),
                                    det.getBook().getAuthor(),
                                    det.getBook().getGenre(),
                                    det.getBook().getStatus()

                            )));
            transaction.commit();
            session.close();
            return true;

        }catch (Exception e){
            transaction.rollback ();
            e.printStackTrace ();
            return false;
        }
    }

    @Override
    public boolean deleteDetail(DetailDto det) {
        session=SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            detailDao.setSession(session);
            detailDao.delete(
                    new Detail(
                            det.getDId(),
                            det.getGDate(),
                            det.getRDate(),
                            new User (
                                    det.getUser().getUserId(),
                                    det.getUser().getUserName(),
                                    det.getUser().getPassword()

                            ),
                            new Book (
                                    det.getBook().getBId(),
                                    det.getBook().getTitle(),
                                    det.getBook().getAuthor(),
                                    det.getBook().getGenre(),
                                    det.getBook().getStatus()


                            )
                    ));
            transaction.commit();
            session.close();
            return true;
        }catch (Exception e){
            session.close();
            transaction.rollback();
        }

        return false;
    }

    @Override
    public List<DetailDto> loadAll() {
        session=SessionFactoryConfig.getInstance ().getSession ();
        Transaction transaction=session.beginTransaction ();

        detailDao.setSession (session);
        List<Detail>list= detailDao. loadAll ();
        List<DetailDto>detaildtos= new ArrayList<>();
        System.out.println ("Check1");

        for (Detail det :list) {
            detaildtos.add(new DetailDto (
                    det.getId(),
                    det.getgDate(),
                    det.getrDate(),
                    new UserDto (
                            det.getUser().getUserId(),
                            det.getUser().getUserName(),
                            det.getUser().getPassword()

                    ),
                    new BookDto (
                            det.getBook().getId(),
                            det.getBook().getTitle(),
                            det.getBook().getAuthor(),
                            det.getBook().getGenre(),
                            det.getBook().getStatus()
                    )
            ));
        }

        System.out.println ("Check2");
        return  detaildtos;
    }
}
