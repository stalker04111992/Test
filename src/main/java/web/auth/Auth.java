package web.auth;

import web.entity.User;

public class Auth
{
    private User user;
    private String []roles;

    public Auth(User user, String[] roles)
    {
        this.user = user;
        this.roles = roles;
    }

    public String GetUsername()
    {
        return user.getUsername();
    }

    public int GetId()
    {
        return user.getId();
    }

    public boolean ExistRole(String roleUser)
    {
        for(String role: roles)
        {
            if (role.equals(roleUser))
            {
                return true;
            }
        }
        return false;
    }

}