package pl.lodz.p.it.spjava.e11.huntingBook.dto;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import pl.lodz.p.it.spjava.e11.huntingBook.model.Account;

public class DTOConverter {

    public static List<AccountDTO> createAccountDTOListFromEntity(List<Account> accounts) {
        return null == accounts ? null :accounts.stream()
                .filter(Objects::nonNull)
                .map(acc -> DTOConverter.createAccountDTOFromEntity(acc))
                .collect(Collectors.toList());
    }

    public static AccountDTO createAccountDTOFromEntity(Account account) {
        //toDO createAccountDTOFromEntity then cerateHunter/AdministratorDTOFromEntity
        return null == account ? null : new AccountDTO(account.getId(), account.getLogin(), account.getIsActive(), account.getName(), account.getSurname(), account.getEmail(), account.getType());
    }
    
}
