drop database if exists bd_tienda;
create database bd_tienda;

use bd_tienda;

create table tiendas(
id int PRIMARY KEY auto_Increment,
horas_abiertas int,
direccion varchar(60),
metros_cuadrados int,
ingresos_mensuales int,
CONSTRAINT chk_horas CHECK (horas_abiertas IN (0, 60,
		200, 450,730))
);

create table Productos(
id int PRIMARY KEY auto_Increment,
nombre varchar(40),
precio float(4,2),
categoria Varchar(40),
fecha_caducidad Date,
id_tienda int,
CONSTRAINT fk_id_tienda 
		FOREIGN KEY(id_tienda)
        REFERENCES tiendas(id)
);

select * from tiendas;
select * from productos;

insert into productos (Nombre, precio,categoria,fecha_caducidad,id_tienda) 
values("chocobollo",2.50,"consumible","2022-12-2",1);
insert into tiendas(horas_abiertas,direccion,metros_cuadrados,ingresos_mensuales) values (0,"calle Larios 1",70,200);