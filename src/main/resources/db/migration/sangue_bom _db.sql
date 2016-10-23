create database sangue_bom_db;
use sangue_bom_db;

create table address(
	id bigint unsigned not null auto_increment ,
	street varchar(255) not null,
	number int,
	neighborhood varchar(255) not null,
	cep varchar(20) not null,
	complement varchar(255) not null,
	city varchar(255) not null,
	state varchar(255) not null,
	latitude double not null,
	longitude double not null,
	primary key (id)
);

create table telephone(
	id bigint unsigned not null auto_increment,
	ddi int not null,
	ddd int not null,
	number varchar(20) not null,
	type varchar(20) not null,
	primary key (id)
);

create table user(
	id bigint unsigned not null auto_increment,
	name varchar(255) not null,
	email varchar(255) not null,
	birth_date date not null,
	address_id bigint unsigned not null,
	telephone_id bigint unsigned not null,
	primary key (id),
	foreign key (address_id) references address(id),
	foreign key (telephone_id) references telephone(id)
);

create table blood_donator(
	user_id bigint unsigned not null,
	blood_type varchar(2) not null,
	blood_factor varchar(1) not null,
	cpf varchar(11) not null,
	nickname varchar(255) not null,
	primary key (user_id),
	foreign key (user_id) references user(id)
);

create table administrator(
	user_id bigint unsigned not null,
	password varchar(20) not null,
	primary key (user_id),
	foreign key (user_id) references user(id)
);

create table super_administrator(
	administrator_id bigint unsigned not null,
	primary key (administrator_id),
	foreign key (administrator_id) references administrator(user_id)
);


create table news(
	id bigint unsigned not null auto_increment,
	title varchar(255) not null,
	text text not null,
	created_at date not null,
	administrator_id bigint unsigned not null,
	primary key (id),
	foreign key (administrator_id) references administrator(user_id)
);

create table intent_donation(
	id bigint unsigned not null auto_increment,
	created_at date not null,
	status int not null,
	grant_date date not null,
	blood_donator_id bigint unsigned not null,
	primary key (id),
	foreign key (blood_donator_id) references blood_donator(user_id)
);
