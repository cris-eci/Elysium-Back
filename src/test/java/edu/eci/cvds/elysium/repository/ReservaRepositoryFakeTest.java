package edu.eci.cvds.elysium.repository;

import edu.eci.cvds.elysium.model.DiaSemanaModel;
import edu.eci.cvds.elysium.model.EstadoReservaModel;
import edu.eci.cvds.elysium.model.ReservaModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

public class ReservaRepositoryFakeTest {

    private FakeReservaRepository fakeRepo;

    @BeforeEach
    public void setUp() {
        fakeRepo = new FakeReservaRepository();
    }

    @Test
    public void testSaveAndFindById() {
        LocalDate fecha = LocalDate.of(2025, 3, 10);
        ReservaModel reserva = new ReservaModel(fecha, DiaSemanaModel.LUNES, "Clase de Programación", "SALON-101", true);
        reserva.setEstado(EstadoReservaModel.ACTIVA);
        
        ReservaModel saved = fakeRepo.save(reserva);
        Optional<ReservaModel> found = fakeRepo.findById(saved.getIdReserva());
        assertTrue(found.isPresent());
        assertEquals("SALON-101", found.get().getIdSalon());
    }

    @Test
    public void testFindByIdLaboratorioAndFechaReserva() {
        LocalDate fecha = LocalDate.of(2025, 3, 10);
        ReservaModel reserva = new ReservaModel(fecha, DiaSemanaModel.LUNES, "Clase de Programación", "SALON-101", true);
        reserva.setEstado(EstadoReservaModel.ACTIVA);
        fakeRepo.save(reserva);
        
        List<ReservaModel> reservas = fakeRepo.findByIdSalonAndFechaReserva("SALON-101", fecha);
        assertFalse(reservas.isEmpty());
        assertEquals("Clase de Programación", reservas.get(0).getProposito());
    }

    @Test
    public void testDeleteReserva() {
        LocalDate fecha = LocalDate.of(2025, 3, 10);
        ReservaModel reserva = new ReservaModel(fecha, DiaSemanaModel.LUNES, "Clase de Programación", "SALON-101", true);
        reserva.setEstado(EstadoReservaModel.ACTIVA);
        ReservaModel saved = fakeRepo.save(reserva);
        assertTrue(fakeRepo.existsById(saved.getIdReserva()));
        fakeRepo.delete(saved);
        assertFalse(fakeRepo.existsById(saved.getIdReserva()));
    }
}
