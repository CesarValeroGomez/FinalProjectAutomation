package entities;

public class Card {

    private String name;
    private String desc;
    private String due;
    private String email;
    private Boolean closed = false;

    public Card() {}

    public Card(String name, String desc, String due, String email, String idList, Boolean closed) {
        setName(name);
        setDesc(desc);
        setDue(due);
        setEmail(email);
        setClosed(closed);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDue() {
        return due;
    }

    public void setDue(String due) {
        this.due = due;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }
}
