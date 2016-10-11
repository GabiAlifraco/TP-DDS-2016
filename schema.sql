
    create table Disponibilidad (
        disponibilidadID bigint not null auto_increment,
        horarioFinal tinyblob,
        horarioInicial tinyblob,
        servicioID bigint,
        poiID bigint,
        primary key (disponibilidadID)
    )

    create table Domicilio (
        domicilioID bigint not null auto_increment,
        alturaCalles integer not null,
        alturaPrincipal integer not null,
        callePrincipal varchar(255),
        codigoPostal integer not null,
        departamento integer not null,
        entreUnaCalle varchar(255),
        hastaOtraCalle varchar(255),
        piso integer not null,
        unidad integer not null,
        primary key (domicilioID)
    )

    create table POIS (
        Tipo_Poi varchar(31) not null,
        poiID bigint not null auto_increment,
        nombre varchar(255),
        distancia integer,
        primary key (poiID)
    )

    create table Region (
        RegionID bigint not null auto_increment,
        barrio varchar(255),
        localidad varchar(255),
        pais varchar(255),
        provincia varchar(255),
        primary key (RegionID)
    )

    create table ServicioCGP (
        servicioID bigint not null auto_increment,
        nombre varchar(255),
        poiID bigint,
        primary key (servicioID)
    )

    create table Ubicacion (
        ubicacionID bigint not null auto_increment,
        coordenadas varchar(255),
        primary key (ubicacionID)
    )

    create table palabrasClaveDePoi (
        poiID bigint not null,
        palabrasClave varchar(255)
    )

    alter table Disponibilidad 
        add constraint FK_km6ffktf955mfdqyirsxtxpti 
        foreign key (servicioID) 
        references ServicioCGP (servicioID)

    alter table Disponibilidad 
        add constraint FK_jvspbhxcn7ijej8sjnmocih8j 
        foreign key (poiID) 
        references POIS (poiID)

    alter table ServicioCGP 
        add constraint FK_5qvqs1yk5v9q7rp564xi5hk9u 
        foreign key (poiID) 
        references POIS (poiID)

    alter table palabrasClaveDePoi 
        add constraint FK_assoywibbby30d9okc9ve5mp5 
        foreign key (poiID) 
        references POIS (poiID)
