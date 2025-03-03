// package edu.eci.cvds.elysium.repository;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertNotNull;
// import java.time.LocalDate;
// import java.util.List;
// import edu.eci.cvds.elysium.model.DiaSemanaModel;
// import edu.eci.cvds.elysium.model.EstadoReservaModel;
// import edu.eci.cvds.elysium.model.ReservaModel;
// import edu.eci.cvds.elysium.model.SalonModel;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
// import org.springframework.test.context.ActiveProfiles;
// import org.springframework.test.context.TestPropertySource;

// @TestPropertySource(locations = "classpath:application-test.properties")
// @DataMongoTest
// public class ReservaRepositoryTest {

//     @Autowired
//     private ReservaRepository reservaRepository;

//     private ReservaModel reserva;

//     @BeforeEach
//     public void setUp() {
//         reservaRepository.deleteAll();
//         reserva = new ReservaModel();
//         reserva.setIdReserva("1");
//         reserva.setIdSalon(new SalonModel("1"));
//         reserva.setFechaReserva(LocalDate.now());
//         reserva.setDiaSemana(DiaSemanaModel.LUNES);
//         reserva.setDuracionBloque(true);
//         reserva.setEstado(EstadoReservaModel.ACTIVA);
//         reservaRepository.save(reserva);
//     }

//     @Test
//     public void testFindByIdReserva() {
//         ReservaModel found = reservaRepository.findByIdReserva("1");
//         assertNotNull(found);
//         assertEquals("1", found.getIdReserva());
//     }

//     @Test
//     public void testFindAll() {
//         List<ReservaModel> reservas = reservaRepository.findAll();
//         assertNotNull(reservas);
//         assertEquals(1, reservas.size());
//     }

//     @Test
//     public void testFindByIdSalon() {
//         List<ReservaModel> reservas = reservaRepository.findByIdSalon(new SalonModel("1"));
//         assertNotNull(reservas);
//         assertEquals(1, reservas.size());
//     }

//     @Test
//     public void testFindByFechaReserva() {
//         List<ReservaModel> reservas = reservaRepository.findByFechaReserva(LocalDate.now());
//         assertNotNull(reservas);
//         assertEquals(1, reservas.size());
//     }

//     @Test
//     public void testFindByDiaSemana() {
//         List<ReservaModel> reservas = reservaRepository.findByDiaSemana(DiaSemanaModel.LUNES);
//         assertNotNull(reservas);
//         assertEquals(1, reservas.size());
//     }

//     @Test
//     public void testFindByDuracionBloque() {
//         List<ReservaModel> reservas = reservaRepository.findByDuracionBloque(true);
//         assertNotNull(reservas);
//         assertEquals(1, reservas.size());
//     }

//     @Test
//     public void testFindByEstado() {
//         List<ReservaModel> reservas = reservaRepository.findByEstado(EstadoReservaModel.ACTIVA);
//         assertNotNull(reservas);
//         assertEquals(1, reservas.size());
//     }
// }