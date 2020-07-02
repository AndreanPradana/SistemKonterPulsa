CREATE TABLESPACE sistemkonterpulsa_07024
datafile 'F:\ANDREAN\Praktikum DB\tablespace\sistemkonterpulsa_07024.dbf'
size 30m;

CREATE USER andreanfp_07024
IDENTIFIED BY andrean
DEFAULT TABLESPACE sistemkonterpulsa_07024
QUOTA 30M ON sistemkonterpulsa_07024;

create table Transaksi
(
id_transaksi INTEGER not null,
jumlah_barang INTEGER,
total_harga INTEGER,
jumlah_pembayaran INTEGER,
jumlah_kembalian INTEGER,
constraint PK_Transaksi primary key (id_transaksi)
);

create table Barang
(
id_barang INTEGER not null,
id_jenis_barang INTEGER,
nama_barang VARCHAR2(15),
stock_barang INTEGER,
harga_barang INTEGER,
constraint PK_Barang primary key (id_barang)
);

create table Jenis_barang
(
id_jenis_barang INTEGER not null,
nama_jenis_barang VARCHAR(15),
constraint PK_Jenis_Barang primary key (id_jenis_barang)
);

create table Detail_pemesanan
(
id_transaksi INTEGER not null,
id_barang INTEGER,
jumlah INTEGER
);

create table Login
(
id_login INTEGER not null,
username VARCHAR2(15),
password CHAR(15),
constraint PK_Login primary key (id_login)
);

alter table barang
add constraint FK_Id_Jenis_Barang FOREIGN KEY (id_jenis_barang)
references jenis_barang (id_jenis_barang);

alter table detail_pemesanan
add constraint FK_Id_Transaksi FOREIGN KEY (id_transaksi)
references transaksi (id_transaksi),
add constraint FK_Id_Barang FOREIGN KEY (id_barang)
references barang (id_barang);

create sequence id_transaksi
minvalue 1
maxvalue 99999999
start with 1
increment by 1
nocache;

create sequence id_barang
minvalue 1
maxvalue 99999999
start with 1
increment by 1
nocache;

create sequence id_jenis_barang
minvalue 1
maxvalue 99999999
start with 1
increment by 1
nocache;

SELECT * FROM BARANG JOIN JENIS_BARANG ON BARANG.ID_JENIS_BARANG = JENIS_BARANG.ID_JENIS_BARANG

SELECT * FROM DETAIL_PEMESANAN JOIN BARANG ON DETAIL_PEMESANAN.ID_BARANG = BARANG.ID_BARANG JOIN JENIS_BARANG ON BARANG.ID_JENIS_BARANG = JENIS_BARANG.ID_JENIS_BARANG WHERE DETAIL_PEMESANAN.ID_TRANSAKSI = ID_TRANSAKI

create view dml_barang as
select * from barang;

create view right_join as
select a.id_login, a.username, b.jumlah_barang, c.total_harga
from login a right join transaksi c
on a.id_login = c.id_login
right join detail_pemesanan b
on c.id_transaksi = b.id_transaksi
where rownum >= (select min(id_transaksi) from transaksi)
and rownum <= (select avg(id_transaksi) from transaksi);


