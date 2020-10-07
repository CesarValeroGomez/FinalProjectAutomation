package entities;

public class ResponseLists {

    private String id;
    private String name;
    private String closed;
    private String pos;
    private String softLimit;
    private String idBoard;
    private String subscribed;

    public ResponseLists() {
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getClosed() {
        return closed;
    }
    public void setClosed(String closed) {
        this.closed = closed;
    }
    public String getPos() {
        return pos;
    }
    public void setPos(String pos) {
        this.pos = pos;
    }
    public String getSoftLimit() {
        return softLimit;
    }
    public void setSoftLimit(String softLimit) {
        this.softLimit = softLimit;
    }
    public String getIdBoard() {
        return idBoard;
    }
    public void setIdBoard(String idBoard) {
        this.idBoard = idBoard;
    }
    public String getSubscribed() {
        return subscribed;
    }
    public void setSubscribed(String subscribed) {
        this.subscribed = subscribed;
    }
}
