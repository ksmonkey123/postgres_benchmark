create table binary_data
(
    id bigserial,
    group_id bigint not null,

    data bytea not null,

    cre_uid varchar(30) not null,
    cre_dat timestamp not null,
    mut_uid varchar(30) not null,
    mut_dat timestamp not null,
    version integer not null,

    constraint binary_data_pk primary key (id)
);

create index binary_data__group_id on binary_data (group_id);