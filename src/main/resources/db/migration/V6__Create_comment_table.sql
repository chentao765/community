create table comment
(
	id bigint auto_increment,
	parent_id bigint,
	type int,
	content varchar(200),
	comment_creator int,
	gtm_create bigint,
	gtm_update bigint,
	like_counts int,
	constraint comment_pk
		primary key (id)
);
