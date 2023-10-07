package DataAccess;

import Controller.Validation;
import Model.Doctor;
import java.util.ArrayList;

public class DoctorDao {
    private static DoctorDao instance = null;
    private ArrayList<Doctor> doctorList;
    public DoctorDao() {
        this.doctorList = new ArrayList<>();
    }
    public static DoctorDao instance() {
        if (instance == null) {
            synchronized (DoctorDao.class) {
                if (instance == null) {
                    instance = new DoctorDao();
                }
            }
        }
        return instance;
    }
    public void addupdateDoctor() {
        System.out.print("Enter code: ");
        String code = Validation.checkInputString();
        if (!Validation.checkCodeExist(doctorList, code)) {
            System.out.println("Code exist. Therefore, updating confirmed.");
            Doctor doctor = getDoctorByCode(code);

            System.out.printf("%-10s%-15s%-25s%-20s\n", "Code", "Name",
                    "Specialization", "Availability");
            System.out.printf("%-10s%-15s%-25s%-20d\n", doctor.getCode(),
                    doctor.getName(), doctor.getSpecialization(),
                    doctor.getAvailability());
            System.out.println("\n");
            if (!Validation.checkInputYN()) return;
            System.out.println("\n");
            System.out.println("--------- Update Doctor ----------");
            System.out.print("Enter name: ");
            String name = Validation.checkInputString();
            System.out.print("Enter specialization: ");
            String specialization = Validation.checkInputString();
            System.out.print("Enter availability: ");
            int availability = Validation.checkInputInt();
            if (!Validation.checkChangeInfo(doctor, code, name, specialization, availability)) {
                System.err.println("No change");
                return;
            }
            doctor.setName(name);
            doctor.setSpecialization(specialization);
            doctor.setAvailability(availability);
            System.out.println("Update successful");
            System.out.println("\n");
            return;
        }
        System.out.println("\n");
        System.out.println("--------- Add Doctor -------");
        System.out.print("Enter name: ");
        String name = Validation.checkInputString();
        System.out.print("Enter specialization: ");
        String specialization = Validation.checkInputString();
        System.out.print("Enter availability: ");
        int availability = Validation.checkInputInt();
        if (!Validation.checkDuplicate(doctorList, code, name, specialization, availability)) {
            System.err.println("Duplicate.");
            return;
        }
        doctorList.add(new Doctor(code, name, specialization, availability));
        System.out.println("Add successful.");
        System.out.println("\n");
    }

    public void deleteDoctor() {
        System.out.println("\n");
        System.out.println("--------- Delete Doctor -------");
        System.out.print("Enter code: ");
        String code = Validation.checkInputString();
        Doctor doctor = getDoctorByCode(code);
        if (doctor == null) {
            System.err.println("Not found doctor.");
            return;
        } else {
            doctorList.remove(doctor);
        }
        System.out.println("Delete successful.");
        System.out.println("\n");
    }

    public void searchDoctor() {
        System.out.println("\n");
        System.out.println("---------- Search Doctor --------");
        System.out.print("Enter name: ");
        String nameSearch = Validation.checkInputString();
        ArrayList<Doctor> listFoundByName = listFoundByName(nameSearch);
        if (listFoundByName.isEmpty()) {
            System.err.println("List empty.");
        } else {
            System.out.println("--------------------- Result -----------------------------");
            System.out.printf("%-10s%-15s%-25s%-20s\n", "Code", "Name",
                    "Specialization", "Availability");
            for (Doctor doctor : listFoundByName) {
                System.out.printf("%-10s%-15s%-25s%-20d\n", doctor.getCode(),
                        doctor.getName(), doctor.getSpecialization(),
                        doctor.getAvailability());
            }
        }
        System.out.println("\n");
    }

    public Doctor getDoctorByCode(String code) {
        for (Doctor doctor : doctorList) {
            if (doctor.getCode().equalsIgnoreCase(code)) {
                return doctor;
            }
        }
        return null;
    }

    public ArrayList<Doctor> listFoundByName(String name) {
        ArrayList<Doctor> listFoundByName = new ArrayList<>();
        for (Doctor doctor : doctorList) {
            if (doctor.getName().contains(name)) {
                listFoundByName.add(doctor);
            }
        }
        return listFoundByName;
    }
}
