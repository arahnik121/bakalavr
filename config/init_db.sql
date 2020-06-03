create table groundterritory
(
    id integer not null
        constraint groundterritory_pk
            primary key
);

alter table groundterritory owner to postgres;

create table aircraft
(
    id char(36) not null
        constraint aircraft_pk
            primary key,
    x integer not null,
    y integer not null,
    rangeofviewx integer not null,
    rangeofviewy integer not null,
    ground_id integer
        constraint aircraft_groundterritory_id_fk
            references groundterritory
            on update restrict on delete cascade,
    object_info text
);

alter table aircraft owner to postgres;








