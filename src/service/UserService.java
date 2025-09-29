package service;

import dao.UserDAO;

public class UserService {
    private UserDAO dao = new UserDAO();

    public boolean login(String username, String password) {
        return dao.validateLogin(username, password);
    }
}
