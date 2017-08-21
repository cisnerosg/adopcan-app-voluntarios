package com.adopcan.adopcan_voluntarios.Security;

import com.adopcan.adopcan_voluntarios.DTO.User;

/**
 * Created by german on 20/8/2017.
 */

public class SecurityHandler {

    private User user;
    private static SecurityHandler security;

    // El constructor es privado, no permite que se genere un constructor por defecto.
    private SecurityHandler(User user) {
        this.user = user;
    }

    public static SecurityHandler getInstance(User user) {
        if (security == null){
            security = new SecurityHandler(user);
        }

        return security;

    }

    // metodos getter y setter

    public User getUser() {
        return user;
    }

    public static SecurityHandler getSecurity() {
        return security;
    }
}
