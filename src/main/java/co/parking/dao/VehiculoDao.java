package co.parking.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.parking.domain.Vehiculo;
import co.parking.domain.enumeration.TipoVehiculo;

@Repository
public interface VehiculoDao extends CrudRepository<Vehiculo, Long> {

	@Query(value = "select v from Vehiculo v where v.placa =:placa")
	public Vehiculo consultarVehiculoPorPlaca(@Param("placa") String placa);
	
	@Query("SELECT COUNT(*) FROM Vehiculo v WHERE v.tipo=:tipo AND v.activo = TRUE")
    public int vehiculosParqueados(@Param("tipo") TipoVehiculo tipo);
	
	@Query("select v.placa, v.tipo, f.fechaIngreso from Vehiculo v inner join Factura f on v.id = f.idVehiculo")
	public List<Vehiculo> findAll();
	

}
