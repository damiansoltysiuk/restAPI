import frontend.MainMenu;

public class Application {
    public static void main(String[] args) {
        MainMenu menu = new MainMenu();
        System.out.println("Hello User!");
        while (true) {
            menu.menu();
            String choose = menu.chooseMenu();
            menu.action(choose);
            System.out.println();
        }

    }
}
