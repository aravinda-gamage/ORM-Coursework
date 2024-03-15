package lk.ijse.ormCoursework.dao.impl;

import lk.ijse.ormCoursework.dao.custom.BookDao;
import org.hibernate.Session;

public class BookDaoImpl implements BookDao {
    private Session session;
    @Override
    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public List<Book> loadAll() {
        String sqlQuery = "FROM Book";
    }

    @Override
    public String save(Book book) {
        return null;
    }

    @Override
    public void update(Book book) {

    }

    @Override
    public void delete(Book book) {

    }

    @Override
    public Book getObject(String id) throws Exception {
        return null;
    }

    @Override
    public List<String> bookIds() {
        return null;
    }
}
