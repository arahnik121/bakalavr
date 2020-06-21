create table aircraft
(
    id text not null
        constraint aircraft_pk
            primary key,
    x integer not null,
    y integer not null,
    rangeofviewx integer not null,
    rangeofviewy integer not null
);

alter table aircraft owner to postgres;

create table groundobject
(
    x integer not null,
    y integer not null,
    classification text,
    aircraft_id text
        constraint groundobject_aircraft_id_fk
            references aircraft
            on update restrict on delete cascade,
    constraint groundobject_pk
        primary key (x, y)
);

alter table groundobject owner to postgres;


















