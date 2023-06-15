package Services.User;

import Model.Abstracts.Users.User;
import Model.Abstracts.Users.UsersGroup;

import java.util.List;

public class GroupService<T extends UsersGroup<E>, E extends User>{

    private T group;

    public GroupService(T group) {
        setGroup(group);
    }

    public GroupService() {
        this(null);
    }

    private T getGroup() {
        return group;
    }

    private void setGroup(T group) {
        this.group = group;
    }

    /**
     * @return Уникальный идентификатор группы пользователей
     */
    public Long getGroupUUID() {
        return getGroup().getUUID();
    }

    List<Long> getUsersUUIDs() {
        return getGroup().getKeys();
    }

    List<E> getUsers() {
        return getGroup().getValues();
    }

    E getUser(Long UUID) {
        return getGroup().getValue(UUID);
    }


}
