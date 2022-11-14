package com.persistence;

// IMPORTS //
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.services.SessionManager;
import java.time.LocalDate;

/**
 * 
 * ContractDAOImpl - Clase - Implementación DAO de la interfaz CommonDAOImpl para la entidad "Contrato"
 * clase hija de CommonDAOImpl
 * 
 * @see com.persistence.Contract
 * @see com.persistence.ContractDAOI
 * @see com.persistence.CommonDAOImpl
 * 
 * @author Santiago
 * 
 */
public class ContractDAOImpl extends CommonDAOImpl<Contract> implements ContractDAOI{
	
	// LOG //
	private static final Logger LOG = LoggerFactory.getLogger(ContractDAOImpl.class);
	
	// Constructor //
	public ContractDAOImpl(SessionManager sm) {
		super(sm);		
	}	
	
	// CRUD //
	@Override
	public boolean create(Contract contract) {
		LOG.debug("Insertando contrato: {}", contract.getId());
		
		if (super.create(contract)) {
			LOG.debug("Contrato insertado correctamente");
			return true;
			
		} else {
			LOG.debug("Error al insertar el contrato");
			return false;
		}
	}

	@Override
	public Contract read(int id) {
		LOG.debug("Buscando contrato: {}", id);
		
		Contract c = super.read(id);
		
		LOG.debug("Contrato buscado correctamente");
		
		return c;
	}	

	@Override
	public List<Contract> read(LocalDate validity) {
		LOG.debug("Buscando contratos por fecha de validez: {}", validity);						    			    
		
		@SuppressWarnings("unchecked")
		final List<Contract> c = sm.getSession().createQuery("FROM Contract WHERE FechaValidez ='" + validity  + "'").list();
		
		StringBuilder mssg = new StringBuilder("");
		
		for (Contract cont : c) {
			mssg.append(cont.getId() + ", ");
		}
		
		LOG.debug("Contratos buscados satisfactoriamente, devueltos: {}", mssg);
		
		return c;
	}

	@Override
	public List<Contract> read(Double monthPrice) {
		LOG.debug("Buscando contratos según precio mensual: {}", monthPrice);		
		
		@SuppressWarnings("unchecked")
		final List<Contract> c = sm.getSession().createQuery("FROM Contract WHERE PrecioMensual='" + monthPrice + "'").list();
		
		StringBuilder mssg = new StringBuilder("");
		
		for (Contract cont : c) {
			mssg.append(cont.getId() + ", ");
		}
		
		LOG.debug("Contratos obtenidos satisfactoriamente, contratos obtenidos {}", mssg);
			
		return c;
	}
	
	@Override
	public List<Contract> readByExpiration(LocalDate expiration) {
		LOG.debug("Buscando contratos por fecha de expiración: {}", expiration);
		
		@SuppressWarnings("unchecked")
		final List<Contract> c = sm.getSession().createQuery("FROM Contract WHERE FechaExpiracion='" + expiration.toString() + "'").list();
		
		StringBuilder mssg = new StringBuilder("");
		
		for (Contract cont : c) {
			mssg.append(cont.getId() + ", ");
		}
		
		LOG.debug("Contratos buscados correctamente, devueltos: {}", mssg);
		
		return c;
	}

	@Override
	public List<Contract> readByClientId(int id) {
		LOG.debug("Buscando contratos por id del cliente: {}", id);
		
		@SuppressWarnings("unchecked")
		final List<Contract> c = sm.getSession().createQuery("FROM Contract WHERE Cliente='" + id + "'").list();
		
		StringBuilder mssg = new StringBuilder("");
		
		for (Contract cont : c) {
			mssg.append(cont.getId() + ", ");
		}
		
		LOG.debug("Contratos buscados correctamente, devueltos: {}", mssg);
		
		return c;
	}


	@Override
	public boolean update(Contract contract) {
		LOG.debug("Actualizando contrato: {}", contract.getId());
		
		if (super.update(contract)) {
			LOG.debug("Contrato actualizado satisfactoriamente");
			
			return true;
		} else {
			LOG.error("Error al actualizar al contrato");
			
			return false;
		}
	}



	@Override
	public boolean delete(Contract contract) {
		LOG.debug("Eliminando contrato: {}", contract.getId());
		
		if (super.delete(contract)) {
			LOG.debug("Contrato eliminado satisfactoriamente");
			
			return true;
		} else {
			LOG.error("Error al eliminar el contrato");
			
			return false;
		}
	}

	@Override
	public List<Contract> searchAll() {
		LOG.debug("Obteniendo lista de contratos");
		
		List<Contract> contracts = super.searchAll();
		
		LOG.debug("Lista de contratos obtenida satisfactoriamente: {}", contracts);
		
		return contracts;
	}			
	
}
