
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
;
    create table POIS (
        poiID bigint not null auto_increment,
        coordenada varchar(255),
        nombre varchar(255),
        ubicacion_ubicacionID bigint,
        primary key (poiID)
    )
;
    create table Region (
        RegionID bigint not null auto_increment,
        barrio varchar(255),
        localidad varchar(255),
        pais varchar(255),
        provincia varchar(255),
        primary key (RegionID)
    )
;
    create table Ubicacion (
        ubicacionID bigint not null auto_increment,
        coordenadas varchar(255),
        domicilio_domicilioID bigint,
        region_RegionID bigint,
        primary key (ubicacionID)
    );actor

    alter table POIS 
        add constraint FK_rq1bh6ctglte2ju8jb84o0ky1 
        foreign key (ubicacion_ubicacionID) 
        references Ubicacion (ubicacionID)
;
    alter table Ubicacion 
        add constraint FK_oikmk409p3l8ed4iwjt3ahaqe 
        foreign key (domicilio_domicilioID) 
        references Domicilio (domicilioID)
;
    alter table Ubicacion 
        add constraint FK_g8k3mkhl5i8ox94kbbjq0xe7l 
        foreign key (region_RegionID) 
        references Region (RegionID)
