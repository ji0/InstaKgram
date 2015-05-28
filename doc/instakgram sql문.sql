
CREATE TABLE member
( 
no        NUMBER(8),
name      VARCHAR2(30),
email     VARCHAR2(80),
password  VARCHAR2(30),
gender    VARCHAR2(10),
pic_ref   VARCHAR2(80)
) ;

ALTER TABLE member
ADD ( CONSTRAINT member_no_pk PRIMARY KEY ( no ) );


CREATE SEQUENCE member_no_seq
 START WITH     1
 INCREMENT BY   1
 MAXVALUE       99999999
 NOCACHE
 NOCYCLE;


---------gboard

CREATE TABLE gboard
( 
no           NUMBER(8),
title        VARCHAR2(200) NOT NULL,
content      VARCHAR2(4000) NOT NULL,
pic_ref      VARCHAR2(80),
member_no    NUMBER(8),
member_name  VARCHAR2(30),
view_cnt	 NUMBER(10),
reg_date	 DATE NOT NULL
) ;

ALTER TABLE gboard
ADD ( CONSTRAINT gboard_no_pk PRIMARY KEY ( no ) );

CREATE SEQUENCE gboard_no_seq
 START WITH     1
 INCREMENT BY   1
 MAXVALUE       99999999
 NOCACHE
 NOCYCLE;


-----------------------------------

CREATE TABLE dboard
( 
no           NUMBER(8),
pic_ref      VARCHAR2(80),
content      VARCHAR2(4000) NOT NULL,
like_cnt	 NUMBER(10),
member_no    NUMBER(8),
member_name  VARCHAR2(30),
reg_date	 DATE NOT NULL
) ;


ALTER TABLE dboard
ADD ( CONSTRAINT dboard_no_pk PRIMARY KEY ( no ) );

CREATE SEQUENCE dboard_no_seq
 START WITH     1
 INCREMENT BY   1
 MAXVALUE       99999999
 NOCACHE
 NOCYCLE;



reply
-------------

CREATE TABLE reply
( 
no           NUMBER(8),
content      VARCHAR2(4000) NOT NULL,
daily_no	 NUMBER(10),
member_no    NUMBER(8),
member_name  VARCHAR2(30),
reg_date	 DATE NOT NULL
) ;


ALTER TABLE reply
ADD ( CONSTRAINT reply_no_pk PRIMARY KEY ( no ) );

CREATE SEQUENCE reply_no_seq
 START WITH     1
 INCREMENT BY   1
 MAXVALUE       99999999
 NOCACHE
 NOCYCLE;

