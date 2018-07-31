package co.parking.dao;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import co.parking.entity.Factura;

public interface VoucherDao extends CrudRepository<Factura, Long> {

	@Query(value = "SELECT f FROM Factura f WHERE f.idVehiculo=:idVehiculo AND fechaSalida is null")
	public Factura consultarVehiculo(@Param("idVehiculo") long idVehiculo);
	
}
