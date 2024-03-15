package lk.ijse.ormCoursework.dao.custom;

import org.hibernate.Session;
public interface BookDao {
    public void setSession(Session session);

    public List<Book> loadAll();
    public String save(Book book);
    public void update(Book book);
    public void delete(Book book);
    public Book getObject(String id) throws Exception;
    public List<String> bookIds();
}
