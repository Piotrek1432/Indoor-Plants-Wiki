drop table if exists plants;
create table plants(
    id int primary key auto_increment,
    name varchar(50) not null,
    description varchar(300)
);