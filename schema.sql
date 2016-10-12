
    create table Disponibilidad (
        idDisponibilidad bigint not null auto_increment,
        horarioFinal tinyblob,
        horarioInicial tinyblob,
        servicioID bigint,
        poiID bigint,
        primary key (idDisponibilidad)
    )

    create table Disponibilidad_dias (
        Disponibilidad_idDisponibilidad bigint not null,
        dias varchar(255),
        Dia integer not null,
        primary key (Disponibilidad_idDisponibilidad, Dia)
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

    create table ServiciosCGP (
        idServicioCGP bigint not null auto_increment,
        nombre varchar(255),
        poiID bigint,
        primary key (idServicioCGP)
    )

    create table Terminales (
        terminalID bigint not null auto_increment,
        comunaTerminal varchar(255),
        coordenadaDispositivoMovil varchar(255),
        nombreTerminal varchar(255),
        primary key (terminalID)
    )

    create table Ubicacion (
        ubicacionID bigint not null auto_increment,
        coordenadas varchar(255),
        region_RegionID bigint,
        primary key (ubicacionID)
    )

    create table palabrasClaveDePoi (
        poiID bigint not null,
        palabrasClave varchar(255)
    )

    alter table Disponibilidad 
        add constraint FK_km6ffktf955mfdqyirsxtxpti 
        foreign key (servicioID) 
        references ServiciosCGP (idServicioCGP)

    alter table Disponibilidad 
        add constraint FK_jvspbhxcn7ijej8sjnmocih8j 
        foreign key (poiID) 
        references POIS (poiID)

    alter table Disponibilidad_dias 
        add constraint FK_qth5ywka3bsues0par91w9l9c 
        foreign key (Disponibilidad_idDisponibilidad) 
        references Disponibilidad (idDisponibilidad)

    alter table ServiciosCGP 
        add constraint FK_buuxq7tkj0i2mrl7ewj5tkkpk 
        foreign key (poiID) 
        references POIS (poiID)

    alter table Ubicacion 
        add constraint FK_g8k3mkhl5i8ox94kbbjq0xe7l 
        foreign key (region_RegionID) 
        references Region (RegionID)

    alter table palabrasClaveDePoi 
        add constraint FK_assoywibbby30d9okc9ve5mp5 
        foreign key (poiID) 
        references POIS (poiID)
