package dao;

import domain.Reader;

public interface ReaderDao {
    boolean addReader(Reader reader);
    Reader findReaderByEmailAndPassword(String email, String password);
}
