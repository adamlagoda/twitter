create schema twitter;

create table users (id int auto_increment primary key, login varchar(20) not null, password varchar(30) not null);

create table tweets (id int auto_increment primary key, messsage varchar(255) not null, author_id int not null,
foreign key (author_id) references users(id));

create table followers (follower_id int not null, followed_id int not null,
foreign key (follower_id) references users(id), foreign key (followed_id) references users(id),
unique(follower_id, followed_id), constraint CHK_Not_follow_himself check(follower_id <> followed_id));