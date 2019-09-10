package cn.ct.community.enums;

public enum CommentTypeEnum {
    QUESTION(1),
    COMMENT(2);

    private Integer type;

    CommentTypeEnum(Integer type) {
        this.type = type;
    }

    public static boolean isExist(Integer type) {
        boolean flag=false;
        for (CommentTypeEnum e:CommentTypeEnum.values()
             ) {
            if(e.getType().equals(type)){
                flag=true;
            }
        }
        return  flag;
    }

    public Integer getType() {
        return type;
    }
}
