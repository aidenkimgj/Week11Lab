package services;

import dataaccess.UserDB;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.User;

public class AccountService {
    
    private UserDB userDB;
    
    public AccountService() {
        userDB = new UserDB();
    }
    
    public User login(String username, String password, String path) {
                
        try {
            User user = userDB.get(username);
            if (password.equals(user.getPassword())) {
                // Log activity
                Logger.getLogger(AccountService.class.getName()).log(Level.INFO, "Successful login by {0}", username);
                /*
                // send basic e-mail
                String msgBody = "Your account has logged in.";
                GmailService.sendMail(email, "Your account has logged in", msgBody, false);
                */
                
                // Send E-mail with template
				
                String to = user.getEmail();
                String subject = "Notes App Login";
                String template = path + "/emailtemplates/login.html";
                
                HashMap<String, String> tags = new HashMap<>();
                tags.put("firstname", user.getFirstName());
                tags.put("lastname", user.getLastName());
                tags.put("date", (new java.util.Date()).toString());
                
                GmailService.sendMail(to, subject, template, tags);
				

                return user;
            }
        } catch (Exception e) {
        }
        
        return null;
    }

    public void resetPassword(String email, String path, String url) {
        
        try {
            String uuid = UUID.randomUUID().toString();
            User user = userDB.getByEmail(email);

            user.setResetpasswordUUID(uuid);
            userDB.update(user);

            String to = user.getEmail();
            String subject = "Notes App Password Reset";
            String template = path + "/emailtemplates/resetpassword.html";
            String link = url + "?uuid=" + uuid;

            HashMap<String, String> tags = new HashMap<>();
            tags.put("firstname", user.getFirstName());
            tags.put("lastname", user.getLastName());
            tags.put("username", user.getUsername());
            tags.put("link", link);
                
        
            GmailService.sendMail(to, subject, template, tags);
        } catch (Exception ex) {
            Logger.getLogger(AccountService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean changePassword(String uuid, String newPassword) {
        try {
            User user = userDB.getByUUID(uuid);
            user.setPassword(newPassword);
            user.setResetpasswordUUID(null);
            userDB.update(user);
            return true;
        } catch (Exception ex) {
            return false;
        }

    }
}
