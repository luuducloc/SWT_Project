/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package management;

import common.FileHandler;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import view.AccountView;
import view.InterfaceView;
import org.junit.After;
import static org.junit.Assert.*;

/**
 *
 * @author locluu
 */
public class AccountManagementTest {
    FileHandler fileHandler = null;
    InterfaceView view = null;
    AccountView accountView = null;
    AccountManagement account = null;
    List<String> listAccounts = null;
    int accountID;
    String userName;
    String password;
    String rePassword;
    int type, result;
    
    
    public AccountManagementTest() {
        view = null;
        fileHandler = null;
        
    }
    
    @BeforeAll
    public void setUpClass() {
        account = new AccountManagement();
        view = new InterfaceView();
        fileHandler = new FileHandler();
        listAccounts = fileHandler.getListAccounts();
        accountView = new AccountView();
    }
    
    @AfterAll
    public void tearDownClass() {
        view = null;
        fileHandler = null;
        listAccounts = null;
        accountView = null;
        
    }
    
    @Before
    public void setUp() {
        System.out.println("Setup method");
        account = new AccountManagement();
        view = new InterfaceView();
        fileHandler = new FileHandler();
        listAccounts = fileHandler.getListAccounts();
        accountView = new AccountView();
    }
    
    @After
    public void tearDown() {
        System.out.println("End method");
    }

    /**
     * Test of userManagementController method, of class AccountManagement.
     */
    @Test
    public void add() {
        //TC1
        userName = "mrc123";
        password = "c123456";
        rePassword ="c123456";
        type = 1;
        result = account.add(userName, password, rePassword, type);
        assertEquals("Test Case #1: ", result, 3);
        
        //TC2
        userName = "mra123";
        password = "c123456";
        rePassword ="c123456";
        type = 1;
        result = account.add(userName, password, rePassword, type);
        assertEquals("Test Case #2: ", result, 3);
        
        //TC3
        userName = "mrc123";
        password = "123456";
        rePassword ="123456";
        type = 1;
        result = account.add(userName, password, rePassword, type);
        assertEquals("Test Case #3: ", result, 3);
        
        //TC4
        userName = "mrc123";
        password = "c123456";
        rePassword ="c12345";
        type = 1;
        result = account.add(userName, password, rePassword, type);
        assertEquals("Test Case #4: ", result, 3);
        
        //TC5
        userName = "mrc123";
        password = "c123456";
        rePassword ="c123456";
        type = -1;
        result = account.add(userName, password, rePassword, type);
        assertEquals("Test Case #5: ", result, 3);
        
        //TC6
        userName = "mra123";
        password = "c123456";
        rePassword ="c123456";
        type = 0;
        result = account.add(userName, password, rePassword, type);
        assertEquals("Test Case #6: ", result, 3);
        
        //TC7
        userName = "mra123";
        password = "c123456";
        rePassword ="c123456";
        type = 5;
        result = account.add(userName, password, rePassword, type);
        assertEquals("Test Case #7: ", result, 3);
        
    }
    
    public void update() {
        System.out.println("1. Test list:");
        
    }
    
}
