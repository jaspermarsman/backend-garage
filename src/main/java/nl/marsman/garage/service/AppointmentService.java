package nl.marsman.garage.service;

import nl.marsman.garage.dto.AppointmentRequestDto;
import nl.marsman.garage.exception.RecordNotFoundException;
import nl.marsman.garage.model.Appointment;
import nl.marsman.garage.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public Iterable<Appointment> getAppointments(LocalDate appointmentDate) {

            return appointmentRepository.findAllByAppointmentDate_Chronology(appointmentDate);
    }


    public Appointment getAppointments(int id) {
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(id);

        if (optionalAppointment.isPresent()) {
            return optionalAppointment.get();
        }
        else {
            throw new RecordNotFoundException("ID does not exist!!!");
        }

    }

    public void deleteAppointment(int id) {
        if (appointmentRepository.existsById(id)) {
            appointmentRepository.deleteById(id);
        }
        else {
            throw new RecordNotFoundException("ID does not exist!!!");
        }
    }


    public int addAppointment(AppointmentRequestDto appointmentRequestDto) {

        Appointment appointment = new Appointment();
        appointment.setAppointmentDate(appointmentRequestDto.getAppointmentDate());


        Appointment newAppointment = appointmentRepository.save(appointment);
        return newAppointment.getId();
    }

    public void updateAppointment(int id, Appointment appointment) {
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(id);

        if (optionalAppointment.isPresent()) {
            Appointment storedAppointment = optionalAppointment.get();

            appointment.setId(storedAppointment.getId());
            appointmentRepository.save(appointment);
        }
        else {
            throw new RecordNotFoundException("ID does not exist!!!");
        }
    }

    public void partialUpdateAppointment(int id, Appointment appointment) {
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(id);

        if (optionalAppointment.isPresent()) {
            Appointment storedAppointment = appointmentRepository.findById(id).orElse(null);

            appointmentRepository.save(appointment);

        }
        else {
            throw new RecordNotFoundException("ID does not exist!!!");
        }
    }


}
