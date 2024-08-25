drop database if exists eshanPosSystem;

create databse if not exists eshanPosSystem;

use eshanPosSystem;

create table customer(
                         id          varchar(200) primary key,
                         name        varchar(200) not null,
                         address     varchar(200) not null,
                         contact     varchar(200) not null
);

create table item(
                     id              varchar(200) primary key,
                     description     varchar(200) not null,
                     unit_price      decimal(10,2) not null,
                     qty             varchar(200) not null
);

create table orders(
                       id              varchar(200) primary key,
                       date            date not null,
                       discount_value  decimal(10,2) not null,
                       sub_total       decimal(10,2) not null,
                       customer_id     varchar(200) not null,
                       constraint foreign key (customer_id) references customer(id)
                           on update cascade on delete cascade
);

create table order_details(
                              order_id            varchar(255) not null ,
                              item_id             varchar(255) not null,
                              qty                 int not null,
                              unit_price          decimal(10,2) not null,
                              total               decimal(10,2) not null,
                              constraint foreign key (order_id) references orders(id)
                                  on update cascade on delete cascade,
                              constraint foreign key (item_id) references item(id)
                                  on update cascade on delete cascade
);