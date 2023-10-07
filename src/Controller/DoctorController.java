package Controller;

import View.Menu;
import Repository.DoctorRepository;

public class DoctorController extends Menu {
    private DoctorRepository doctorRepository;
    static String[] options = {"Add or Update doctor", "Delete doctor", "Search doctor", "Exit"};
    public DoctorController() {
        super("========= Doctor Management =========", options);
        doctorRepository = new DoctorRepository();
    }
    public void execute(int choice) {
            switch (choice) {
                case 1:
                    doctorRepository.addupdateDoctor();
                    break;
                case 2:
                    doctorRepository.deleteDoctor();
                    break;
                case 3:
                    doctorRepository.searchDoctor();
                    break;
                case 4:
                    System.out.println("Exit");
            }
    }
}



