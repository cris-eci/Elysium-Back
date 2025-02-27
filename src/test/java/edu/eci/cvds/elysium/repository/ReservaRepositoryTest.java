package edu.eci.cvds.elysium.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import edu.eci.cvds.elysium.model.DiaSemanaModel;
import edu.eci.cvds.elysium.model.EstadoReservaModel;
import edu.eci.cvds.elysium.model.ReservaModel;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
class ReservaRepositoryTest {

    @Autowired
    private ReservaRepository reservaRepository;

    @AfterEach
    void cleanUp() {
        reservaRepository.deleteAll();
    }

    @Test
    void testSaveAndFindById() {
        ReservaModel reserva = new ReservaModel();
        reserva.setIdReserva("RES-100");
        reserva.setFechaReserva(LocalDate.of(2025, 3, 10));
        reserva.setHoraInicio(LocalTime.of(10, 0));
        reserva.setHoraFin(LocalTime.of(12, 0));
        reserva.setDiaSemana(DiaSemanaModel.LUNES);
        reserva.setProposito("Clase de Programación");
        reserva.setIdLaboratorio("LAB-101");
        reserva.setIdUsuario("USER-001");
        reserva.setEstado(EstadoReservaModel.RESERVADA);

        reservaRepository.save(reserva);

        Optional<ReservaModel> found = reservaRepository.findById("RES-100");
        assertTrue(found.isPresent());
        assertEquals("USER-001", found.get().getIdUsuario());
    }

    @Test
    void testFindByIdLaboratorioAndFechaReserva() {
        ReservaModel reserva = new ReservaModel();
        reserva.setIdReserva("RES-101");
        reserva.setFechaReserva(LocalDate.of(2025, 3, 10));
        reserva.setHoraInicio(LocalTime.of(10, 0));
        reserva.setHoraFin(LocalTime.of(12, 0));
        reserva.setDiaSemana(DiaSemanaModel.LUNES);
        reserva.setProposito("Clase de Programación");
        reserva.setIdLaboratorio("LAB-101");
        reserva.setIdUsuario("USER-001");
        reserva.setEstado(EstadoReservaModel.RESERVADA);

        reservaRepository.save(reserva);

        List<ReservaModel> reservas = reservaRepository.findByIdLaboratorioAndFechaReserva("LAB-101", LocalDate.of(2025, 3, 10));
        assertFalse(reservas.isEmpty());
        assertEquals("RES-101", reservas.get(0).getIdReserva());
    }

    @Test
    void testDeleteReserva() {
        ReservaModel reserva = new ReservaModel();
        reserva.setIdReserva("RES-102");
        reserva.setFechaReserva(LocalDate.of(2025, 3, 10));
        reserva.setHoraInicio(LocalTime.of(10, 0));
        reserva.setHoraFin(LocalTime.of(12, 0));
        reserva.setDiaSemana(DiaSemanaModel.LUNES);
        reserva.setProposito("Clase de Programación");
        reserva.setIdLaboratorio("LAB-101");
        reserva.setIdUsuario("USER-001");
        reserva.setEstado(EstadoReservaModel.RESERVADA);

        reservaRepository.save(reserva);
        reservaRepository.delete(reserva);

        Optional<ReservaModel> found = reservaRepository.findById("RES-102");
        assertFalse(found.isPresent());
    }
}
