/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import constraint.SystemConstraint;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;
import model.Candidate;

/**
 *
 * @author Dang Phat
 */
public class DataInput {

    private Scanner in = new Scanner(System.in);

//    ?private final  SystemConstraint.PASS_VALID;

//    private final String PHONE_VALID = "^\\d{10}\\d*$";
//
//    private final String NAME_VALID = "^[a-zA-Z\\s]+";
//    
//    private String empty = "Not empty! Please enter again!";

    /*
    [A-Za-z0-9.-+%]+ user must be input from a-z ignore case,0-9 and .-+% least one times
    @ user must be input "@"
    [A-Za-z.-]+ user mustbe input from a-z ignore case, "." "-" least one times
    \\. user must be input "."
    [A-Za-z]{2,4} user must be input from a-z ignore 2 - 4 times
     */
//    private final String EMAIL_VALID
//            = "^[A-Za-z0-9.+-_%]+@[A-Za-z.-]+\\.[A-Za-z]{2,4}$";

    //check user input number limit
    public int checkInputIntLimit(String msg, int min, int max) {
        //loop until user input correct
        while (true) {
            try {
                System.out.println(msg);
                int result = Integer.parseInt(in.nextLine().trim());
                if (result < min || result > max) {
                    throw new NumberFormatException();

                }
                return result;
            } catch (NumberFormatException e) {
                System.err.println("Please input number in rage [" + min + ", " + max + "]\nPlease enter again!");
            }
        }
    }

    //check user input string
    public String checkInputString(String msg) {
        //loop until user input correct
        while (true) {
            System.out.println(msg);
            String result = in.nextLine().trim();
            if (result.isEmpty()) {
                System.err.println(SystemConstraint.EMPTY);
            } else {
                return result;
            }
        }
    }

    public String checkInputSkill(String msg) {
        List<String> listSkills = new FileHandler().getAllDataInFile("skill.txt");
        while (true) {
            System.out.println(msg);
            String result = in.nextLine().trim();
            if (result.isEmpty()) {
                System.err.println(SystemConstraint.EMPTY);
            } else {
                for (String skill : listSkills) {
                    if (skill.split("\\|")[1].equalsIgnoreCase(result)) {
                        return result;
                    }
                }
                System.out.println("Skill does not exist!");
            }
        }
    }

    public boolean checkUserExist(List<String> listAccounts, String name) {
        for (int i = 0; i < listAccounts.size(); i++) {
            if (listAccounts.get(i).split("\\|")[1].equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    // ****
    public String checkUserName(String msg, List<String> listAccounts) {
        while (true) {
            System.out.println(msg);
            String result = in.nextLine().trim();

            if (result.isEmpty()) {
                System.err.println(SystemConstraint.EMPTY);
            } else if (result.length() < 5 || !Character.isLetter(result.charAt(0))) {
                System.out.println("Invalid format! Please enter again!");
            } else if (checkUserExist(listAccounts, result)) {
                System.err.println("User exist!!!");
            } else {
                return result;
            }
        }
    }

    public String checkPassword(String msg) {
        while (true) {
            System.out.println(msg);
            String result = in.nextLine().trim();
            if (result.isEmpty()) {
                System.err.println(SystemConstraint.EMPTY);
            } else if (!result.matches(SystemConstraint.PASS_VALID)) {
                System.out.println("Password must include more than 6 chars, both letter and numbers!");
            } else {
                return result;
            }
        }
    }

    //check user input y/Y or n/N
    public boolean checkInputYN(String msg) {
        //loop until user input correct
        while (true) {
            String result = checkInputString(msg);
            //check user input y/Y or n/N
            if (result.equalsIgnoreCase("Y")) {
                return true;
            } else if (result.equalsIgnoreCase("N")) {
                return false;
            }
            System.err.println("Please input y/Y or n/N.");
            System.out.print("Enter again: ");
        }
    }

    //check phone is number with minimum 10 characters
    public String checkInputPhone(String msg) {
        while (true) {
            String result = checkInputString(msg);
            //check user input phone valid
            if (result.matches(SystemConstraint.PHONE_VALID)) {
                return result;
            } else {
                System.err.println("Phone is number with minimum 10 characters!"
                        + "\nPlease enter again!");
            }
        }
    }

    //check email with format <account name>@<domain>. (eg: annguyen@fpt.edu.vn)
    public String checkInputEmail(String msg) {
        //loop until user input correct
        while (true) {
            String result = checkInputString(msg);
            //check user input email valid
            if (result.matches(SystemConstraint.EMAIL_VALID)) {
                return result;
            } else {
                System.err.println("Email with format <account name>@<domain>"
                        + "\nPlease enter again!");
            }
        }
    }

    //check user input graduation rank
    public String checkInputGraduationRank(String msg) {
        while (true) {
            String result = checkInputString(msg);
            if (result.equalsIgnoreCase("Excellence")
                    || result.equalsIgnoreCase("Good")
                    || result.equalsIgnoreCase("Fair")
                    || result.equalsIgnoreCase("Poor")) {
                return result;
            } else {
                System.err.println("Please input string: Excellence, Good, Fair, Poor");
            }
        }
    }

    //check id exist or not
    public boolean checkIdExist(ArrayList<Candidate> candidates, int id) {
        for (Candidate candidate : candidates) {
            if (candidate.getId() == id) {
                System.err.println("Id exist.");
                return false;
            }
        }
        return true;
    }

    //check experience must be smaller then age
    public int checkInputExprience(String msg, int birthDate) {
        int yearCurrent = Calendar.getInstance().get(Calendar.YEAR);
        int age = yearCurrent - birthDate;
        while (true) {
            int yearExperience = checkInputIntLimit(msg, 1, age); //từ 1 đến số tuổi, năm kinh nghiệm phải nhỏ hơn số tuổi
            if (yearExperience > age) {
                System.err.println("Experience must be smaller than age! Please enter again!");
            } else {
                return yearExperience;
            }
        }

    }

//new function
    public String checkNameInput(String msg) {
        while (true) {
            System.out.println(msg);
            String result = in.nextLine().trim();
            if (result.isEmpty()) {
                System.err.println(SystemConstraint.EMPTY);
            } else if (!result.matches(SystemConstraint.NAME_VALID)) {
                System.out.println("Name mustn't include number or special character!");
            } else {
                return result;
            }
        }
    }

}
