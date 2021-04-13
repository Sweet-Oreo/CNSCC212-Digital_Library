package service;

import domain.Reader;

public interface ReaderService {
    boolean addReader(Reader reader);
    boolean checkEmail(String email);
    Reader login(Reader user);
}
