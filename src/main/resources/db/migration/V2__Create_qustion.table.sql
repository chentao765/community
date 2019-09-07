create table question
(
	id int auto_increment,
	title varchar(50),
	descrption varchar(200),
	tag varchar(50),
	like_counts int,
	creator varchar(20),
	view_counts int,
	comment_counts int,
	constraint question_pk
		primary key (id)
);
