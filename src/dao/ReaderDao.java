package dao;

import domain.Reader;

public interface ReaderDao {
    boolean addReader(Reader reader);
    boolean checkEmail(String email);
    Reader findReaderByEmailAndPassword(String email, String password);
}
