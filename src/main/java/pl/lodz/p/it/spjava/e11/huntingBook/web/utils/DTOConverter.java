package pl.lodz.p.it.spjava.e11.huntingBook.web.utils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import pl.lodz.p.it.spjava.e11.huntingBook.dto.AccountDTO;
import pl.lodz.p.it.spjava.e11.huntingBook.dto.AdministratorDTO;
import pl.lodz.p.it.spjava.e11.huntingBook.dto.HuntDTO;
import pl.lodz.p.it.spjava.e11.huntingBook.dto.HunterDTO;
import pl.lodz.p.it.spjava.e11.huntingBook.dto.MasterOfTheHunterDTO;
import pl.lodz.p.it.spjava.e11.huntingBook.dto.ResultDTO;
import pl.lodz.p.it.spjava.e11.huntingBook.model.Account;
import pl.lodz.p.it.spjava.e11.huntingBook.model.Administrator;
import pl.lodz.p.it.spjava.e11.huntingBook.model.Hunt;
import pl.lodz.p.it.spjava.e11.huntingBook.model.Hunter;
import pl.lodz.p.it.spjava.e11.huntingBook.model.MasterOfTheHunter;
import pl.lodz.p.it.spjava.e11.huntingBook.model.Result;

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

    public static HuntDTO createHuntDTOFromEntity(Hunt hunt) {
        return null == hunt ? null : new HuntDTO(hunt.getId(), hunt.getStartTime(), hunt.getEndTime(), hunt.getArea(), hunt.isIsEnded(), DTOConverter.createResultDTOFromEntity(hunt.getResult()));
    }
    
    public static ResultDTO createResultDTOFromEntity(Result result){
        return null == result ? null : new ResultDTO(result.getId(), result.getIsPrivateUse(), result.getTypeOfResult(), result.getAnimalType(), result.getAnimalWeight(), result.getIsConfirmed());
    }

    public static List<HuntDTO> createHuntsDTOListFromEntity(List<Hunt> hunts) {
        return null == hunts ? null : hunts.stream()
                .filter(Objects::nonNull)
                .map(hun -> DTOConverter.createHuntDTOFromEntity(hun))
                .collect(Collectors.toList());
    }

}
