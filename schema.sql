
    create table Disponibilidad (
        idDisponibilidad bigint not null auto_increment,
        horarioFinal tinyblob,
        horarioInicial tinyblob,
        id tinyblob,
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

    create table MailsDemora (
        MailDemora_Id bigint not null auto_increment,
        TiempoMaximoBusqueda bigint,
        primary key (MailDemora_Id)
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

    create table ResultadosBusqueda (
        idResultado bigint not null auto_increment,
        cantidadDeResultados integer not null,
        FechaBusqueda tinyblob,
        fraseBuscada varchar(255),
        id tinyblob,
        unTiempoFinalizacion mediumblob,
        unTiempoInicio mediumblob,
        terminal_terminalID bigint,
        primary key (idResultado)
    )

    create table ResultadosBusqueda_POIS (
        ResultadosBusqueda_idResultado bigint not null,
        poisEncontrados_poiID bigint not null
    )

    create table ServiciosCGP (
        idServicioCGP bigint not null auto_increment,
        id tinyblob,
        nombre varchar(255),
        poiID bigint,
        primary key (idServicioCGP)
    )

    create table ServiciosCGP_Disponibilidad (
        ServiciosCGP_idServicioCGP bigint not null,
        horariosDeAtencion_idDisponibilidad bigint not null,
        horarios integer not null,
        primary key (ServiciosCGP_idServicioCGP, horarios)
    )

    create table Terminales (
        terminalID bigint not null auto_increment,
        comunaTerminal varchar(255),
        latitud double precision not null,
        longitud double precision not null,
        nombreTerminal varchar(255),
        mail_MailDemora_Id bigint,
        usuario_id bigint,
        primary key (terminalID)
    )

    create table Ubicacion (
        ubicacionID bigint not null auto_increment,
        latitud double precision not null,
        longitud double precision not null,
        id tinyblob,
        region_RegionID bigint,
        primary key (ubicacionID)
    )

    create table Usuarios (
        id bigint not null auto_increment,
        password varchar(255) not null,
        role varchar(255) not null,
        username varchar(255) not null,
        primary key (id)
    )

    create table palabrasClaveDePoi (
        poiID bigint not null,
        palabrasClave varchar(255)
    )

    alter table ServiciosCGP_Disponibilidad 
        add constraint UK_sc7sk0ooswmxqt8swjv4sc97v  unique (horariosDeAtencion_idDisponibilidad)

    alter table Usuarios 
        add constraint UK_ko1epxuhyipq4hkt8paojfa5q  unique (username)

    alter table Disponibilidad 
        add constraint FK_jvspbhxcn7ijej8sjnmocih8j 
        foreign key (poiID) 
        references POIS (poiID)

    alter table Disponibilidad_dias 
        add constraint FK_qth5ywka3bsues0par91w9l9c 
        foreign key (Disponibilidad_idDisponibilidad) 
        references Disponibilidad (idDisponibilidad)

    alter table ResultadosBusqueda 
        add constraint FK_jevst7er4ku2ibrdmfxof0hfr 
        foreign key (terminal_terminalID) 
        references Terminales (terminalID)

    alter table ResultadosBusqueda_POIS 
        add constraint FK_f50bfqh2tv2ftrtu7fp9g51vj 
        foreign key (poisEncontrados_poiID) 
        references POIS (poiID)

    alter table ResultadosBusqueda_POIS 
        add constraint FK_q6s5cd8jocof1u30y7ye0v0at 
        foreign key (ResultadosBusqueda_idResultado) 
        references ResultadosBusqueda (idResultado)

    alter table ServiciosCGP 
        add constraint FK_buuxq7tkj0i2mrl7ewj5tkkpk 
        foreign key (poiID) 
        references POIS (poiID)

    alter table ServiciosCGP_Disponibilidad 
        add constraint FK_sc7sk0ooswmxqt8swjv4sc97v 
        foreign key (horariosDeAtencion_idDisponibilidad) 
        references Disponibilidad (idDisponibilidad)

    alter table ServiciosCGP_Disponibilidad 
        add constraint FK_hwd7ks0h0pe4b5n9p690ohe0v 
        foreign key (ServiciosCGP_idServicioCGP) 
        references ServiciosCGP (idServicioCGP)

    alter table Terminales 
        add constraint FK_ao9hqggxyrlruljcaqqltl114 
        foreign key (mail_MailDemora_Id) 
        references MailsDemora (MailDemora_Id)

    alter table Terminales 
        add constraint FK_pacsygje1yi58ebvai0vf5t1a 
        foreign key (usuario_id) 
        references Usuarios (id)

    alter table Ubicacion 
        add constraint FK_g8k3mkhl5i8ox94kbbjq0xe7l 
        foreign key (region_RegionID) 
        references Region (RegionID)

    alter table palabrasClaveDePoi 
        add constraint FK_assoywibbby30d9okc9ve5mp5 
        foreign key (poiID) 
        references POIS (poiID)
