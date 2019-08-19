create table user (
    id      integer         not null    primary key autoincrement,
    name    varchar(30)     not null,
    age     int
);

create table message (
    id          integer         not null    primary key autoincrement,
    authorId    integer         not null,
    content     varchar(128)    not null,
    constraint fk_message_authorId foreign key(authorId) references user(id)
);