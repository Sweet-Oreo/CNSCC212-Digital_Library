package dao;

import java.util.List;

/**
 * Handle interactions with database associated with major.
 */
public interface MajorDao {
    List<String> findMajors();
}
