package services;

import com.google.inject.ImplementedBy;

/**
 */
@ImplementedBy(DBLoginService.class)
public interface LoginService {

    public boolean login(String id, String rawPassword);
}
