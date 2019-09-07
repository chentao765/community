create table USER
(
    ID         INTEGER   auto_increment,
    ACCOUNT_ID VARCHAR(100),
    NAME       VARCHAR(50),
    TOKEN      CHAR(36),
    GTM_CREATE BIGINT,
    GTM_UPDATE BIGINT,
    constraint USER_PK
        primary key (ID)
);