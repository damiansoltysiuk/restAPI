package frontend;

import model.User;
import restAPI.RestAPI;
import util.UserUtil;

import java.util.List;
import java.util.Scanner;

public class MainMenu {
    public static void menu() {
        System.out.println("Please select action:\n1. Download userList\n2. Show info userID\n3. Add user\n4. Update user\n5. Delete user\n6. Exit");
    }

    public static String chooseMenu() {
        System.out.printf("Choose: ");
        Scanner sc = new Scanner(System.in);
        String choose = sc.nextLine();
        while (!choose.matches("[1-6]")) {
            System.out.println("Please select 1 - 6:");
            choose = sc.nextLine();
        }
        return choose;
    }

    public void action(String choose) {
        switch (choose) {
            case "1" : downloadUserList(); break;
            case "2" : showUser(); break;
            case "3" : addUser(); break;
            case "4" : updateUser(); break;
            case "5" : deleteUser(); break;
            case "6" : exit();break;
        }
    }

    void downloadUserList() {
        List<User> userList = RestAPI.getListUsers();
        System.out.println(userList);
    }

    void showUser() {
        int userID = selectUserID();
        User user = RestAPI.getUser(userID);
        System.out.println(user.toString());
    }

    int selectUserID() {
        System.out.printf("UserID: ");
        Scanner sc = new Scanner(System.in);
        String userID = sc.nextLine();
        while (!userID.matches("\\d*")) {
            System.out.printf("UserID must be a num! UserID: ");
            userID = sc.nextLine();
        }
        return Integer.parseInt(userID);
    }

    void addUser() {
        User user = new UserUtil().addUserData();
        new RestAPI().postUser(user);
    }

    void updateUser() {
        System.out.printf("Select UserID to update: ");
        int userID = selectUserID();
        System.out.println("Empty field is not sending. Data:");
        User user = new UserUtil().addUserData();
        String data = new UserUtil().userToJsonType(user);
        new RestAPI().putUpdateUser(userID, data);
    }

    void deleteUser() {
        System.out.printf("Select UserID to delete. ");
        int userID = selectUserID();
        new RestAPI().deleteUser(userID);
    }

    void exit() {
        System.out.println("Bye, bye");
        System.exit(0);
    }
}

