package cn.ct.community.enums;

public enum NotificationStatusEnum {
    UNREAD(1,"未读"),
    READ(2,"已读");

    private int status;
    private String name;

    public int getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }

    NotificationStatusEnum(int status, String name) {
        this.status = status;
        this.name = name;
    }
    public static  String getStatusName(int status){
        for (NotificationStatusEnum n:
                NotificationStatusEnum.values()) {
            if(n.getStatus()==status){
                return n.getName();
            }


        }
        return null;
    }
}
