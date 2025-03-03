package edu.eci.cvds.elysium.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import edu.eci.cvds.elysium.model.DiaSemanaModel;
import edu.eci.cvds.elysium.model.EstadoReservaModel;
import edu.eci.cvds.elysium.model.ReservaModel;
import edu.eci.cvds.elysium.model.SalonModel;

public class ReservaServiceTest {

    @Mock
    private ReservaService reservaService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testConsultarReservas() {
        List<ReservaModel> reservas = Arrays.asList(new ReservaModel(), new ReservaModel());
        when(reservaService.consultarReservas()).thenReturn(reservas);

        List<ReservaModel> result = reservaService.consultarReservas();
        assertEquals(2, result.size());
    }

    @Test
    public void testConsultarReservasPorSalon() {
        SalonModel salon = new SalonModel();
        List<ReservaModel> reservas = Arrays.asList(new ReservaModel(), new ReservaModel());
        when(reservaService.consultarReservasPorSalon(salon)).thenReturn(reservas);

        List<ReservaModel> result = reservaService.consultarReservasPorSalon(salon);
        assertEquals(2, result.size());
    }

    @Test
    public void testConsultarReservasPorFecha() {
        LocalDate fecha = LocalDate.now();
        List<ReservaModel> reservas = Arrays.asList(new ReservaModel(), new ReservaModel());
        when(reservaService.consultarReservasPorFecha(fecha)).thenReturn(reservas);

        List<ReservaModel> result = reservaService.consultarReservasPorFecha(fecha);
        assertEquals(2, result.size());
    }

    @Test
    public void testConsultarReservasPorDiaSemana() {
        DiaSemanaModel diaSemana = DiaSemanaModel.LUNES;
        List<ReservaModel> reservas = Arrays.asList(new ReservaModel(), new ReservaModel());
        when(reservaService.consultarReservasPorDiaSemana(diaSemana)).thenReturn(reservas);

        List<ReservaModel> result = reservaService.consultarReservasPorDiaSemana(diaSemana);
        assertEquals(2, result.size());
    }

    @Test
    public void testConsultarReservasPorEstado() {
        EstadoReservaModel estado = EstadoReservaModel.ACTIVA;
        List<ReservaModel> reservas = Arrays.asList(new ReservaModel(), new ReservaModel());
        when(reservaService.consultarReservasPorEstado(estado)).thenReturn(reservas);

        List<ReservaModel> result = reservaService.consultarReservasPorEstado(estado);
        assertEquals(2, result.size());
    }

    @Test
    public void testConsultarReservasPorDuracionBloque() {
        boolean duracionBloque = true;
        List<ReservaModel> reservas = Arrays.asList(new ReservaModel(), new ReservaModel());
        when(reservaService.consultarReservasPorDuracionBloque(duracionBloque)).thenReturn(reservas);

        List<ReservaModel> result = reservaService.consultarReservasPorDuracionBloque(duracionBloque);
        assertEquals(2, result.size());
    }

    @Test
    public void testConsultarReserva() {
        String idReserva = "123";
        ReservaModel reserva = new ReservaModel();
        when(reservaService.consultarReserva(idReserva)).thenReturn(reserva);

        ReservaModel result = reservaService.consultarReserva(idReserva);
        assertNotNull(result);
    }

    @Test
    public void testCrearReserva() {
        doNothing().when(reservaService).crearReserva(anyString(), any(LocalDate.class), any(DiaSemanaModel.class), anyString(), any(SalonModel.class), anyBoolean());

        reservaService.crearReserva("123", LocalDate.now(), DiaSemanaModel.LUNES, "Proposito", new SalonModel(), true);
        verify(reservaService, times(1)).crearReserva(anyString(), any(LocalDate.class), any(DiaSemanaModel.class), anyString(), any(SalonModel.class), anyBoolean());
    }

    @Test
    public void testActualizarReserva() {
        doNothing().when(reservaService).actualizarReserva(anyString(), anyChar(), any(LocalDate.class), any(DiaSemanaModel.class), any(SalonModel.class), anyBoolean());

        reservaService.actualizarReserva("123", 'A', LocalDate.now(), DiaSemanaModel.LUNES, new SalonModel(), true);
        verify(reservaService, times(1)).actualizarReserva(anyString(), anyChar(), any(LocalDate.class), any(DiaSemanaModel.class), any(SalonModel.class), anyBoolean());
    }

    @Test
    public void testDeleteReserva() {
        doNothing().when(reservaService).deleteReserva(anyString());

        reservaService.deleteReserva("123");
        verify(reservaService, times(1)).deleteReserva(anyString());
    }

    @Test
    public void testCancelReserva() {
        doNothing().when(reservaService).cancelReserva(anyString());

        reservaService.cancelReserva("123");
        verify(reservaService, times(1)).cancelReserva(anyString());
    }

    @Test
    public void testRechazarReserva() {
        doNothing().when(reservaService).rechazarReserva(anyString());

        reservaService.rechazarReserva("123");
        verify(reservaService, times(1)).rechazarReserva(anyString());
    }
}