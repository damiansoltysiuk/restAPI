package util;

import model.User;

import java.util.Scanner;

public class UserUtil {
    public static String userToJsonType(User user) {
        StringBuilder sb = new StringBuilder("{");
        if (user.getEmail().trim().length() != 0) {
            sb.append("\"email\":\"");
            sb.append(user.getEmail() + "\",");
        }
        if (user.getFirstName().trim().length() != 0) {
            sb.append("\"first_name\":");
            sb.append(user.getFirstName() + "\",");
        }
        if (user.getLastName().trim().length() != 0) {
            sb.append("\"last_name\":");
            sb.append(user.getLastName() + "\",");
        }
        if (user.getAvatarLink().trim().length() != 0) {
            sb.append("\"avatar\":");
            sb.append(user.getFirstName() + "\",");
        }
        int index = sb.lastIndexOf(",");
        return (index > 0 ? sb.substring(0, index): sb.toString()) + "}";
    }

    public static User addUserData() {
        Scanner sc = new Scanner(System.in);
        System.out.printf("User email: ");
        String email = sc.nextLine();
        System.out.printf("First name: ");
        String firstName = sc.nextLine();
        System.out.printf("Last name: ");
        String lastName = sc.nextLine();
        System.out.printf("Avatar [URI]: ");
        String avatar = sc.nextLine();

        return new User(email, firstName, lastName, avatar);
    }
}
