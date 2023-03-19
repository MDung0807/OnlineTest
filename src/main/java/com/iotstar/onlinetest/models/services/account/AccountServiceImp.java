package com.iotstar.onlinetest.models.services.account;

import com.iotstar.onlinetest.DTO.AccountDTO;
import com.iotstar.onlinetest.models.entities.Account;
import com.iotstar.onlinetest.models.entities.Role;
import com.iotstar.onlinetest.models.entities.Student;
import com.iotstar.onlinetest.models.repositories.AccountDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountServiceImp implements AccountService{
    @Autowired
    private AccountDAO accountDAO;

    private AccountDTO accountDTO;
    private Account account;

    @Override
    public AccountDTO getAccByUsername(String username) {
        account = accountDAO.getByUsername(username);
        accountDTO = AccountDTO.builder()
                .accountId(account.getAccountId())
                .username(account.getUsername())
                .password(account.getPassword())
                .role(account.getRole())
                .student(account.getStudent())
                .build();
        return accountDTO;
    }

    @Override
    public AccountDTO getByStudentName(String name) {
        account = accountDAO.getByUsername(name);
        accountDTO = AccountDTO.builder()
                .accountId(account.getAccountId())
                .username(account.getUsername())
                .password(account.getPassword())
                .role(account.getRole())
                .student(account.getStudent())
                .build();
        return accountDTO;
    }

    @Override
    @Transactional
    public void createAccount(AccountDTO accountDTO){
        String err = null;
        Student student = Student.builder()
                .StudentId(4)
                .email("dominhdung21082002@gmail.com")
                .name("dominhdung").build();

        Role role = Role.builder()
                .roleId(1)
                .roleName("admin").build();

        account = Account.builder()
                .username("êrre")
                .password("abcd")
                .student(student)
                .role(role).build();
        try {
            accountDAO.save(account);
        }
        catch (Exception ex){
           err = ex.getMessage();
        }
    }
}
