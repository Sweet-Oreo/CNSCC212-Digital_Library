package dao;

import domain.Reader;
import domain.University;

public interface ReaderDao {
    boolean addReader(Reader reader);
    boolean checkReaderEmail(String email);
    Reader findReaderByEmailAndPassword(String email, String password);
}
