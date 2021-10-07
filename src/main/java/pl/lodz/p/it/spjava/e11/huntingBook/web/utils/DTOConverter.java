package pl.lodz.p.it.spjava.e11.huntingBook.web.utils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import pl.lodz.p.it.spjava.e11.huntingBook.dto.AccountDTO;
import pl.lodz.p.it.spjava.e11.huntingBook.dto.AdministratorDTO;
import pl.lodz.p.it.spjava.e11.huntingBook.dto.HunterDTO;
import pl.lodz.p.it.spjava.e11.huntingBook.dto.MasterOfTheHunterDTO;
import pl.lodz.p.it.spjava.e11.huntingBook.model.Account;
import pl.lodz.p.it.spjava.e11.huntingBook.model.Administrator;
import pl.lodz.p.it.spjava.e11.huntingBook.model.Hunter;
import pl.lodz.p.it.spjava.e11.huntingBook.model.MasterOfTheHunter;

public class DTOConverter {

    public static AccountDTO createAccountDTOFromEntity(Account account) {

        if (account instanceof Hunter) {
            return createHunterDTOFromEntity((Hunter) account);
        }

        if (account instanceof MasterOfTheHunter) {
            return createMOTHunterDTOFromEntity((MasterOfTheHunter) account);
        }

        if (account instanceof Administrator) {
            return createAdministratorDTOFromEntity((Administrator) account);
        }
        return null;
    }

    private static HunterDTO createHunterDTOFromEntity(Hunter hunter) {
        return null == hunter ? null : new HunterDTO(hunter.getPesel(), hunter.getPhoneNumber(), hunter.getId(), hunter.getLogin(), hunter.getIsActive(), hunter.getName(), hunter.getSurname(), hunter.getEmail(), hunter.getType());
    }

    private static MasterOfTheHunterDTO createMOTHunterDTOFromEntity(MasterOfTheHunter mOTHunter) {
        return null == mOTHunter ? null : new MasterOfTheHunterDTO(mOTHunter.getContactNumber(), mOTHunter.getId(), mOTHunter.getLogin(), mOTHunter.getIsActive(), mOTHunter.getName(), mOTHunter.getSurname(), mOTHunter.getEmail(), mOTHunter.getType());
    }

    private static AdministratorDTO createAdministratorDTOFromEntity(Administrator admin) {
        return null == admin ? null : new AdministratorDTO(admin.getAlarmNumber(), admin.getId(), admin.getLogin(), admin.getIsActive(), admin.getName(), admin.getSurname(), admin.getEmail(), admin.getType());
    }

    public static List<AccountDTO> createAccountDTOListFromEntity(List<Account> accounts) {
        return null == accounts ? null : accounts.stream()
                .filter(Objects::nonNull)
                .map(acc -> DTOConverter.createAccountDTOFromEntity(acc))
                .collect(Collectors.toList());
    }

}
