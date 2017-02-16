create table Word (ID  SERIAL PRIMARY KEY,data char(50) not null, language_id int not null default 0,right_answers_count int not null default 0);
create table word_join (word_id int not null, translation_id int not null,user_id int not null,PRIMARY KEY(word_id, translation_id,user_id));
create table language (id serial primary key,data char(50) not null);
create table UserAcc (id serial primary key,login char(50) not null,password char(50) not null,email char(50) not null);