package edu.eci.cvds.elysium.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import edu.eci.cvds.elysium.model.*;
import edu.eci.cvds.elysium.repository.ReservaRepository;


public class ReservaServiceImplTest {

    @Mock
    private ReservaRepository reservaRepository;

    @InjectMocks
    private ReservaServiceImpl reservaService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testConsultarReservas() {
        List<ReservaModel> reservas = Arrays.asList(new ReservaModel(), new ReservaModel());
        when(reservaRepository.findAll()).thenReturn(reservas);

        List<ReservaModel> result = reservaService.consultarReservas();
        assertEquals(2, result.size());
    }

    @Test
    public void testConsultarReservasPorSalon() {
        SalonModel salon = new SalonModel();
        List<ReservaModel> reservas = Arrays.asList(new ReservaModel(), new ReservaModel());
        when(reservaRepository.findByIdSalon(salon)).thenReturn(reservas);

        List<ReservaModel> result = reservaService.consultarReservasPorSalon(salon);
        assertEquals(2, result.size());
    }

    @Test
    public void testConsultarReservasPorFecha() {
        LocalDate fecha = LocalDate.now();
        List<ReservaModel> reservas = Arrays.asList(new ReservaModel(), new ReservaModel());
        when(reservaRepository.findByFechaReserva(fecha)).thenReturn(reservas);

        List<ReservaModel> result = reservaService.consultarReservasPorFecha(fecha);
        assertEquals(2, result.size());
    }

    @Test
    public void testConsultarReservasPorDiaSemana() {
        DiaSemanaModel diaSemana = mock(DiaSemanaModel.class);
        List<ReservaModel> reservas = Arrays.asList(new ReservaModel(), new ReservaModel());
        when(reservaRepository.findByDiaSemana(diaSemana)).thenReturn(reservas);

        List<ReservaModel> result = reservaService.consultarReservasPorDiaSemana(diaSemana);
        assertEquals(2, result.size());
    }

    @Test
    public void testConsultarReservasPorEstado() {
        EstadoReservaModel estado = mock(EstadoReservaModel.class);
        List<ReservaModel> reservas = Arrays.asList(new ReservaModel(), new ReservaModel());
        when(reservaRepository.findByEstado(estado)).thenReturn(reservas);

        List<ReservaModel> result = reservaService.consultarReservasPorEstado(estado);
        assertEquals(2, result.size());
    }

    @Test
    public void testConsultarReservasPorDuracionBloque() {
        boolean duracionBloque = true;
        List<ReservaModel> reservas = Arrays.asList(new ReservaModel(), new ReservaModel());
        when(reservaRepository.findByDuracionBloque(duracionBloque)).thenReturn(reservas);

        List<ReservaModel> result = reservaService.consultarReservasPorDuracionBloque(duracionBloque);
        assertEquals(2, result.size());
    }

    @Test
    public void testConsultarReserva() {
        String idReserva = "1";
        ReservaModel reserva = new ReservaModel();
        when(reservaRepository.findByIdReserva(idReserva)).thenReturn(reserva);

        ReservaModel result = reservaService.consultarReserva(idReserva);
        assertNotNull(result);
    }

    @Test
    public void testCrearReserva() {
        String idReserva = "1";
        LocalDate fechaReserva = LocalDate.now();
        DiaSemanaModel diaSemana = mock(DiaSemanaModel.class);
        String proposito = "Meeting";
        SalonModel salon = new SalonModel();
        boolean duracionBloque = true;

        reservaService.crearReserva(idReserva, fechaReserva, diaSemana, proposito, salon, duracionBloque);
        verify(reservaRepository, times(1)).save(any(ReservaModel.class));
    }

    @Test
    public void testActualizarReserva() {
        String idReserva = "1";
        char tipoCampo = 'f';
        LocalDate value1 = LocalDate.now();
        DiaSemanaModel value2 = mock(DiaSemanaModel.class);
        SalonModel value3 = new SalonModel();
        boolean value4 = true;

        ReservaModel reserva = new ReservaModel();
        // Set the idReserva so that comparisons in the service do not throw an NPE
        reserva.setIdReserva(idReserva);
        when(reservaRepository.findByIdReserva(idReserva)).thenReturn(reserva);

        reservaService.actualizarReserva(idReserva, tipoCampo, value1, value2, value3, value4);
        verify(reservaRepository, times(1)).save(reserva);
    }


    @Test
    public void testCancelReserva() {
        String idReserva = "1";
        ReservaModel reserva = new ReservaModel();
        reserva.setIdReserva(idReserva);
        when(reservaRepository.findByIdReserva(idReserva)).thenReturn(reserva);

        reservaService.cancelReserva(idReserva);
        verify(reservaRepository, times(1)).save(reserva);
    }

    @Test
    public void testDeleteReserva() {
        String idReserva = "1";
        ReservaModel reserva = new ReservaModel();
        reserva.setIdReserva(idReserva);
        when(reservaRepository.findByIdReserva(idReserva)).thenReturn(reserva);

        reservaService.deleteReserva(idReserva);
        verify(reservaRepository, times(1)).save(reserva);
    }

    @Test
    public void testRechazarReserva() {
        String idReserva = "1";
        ReservaModel reserva = new ReservaModel();
        reserva.setIdReserva(idReserva);
        when(reservaRepository.findByIdReserva(idReserva)).thenReturn(reserva);

        reservaService.rechazarReserva(idReserva);
        verify(reservaRepository, times(1)).save(reserva);
    }

}