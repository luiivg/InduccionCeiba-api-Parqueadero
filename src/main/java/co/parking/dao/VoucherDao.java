package co.parking.dao;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import co.parking.domain.Factura;

public interface VoucherDao extends CrudRepository<Factura, Long> {

	@Query(value = "select f from Factura f where f.idVehiculo = :idVehiculo AND totalAPagar is null")
	public Factura consultarVehiculo(@Param("idVehiculo") long idVehiculo);
	
}
