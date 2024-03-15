package lk.ijse.ormCoursework.bo.custom;

import lk.ijse.ormCoursework.dto.BookDto;

import java.util.List;

public interface BookBo {
    List<BookDto> loadAll();
    boolean saveBook(BookDto dto) ;
    boolean updateBook(BookDto dto) ;
    boolean deleteBook(BookDto dto) ;
    BookDto getBook(String id) throws Exception ;
}
