package cn.ct.community.enums;

public enum  NotificationTypeEnum {
    QUESTION_REPLAY(1,"回复了问题"),
    COMMENT_REPLAY(2,"回复了评论");

    private int type;
    private String name;

    NotificationTypeEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static String getTypeName(int type){
        for (NotificationTypeEnum n:
                NotificationTypeEnum.values()) {
            if(n.getType()==type){
                return n.getName();
            }

        }
        return null;
    }
}
