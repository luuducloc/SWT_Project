package constraint;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author s1tha
 */
public class SystemConstraint {

    public static final String SKILL = "skill.txt";
    public static final String SKILL_CANDIDATE = "skill candidate.txt";
    public static final String CANDIDATE = "candidate.txt";
    public static String PASS_VALID = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$";
    public static String PHONE_VALID = "^\\d{10}\\d*$";
    public static String NAME_VALID = "^[a-zA-Z\\s]+";
    public static String EMPTY = "Not empty! Please enter again!";
    public static String EMAIL_VALID
            = "^[A-Za-z0-9.+-_%]+@[A-Za-z.-]+\\.[A-Za-z]{2,4}$";
    
}
