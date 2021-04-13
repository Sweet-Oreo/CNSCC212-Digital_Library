package service;

import domain.Reader;

public interface ReaderService {
    Reader login(Reader user);

    boolean addReader(Reader reader);
}
