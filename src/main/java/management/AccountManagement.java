/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package management;

import common.DataInput;
import common.FileHandler;
import java.util.List;
import model.User;
import view.AccountView;
import view.InterfaceView;
import view.Message;

/**
 *
 * @author Dang Phat
 */
public class AccountManagement {

    FileHandler fileHandler = new FileHandler();

    private final InterfaceView view = new InterfaceView();
    List<String> listAccounts = null;
    DataInput dataInput = new DataInput();

    private void writeFile(List<String> list) {
        fileHandler.saveAccount(list);
    }

    private void list() {
        listAccounts = fileHandler.getListAccounts();
        Message m = new Message();
        m.AccountManagementMessage(2, "");
        m.AccountManagementMessage(3, "");

        listAccounts.stream().map(account -> account.split("\\|")).forEachOrdered(accInfo -> {
            String type = accInfo[3].equals("1") ? "Admin" : "Memeber";
            m.listAccountDisplay(1, accInfo[0], accInfo[1], type);

        });
    }

    private void add() {
        listAccounts = fileHandler.getListAccounts();
        User newUser = new AccountView().createUserInfo(listAccounts);
        listAccounts.add(newUser.getUserCode() + "|" + newUser.getUserName() + "|" + newUser.getPassword() + "|" + newUser.getType());
        writeFile(listAccounts);
        Message m = new Message();
        m.AccountManagementMessage(1, "Add ");
    }

    public int add(String userName, String password, String rePass, int type) {
        listAccounts = fileHandler.getListAccounts();
        User newUser = new AccountView().createUser(listAccounts, userName, password, rePass, type);
        if (newUser != null) {
            listAccounts.add(newUser.getUserCode() + "|" + newUser.getUserName() + "|" + newUser.getPassword() + "|" + newUser.getType());

            return listAccounts.size();
        }
        return listAccounts.size();
    }

    private void update() {
        System.out.println(delete(6));
        Message m = new Message();
        listAccounts = fileHandler.getListAccounts();
        User newUser = new AccountView().updateUser(listAccounts);
        for (int i = 0; i < listAccounts.size(); i++) {
            if (listAccounts.get(i).split("\\|")[0].equals(newUser.getUserCode() + "")) {
                listAccounts.set(i, listAccounts.get(i).substring(0, listAccounts.get(i).lastIndexOf("|") + 1) + newUser.getType());
                break;
            }
        }
        writeFile(listAccounts);
        m.AccountManagementMessage(1, "Update ");
    }

    public boolean update(int code, int type) {
        listAccounts = fileHandler.getListAccounts();
        User newUser = new AccountView().updateUser1(listAccounts, code, type);
        if (newUser != null) {
            if (newUser != null) {
                for (int i = 0; i < listAccounts.size(); i++) {
                    if (listAccounts.get(i).split("\\|")[0].equals(newUser.getUserCode() + "")) {
                        listAccounts.set(i, listAccounts.get(i).substring(0, listAccounts.get(i).lastIndexOf("|") + 1) + newUser.getType());
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private void delete() {
        listAccounts = fileHandler.getListAccounts();
        Message m = new Message();
        int userCode = dataInput.checkInputIntLimit("Enter user code to delete: ", 1, listAccounts.size());
        System.out.println(userCode);
        for (String account : listAccounts) {
            if (Integer.parseInt(account.split("\\|")[0]) == userCode) {
                listAccounts.remove(userCode - 1);
                break;
            }
        }
        for (int i = userCode - 1; i < listAccounts.size(); i++) {
            String candidateUpdated = (i + 1) + listAccounts.get(i).substring(listAccounts.get(i).indexOf("|"));
            listAccounts.set(i, candidateUpdated);
        }
        writeFile(listAccounts);
        m.AccountManagementMessage(1, "Delete ");
    }

    public int delete(int code) {
        System.out.println("code: " + code);
        listAccounts = fileHandler.getListAccounts();
        if (code >= 1 || code <= listAccounts.size()) {
            for (String account : listAccounts) {
                if (Integer.parseInt(account.split("\\|")[0]) == code) {
                    listAccounts.remove(code - 1);
                    return listAccounts.size();
                }
            }
            for (int i = code - 1; i < listAccounts.size(); i++) {
                String candidateUpdated = (i + 1) + listAccounts.get(i).substring(listAccounts.get(i).indexOf("|"));
                listAccounts.set(i, candidateUpdated);
                return listAccounts.size();
            }
        }

        return listAccounts.size();
    }

    private void changePassword() {
        Message m = new Message();
        listAccounts = fileHandler.getListAccounts();
        User newUser = new AccountView().changePassword(listAccounts);
        for (int i = 0; i < listAccounts.size(); i++) {
            String[] accInfo = listAccounts.get(i).split("\\|");
            if (accInfo[0].equals(newUser.getUserCode() + "")) {
                listAccounts.set(i, accInfo[0] + "|" + accInfo[1] + "|" + newUser.getPassword() + "|" + accInfo[3]);
                break;
            }
        }
        writeFile(listAccounts);
        m.AccountManagementMessage(1, "Change password");
    }

    public void userManagementController() {
        int userManagementChoice = view.userManagement();
        switch (userManagementChoice) {
            case 1:
                list();
                break;
            case 2:
                add();
                break;
            case 3:
                update();
                break;
            case 4:
                delete();
                break;
            case 5:
                changePassword();
                break;
            default:
                break;
        }
    }
}
