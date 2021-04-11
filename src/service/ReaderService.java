package service;

import domain.Reader;

public interface ReaderService {
    boolean addReader(Reader reader);
    Reader login(Reader user);
}
