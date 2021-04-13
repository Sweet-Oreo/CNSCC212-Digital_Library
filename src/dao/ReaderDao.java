package dao;

import domain.Reader;
import domain.University;

public interface ReaderDao {
    public Reader findReaderByEmailAndPassword(String email, String password);

    boolean addReader(Reader reader);
}
