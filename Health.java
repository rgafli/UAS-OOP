import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.Scanner;

class Patient {
    private Date birthDate;
    private String fullName;
    private String email;
    private String counsellingType;

    public Patient(String input) throws ParseException {
        String[] data = input.split(" - ");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        this.birthDate = dateFormat.parse(data[0]);
        this.fullName = data[1];
        this.email = data[2];
        this.counsellingType = data[3];
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getCounsellingType() {
        return counsellingType;
    }

    public int getAge() {
        Calendar dob = Calendar.getInstance();
        dob.setTime(birthDate);
        Calendar today = Calendar.getInstance();
        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)) {
            age--;
        }
        return age;
    }
}

class MentalHealthApp {
    private ArrayList<Patient> patients = new ArrayList<>();

    public void addNewPatient(String input) throws ParseException {
        Patient patient = new Patient(input);
        patients.add(patient);
    }

    public void listPatients() {
        if (patients.isEmpty()) {
            System.out.println("Data not found!");
        } else {
            for (Patient patient : patients) {
                System.out.println("Name: " + patient.getFullName());
                System.out.println("Birth Date: " + patient.getBirthDate());
                System.out.println("Email: " + patient.getEmail());
                System.out.println("Counselling Type:(face to face/online):  " + patient.getCounsellingType());
                System.out.println();
            }
        }
    }

    public void distributeCure() {
        if (patients.isEmpty()) {
            System.out.println("Data not found!");
        } else {
            patients.sort(Comparator.comparing(Patient::getAge));
            Patient youngestPatient = patients.remove(0);
            System.out.println("Distributed cure to: " + youngestPatient.getFullName());
        }
    }
}

public class Health {
    public static void main(String[] args) throws ParseException {
        Scanner scanner = new Scanner(System.in);
        MentalHealthApp app = new MentalHealthApp();

        while (true) {
            System.out.println("1. Add New Patient");
            System.out.println("2. List Patients");
            System.out.println("3. Distribute Cure");
            System.out.println("4. Exit");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            switch (choice) {
                case 1:
                    System.out.print("Enter patient details (dd/mm/yyyy - Full Name - email - counselling): ");
                    String patientInput = scanner.nextLine();
                    app.addNewPatient(patientInput);
                    break;
                case 2:
                    app.listPatients();
                    break;
                case 3:
                    app.distributeCure();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please select again.");
            }
        }
    }
}
