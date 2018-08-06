/*Populate tables*/

INSERT INTO vehiculo(tipo, placa, cilindraje, activo, fecha_ingreso) VALUES('MOTO', 'DFG567', '800', true,'2018-08-03T17:07:20.673');
INSERT INTO vehiculo(tipo, placa, cilindraje, activo, fecha_ingreso) VALUES('MOTO', 'YY56R', '400', true, '2018-08-04T17:07:20.673');
INSERT INTO vehiculo(tipo, placa, cilindraje, activo, fecha_ingreso) VALUES('CARRO', 'PPG56R', '1000', false, '2018-08-03T14:09:20.673');
INSERT INTO vehiculo(tipo, placa, cilindraje, activo, fecha_ingreso) VALUES('MOTO', 'WUQ86D', '250', true, '2018-08-03T17:07:40.673');
INSERT INTO vehiculo(tipo, placa, cilindraje, activo, fecha_ingreso) VALUES('CARRO', 'PTQ56D', '9410', true, '2018-08-03T17:07:40.673');
INSERT INTO vehiculo(tipo, placa, cilindraje, activo, fecha_ingreso) VALUES('MOTO', 'HHG56R', '800', true, '2018-08-03T17:07:40.673');
INSERT INTO vehiculo(tipo, placa, cilindraje, activo, fecha_ingreso) VALUES('MOTO', 'WTQ86D', '800', false, '2018-08-03T17:07:40.673');
INSERT INTO vehiculo(tipo, placa, cilindraje, activo, fecha_ingreso) VALUES('MOTO', 'WTQ88D', '800', true, '2018-08-03T17:07:40.673');
INSERT INTO vehiculo(tipo, placa, cilindraje, activo, fecha_ingreso) VALUES('CARRO', 'GKOY89O', '800', true, '2018-08-03T17:07:40.673');

INSERT INTO FACTURA(fecha_ingreso,fecha_salida,vehiculo_id,total_pagar) VALUES('2018-08-03T17:07:20.673',null, 1, 0.0);
INSERT INTO FACTURA(fecha_ingreso,fecha_salida,vehiculo_id,total_pagar) VALUES('2018-08-04T17:07:20.673','2018-08-04T19:07:20.673', 2, 300.0);
INSERT INTO FACTURA(fecha_ingreso,fecha_salida,vehiculo_id,total_pagar) VALUES('2018-08-03T14:09:20.673',null, 3, 0.0);
INSERT INTO FACTURA(fecha_ingreso,fecha_salida,vehiculo_id,total_pagar) VALUES('2018-08-03T17:07:40.673',null, 4, 0.0);
INSERT INTO FACTURA(fecha_ingreso,fecha_salida,vehiculo_id,total_pagar) VALUES('2018-08-03T19:07:20.673',null, 5, 0.0);