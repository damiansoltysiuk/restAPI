package model;

public class User {
    private int id;
    private String email;
    private String firstName;
    private String lastName;
    private String avatarLink;

    public User(int id, String email, String firstName, String lastName, String avatarLink) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.avatarLink = avatarLink;
    }

    public User(String email, String firstName, String lastName, String avatarLink) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.avatarLink = avatarLink;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAvatarLink() {
        return avatarLink;
    }

    public void setAvatarLink(String avatarLink) {
        this.avatarLink = avatarLink;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("id=").append(id);
        sb.append(", email='").append(email).append('\'');
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", avatarLink='").append(avatarLink).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

