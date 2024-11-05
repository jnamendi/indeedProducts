create table if not exists Product(
    barcode varchar(10) not null,
    item varchar(100) not null,
    category varchar(100) not null,
    price int not null,
    discount int not null,
    available int not null,
    PRIMARY KEY ( barcode )
);