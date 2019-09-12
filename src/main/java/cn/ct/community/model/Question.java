package cn.ct.community.model;

public class Question {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column QUESTION.ID
     *
     * @mbg.generated Thu Sep 12 22:15:35 CST 2019
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column QUESTION.TITLE
     *
     * @mbg.generated Thu Sep 12 22:15:35 CST 2019
     */
    private String title;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column QUESTION.DESCRPTION
     *
     * @mbg.generated Thu Sep 12 22:15:35 CST 2019
     */
    private String descrption;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column QUESTION.TAG
     *
     * @mbg.generated Thu Sep 12 22:15:35 CST 2019
     */
    private String tag;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column QUESTION.LIKE_COUNTS
     *
     * @mbg.generated Thu Sep 12 22:15:35 CST 2019
     */
    private Integer likeCounts;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column QUESTION.CREATOR
     *
     * @mbg.generated Thu Sep 12 22:15:35 CST 2019
     */
    private Integer creator;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column QUESTION.VIEW_COUNTS
     *
     * @mbg.generated Thu Sep 12 22:15:35 CST 2019
     */
    private Integer viewCounts;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column QUESTION.COMMENT_COUNTS
     *
     * @mbg.generated Thu Sep 12 22:15:35 CST 2019
     */
    private Integer commentCounts;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column QUESTION.GTM_CREATE
     *
     * @mbg.generated Thu Sep 12 22:15:35 CST 2019
     */
    private Long gtmCreate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column QUESTION.GTM_UPDATE
     *
     * @mbg.generated Thu Sep 12 22:15:35 CST 2019
     */
    private Long gtmUpdate;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column QUESTION.ID
     *
     * @return the value of QUESTION.ID
     *
     * @mbg.generated Thu Sep 12 22:15:35 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column QUESTION.ID
     *
     * @param id the value for QUESTION.ID
     *
     * @mbg.generated Thu Sep 12 22:15:35 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column QUESTION.TITLE
     *
     * @return the value of QUESTION.TITLE
     *
     * @mbg.generated Thu Sep 12 22:15:35 CST 2019
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column QUESTION.TITLE
     *
     * @param title the value for QUESTION.TITLE
     *
     * @mbg.generated Thu Sep 12 22:15:35 CST 2019
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column QUESTION.DESCRPTION
     *
     * @return the value of QUESTION.DESCRPTION
     *
     * @mbg.generated Thu Sep 12 22:15:35 CST 2019
     */
    public String getDescrption() {
        return descrption;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column QUESTION.DESCRPTION
     *
     * @param descrption the value for QUESTION.DESCRPTION
     *
     * @mbg.generated Thu Sep 12 22:15:35 CST 2019
     */
    public void setDescrption(String descrption) {
        this.descrption = descrption == null ? null : descrption.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column QUESTION.TAG
     *
     * @return the value of QUESTION.TAG
     *
     * @mbg.generated Thu Sep 12 22:15:35 CST 2019
     */
    public String getTag() {
        return tag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column QUESTION.TAG
     *
     * @param tag the value for QUESTION.TAG
     *
     * @mbg.generated Thu Sep 12 22:15:35 CST 2019
     */
    public void setTag(String tag) {
        this.tag = tag == null ? null : tag.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column QUESTION.LIKE_COUNTS
     *
     * @return the value of QUESTION.LIKE_COUNTS
     *
     * @mbg.generated Thu Sep 12 22:15:35 CST 2019
     */
    public Integer getLikeCounts() {
        return likeCounts;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column QUESTION.LIKE_COUNTS
     *
     * @param likeCounts the value for QUESTION.LIKE_COUNTS
     *
     * @mbg.generated Thu Sep 12 22:15:35 CST 2019
     */
    public void setLikeCounts(Integer likeCounts) {
        this.likeCounts = likeCounts;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column QUESTION.CREATOR
     *
     * @return the value of QUESTION.CREATOR
     *
     * @mbg.generated Thu Sep 12 22:15:35 CST 2019
     */
    public Integer getCreator() {
        return creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column QUESTION.CREATOR
     *
     * @param creator the value for QUESTION.CREATOR
     *
     * @mbg.generated Thu Sep 12 22:15:35 CST 2019
     */
    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column QUESTION.VIEW_COUNTS
     *
     * @return the value of QUESTION.VIEW_COUNTS
     *
     * @mbg.generated Thu Sep 12 22:15:35 CST 2019
     */
    public Integer getViewCounts() {
        return viewCounts;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column QUESTION.VIEW_COUNTS
     *
     * @param viewCounts the value for QUESTION.VIEW_COUNTS
     *
     * @mbg.generated Thu Sep 12 22:15:35 CST 2019
     */
    public void setViewCounts(Integer viewCounts) {
        this.viewCounts = viewCounts;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column QUESTION.COMMENT_COUNTS
     *
     * @return the value of QUESTION.COMMENT_COUNTS
     *
     * @mbg.generated Thu Sep 12 22:15:35 CST 2019
     */
    public Integer getCommentCounts() {
        return commentCounts;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column QUESTION.COMMENT_COUNTS
     *
     * @param commentCounts the value for QUESTION.COMMENT_COUNTS
     *
     * @mbg.generated Thu Sep 12 22:15:35 CST 2019
     */
    public void setCommentCounts(Integer commentCounts) {
        this.commentCounts = commentCounts;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column QUESTION.GTM_CREATE
     *
     * @return the value of QUESTION.GTM_CREATE
     *
     * @mbg.generated Thu Sep 12 22:15:35 CST 2019
     */
    public Long getGtmCreate() {
        return gtmCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column QUESTION.GTM_CREATE
     *
     * @param gtmCreate the value for QUESTION.GTM_CREATE
     *
     * @mbg.generated Thu Sep 12 22:15:35 CST 2019
     */
    public void setGtmCreate(Long gtmCreate) {
        this.gtmCreate = gtmCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column QUESTION.GTM_UPDATE
     *
     * @return the value of QUESTION.GTM_UPDATE
     *
     * @mbg.generated Thu Sep 12 22:15:35 CST 2019
     */
    public Long getGtmUpdate() {
        return gtmUpdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column QUESTION.GTM_UPDATE
     *
     * @param gtmUpdate the value for QUESTION.GTM_UPDATE
     *
     * @mbg.generated Thu Sep 12 22:15:35 CST 2019
     */
    public void setGtmUpdate(Long gtmUpdate) {
        this.gtmUpdate = gtmUpdate;
    }
}