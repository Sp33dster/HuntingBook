package pl.lodz.p.it.spjava.e11.huntingBook.web.utils;

import pl.lodz.p.it.spjava.e11.huntingBook.dto.AccountDTO;
import pl.lodz.p.it.spjava.e11.huntingBook.dto.HunterDTO;

public class AccountUtils {
    
//    public static boolean isAdministrator(AccountDTO accountDTO){
//        return (accountDTO instanceof AdministratorDTO);
//    }
    
    public static boolean isHunter(AccountDTO accountDTO){
        return (accountDTO instanceof HunterDTO);
    }
    
//    public static boolean isMasterOfTheHunter(AccountDTO accountDTO){
//        return (accountDTO instanceof MasterOfTheHunterDTO);
//    }
}
