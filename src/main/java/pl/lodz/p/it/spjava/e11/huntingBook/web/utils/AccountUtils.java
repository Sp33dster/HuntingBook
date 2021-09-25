package pl.lodz.p.it.spjava.e11.huntingBook.web.utils;

import org.apache.commons.codec.digest.DigestUtils;
import pl.lodz.p.it.spjava.e11.huntingBook.dto.AccountDTO;
import pl.lodz.p.it.spjava.e11.huntingBook.dto.AdministratorDTO;
import pl.lodz.p.it.spjava.e11.huntingBook.dto.HunterDTO;
import pl.lodz.p.it.spjava.e11.huntingBook.dto.MasterOfTheHunterDTO;

public class AccountUtils {

    public static boolean isAdministrator(AccountDTO accountDTO) {
        return (accountDTO instanceof AdministratorDTO);
    }

    public static boolean isHunter(AccountDTO accountDTO) {
        return (accountDTO instanceof HunterDTO);
    }

    public static boolean isMasterOfTheHunter(AccountDTO accountDTO) {
        return (accountDTO instanceof MasterOfTheHunterDTO);
    }

    public static String createSHAOfPassword(String password) {
        String sha3Hex = new DigestUtils("SHA3-256").digestAsHex(password);
        return sha3Hex;
    }

}
