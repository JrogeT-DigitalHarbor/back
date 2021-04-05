package jrogetdigitalharbor.back.controllers;

import jrogetdigitalharbor.back.RequestModel;
import jrogetdigitalharbor.back.ResponseModel;
import jrogetdigitalharbor.back.SearchingRequest;
import jrogetdigitalharbor.back.models.*;
import jrogetdigitalharbor.back.repositories.DoctorRepository;
import jrogetdigitalharbor.back.repositories.PatientRepository;
import jrogetdigitalharbor.back.repositories.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/doctors")
public class DoctorController extends BaseController {

    private DoctorRepository repository;
    private UserRepository userRepository;
    private PatientRepository patientRepository;

    public DoctorController(
            DoctorRepository repository,
            UserRepository userRepository,
            PatientRepository patientRepository
    ) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.patientRepository = patientRepository;
    }

    @PostMapping("")
    public ResponseModel create(@RequestBody RequestModel<Doctor> request) {
        try {
            userRepository.findById(request.userId).get();
            Doctor newDoctor = new Doctor(request);
            Doctor doctorCreated = repository.save(newDoctor);
            return sendResponse("Doctor created.", doctorCreated);
        } catch (NoSuchElementException e) {
            return sendResponse("User not found.");
        } catch (Exception e) {
            return sendErrorResponse(e);
        }
    }

    @GetMapping("")
    public ResponseModel readAll() {
        List<Doctor> doctorsDoc = this.repository.findAll();
        List<DoctorDTO> doctors = new LinkedList<>();
        for (Doctor doctor : doctorsDoc) {
            List<AppointmentDTO> doctorAppointments = new LinkedList<>();
            List<Patient> patients = patientRepository.findAll();
            for (Patient patient : patients) {
                for (Appointment appointment : patient.appointments) {
                    if (appointment.doctorId.equals(doctor.id)) {
                        doctorAppointments.add(new AppointmentDTO(appointment, patient));
                    }
                }
            }
            doctors.add(new DoctorDTO(doctor, doctorAppointments));
        }
        return sendResponse("doctors found.", doctors);
    }

    @GetMapping("/{id}")
    public ResponseModel readOne(@PathVariable String id) {
        Optional<Doctor> doctorFound = repository.findById(id);
        if (!doctorFound.isPresent()) {
            return sendResponse("Doctor not found.");
        }
        return sendResponse("Doctor found.", doctorFound.get());
    }

    @PutMapping("/{id}")
    public ResponseModel update(@RequestBody RequestModel<Doctor> request, @PathVariable String id) {
        try {
            userRepository.findById(request.userId).get();
            Doctor doctorFound = new Doctor(request);
            return sendResponse("Doctor updated.", this.repository.save(doctorFound));
        } catch (NoSuchElementException e) {
            return sendResponse("User not found.");
        } catch (Exception e) {
            return sendErrorResponse(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseModel delete(@PathVariable String id) {
        if (!repository.findById(id).isPresent()) {
            return sendResponse("Doctor not found.");
        }
        repository.deleteById(id);
        return sendResponse("Doctor deleted.");
    }

    @PostMapping("/search/name")
    public ResponseModel searchName(@RequestBody SearchingRequest request) {
        List<Doctor> doctorsDoc = repository.findByNameLike(request.word);
        List<DoctorDTO> doctors = new LinkedList<>();
        for (Doctor doctor : doctorsDoc) {
            List<AppointmentDTO> doctorAppointments = new LinkedList<>();
            List<Patient> patients = patientRepository.findAll();
            for (Patient patient : patients) {
                for (Appointment appointment : patient.appointments) {
                    if (appointment.doctorId.equals(doctor.id)) {
                        doctorAppointments.add(new AppointmentDTO(appointment, patient));
                    }
                }
            }
            doctors.add(new DoctorDTO(doctor, doctorAppointments));
        }
        return sendResponse("Doctors found.", doctors);
    }

    @PostMapping("/search/lastname")
    public ResponseModel searchLastname(@RequestBody SearchingRequest request) {
        List<Doctor> doctorsDoc = repository.findByLastnameLike(request.word);
        List<DoctorDTO> doctors = new LinkedList<>();
        for (Doctor doctor : doctorsDoc) {
            List<AppointmentDTO> doctorAppointments = new LinkedList<>();
            List<Patient> patients = patientRepository.findAll();
            for (Patient patient : patients) {
                for (Appointment appointment : patient.appointments) {
                    if (appointment.doctorId.equals(doctor.id)) {
                        doctorAppointments.add(new AppointmentDTO(appointment, patient));
                    }
                }
            }
            doctors.add(new DoctorDTO(doctor, doctorAppointments));
        }
        return sendResponse("Doctors found.", doctors);
    }

    @PostMapping("/search/dates")
    public ResponseModel searchDates(@RequestBody SearchingRequest request) {
        List<Doctor> doctorsDoc = repository.findByDateOfBirthBetween(request.dateA, request.dateB);
        List<DoctorDTO> doctors = new LinkedList<>();
        for (Doctor doctor : doctorsDoc) {
            List<AppointmentDTO> doctorAppointments = new LinkedList<>();
            List<Patient> patients = patientRepository.findAll();
            for (Patient patient : patients) {
                for (Appointment appointment : patient.appointments) {
                    if (appointment.doctorId.equals(doctor.id)) {
                        doctorAppointments.add(new AppointmentDTO(appointment, patient));
                    }
                }
            }
            doctors.add(new DoctorDTO(doctor, doctorAppointments));
        }
        return sendResponse("Doctors found.", doctors);
    }
}
